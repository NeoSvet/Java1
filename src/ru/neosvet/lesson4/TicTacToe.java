package ru.neosvet.lesson4;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    enum Line {ROW, COLUMN, DIAGONAL_ONE, DIAGONAL_TWO}

    private static final int SIZE = 5, LINE_FOR_WIN = 3, COORD_FOR_EXIT = -1;
    private static final char DOT_EMPTY = '•', DOT_NULL = 'n', DOT_FIRST = 'X', DOT_SECOND = 'O';
    private static final String EMPTY = " ";

    private static char[][] map = new char[SIZE][SIZE];
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    private static boolean human_first;

    public static void main(String[] args) {
        launchGame();
    }

    private static void launchGame() {
        while (true) {
            System.out.println("Вы желаете ходить первым или вторым? 1 – первым / другое - вторым");
            human_first = scanner.next().equals("1");
            initMap();
            playGame();
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
            if (!scanner.next().equals("1"))
                break;
        }
    }

    private static void initMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    private static void printMap() {
        printMapHeader();
        printMapRow();
    }

    private static void printMapHeader() {
        System.out.print(EMPTY + EMPTY);
        for (int i = 0; i < SIZE; i++) {
            printMapNumber(i);
        }
        System.out.println();
    }

    private static void printMapNumber(int i) {
        System.out.print(i + 1 + EMPTY);
    }

    private static void printMapRow() {
        for (int i = 0; i < SIZE; i++) {
            printMapNumber(i);
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + EMPTY);
            }
            System.out.println();
        }
    }

    private static void playGame() {
        boolean isEnd;
        boolean firstStep = true;
        do {
            printMap();
            if ((human_first && firstStep) || (!human_first && !firstStep))
                isEnd = isEndAfterHumanTurn();
            else
                isEnd = isEndAfterAiTurn();
            firstStep = !firstStep;
        } while (!isEnd);
    }

    private static boolean isEndAfterHumanTurn() {
        int x, y;
        System.out.println("\nВведите номер строки и столбца! (0 - для выхода)");
        while (true) {
            do {
                System.out.print("Строка: ");
                x = scanner.nextInt() - 1;
            } while (isNotValid(x));

            do {
                System.out.print("Столбец: ");
                y = scanner.nextInt() - 1;
            } while (isNotValid(y));

            if (isBusyCell(x, y))
                System.out.println("Вы выбрали занятую ячейку.");
            else
                break;
        }

        map[x][y] = human_first ? DOT_FIRST : DOT_SECOND;
        return checkEnd(x, y, true);
    }

    private static boolean isEndAfterAiTurn() {
        Point p;
        System.out.println("\nХод компьютера!");
        do {
            p = getPointForAiStep();
        } while (isBusyCell(p.x, p.y));

        map[p.x][p.y] = human_first ? DOT_SECOND : DOT_FIRST;
        return checkEnd(p.x, p.y, false);
    }

    private static Point getPointForAiStep() {
        Point p;

        char ai = human_first ? DOT_SECOND : DOT_FIRST;
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (map[x][y] == ai) {
                    p = getPointFromCheckChance(x, y, ai);
                    if (p != null)
                        return p;
                }
            }
        }

        char human = human_first ? DOT_FIRST : DOT_SECOND;
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (map[x][y] == human) {
                    p = getPointFromCheckChance(x, y, human);
                    if (p != null)
                        return p;
                }
            }
        }

        return new Point(random.nextInt(SIZE), random.nextInt(SIZE));
    }

    private static Point getPointFromCheckChance(int x, int y, char symbol) {
        Point p;
        p = getPointFromCheckChanceLine(symbol, x, y, Line.ROW);
        if (p != null)
            return p;
        p = getPointFromCheckChanceLine(symbol, x, y, Line.COLUMN);
        if (p != null)
            return p;
        p = getPointFromCheckChanceLine(symbol, x, y, Line.DIAGONAL_ONE);
        if (p != null)
            return p;
        p = getPointFromCheckChanceLine(symbol, x, y, Line.DIAGONAL_TWO);
        return p;
    }

    private static Point getPointFromCheckChanceLine(char symbol, int x, int y, Line line) {
        char cell;
        int k = 1, i = x, j = y;
        boolean firstSide = true;
        boolean hasEmpty = false;
        Point p = null;

        Point steps = getSteps(line);
        while (true) {
            i += steps.x;
            j += steps.y;
            cell = getCell(i, j);
            if (cell == symbol) {
                if (++k == LINE_FOR_WIN)
                    return p;
            } else if (firstSide && (cell != DOT_EMPTY || hasEmpty)) {
                firstSide = false;
                steps.x = steps.x * -1;
                steps.y = steps.y * -1;
                i = x;
                j = y;
            } else if (cell == DOT_EMPTY) {
                if (hasEmpty)
                    break;
                p = new Point(i, j);
                if (++k == LINE_FOR_WIN)
                    return p;
                hasEmpty = true;
            } else
                break;
        }
        return null;
    }

    private static boolean isNotValid(int coord) {
        if (coord == COORD_FOR_EXIT)
            System.exit(0);
        if (coord < 0 || coord >= SIZE) {
            System.out.printf("Координата должна быть от 1 до %d.%n", SIZE);
            return true;
        }
        return false;
    }

    private static boolean isBusyCell(int x, int y) {
        return map[x][y] != DOT_EMPTY;
    }

    private static boolean checkEnd(int x, int y, boolean isHuman) {
        if (checkWinOnCell(x, y)) {
            printMap();
            System.out.println(isHuman ? "УРА! Вы победили!"
                    : "Восстание близко! AI победил");
            return true;
        }

        if (isMapFull()) {
            printMap();
            System.out.println("Ничья!");
            return true;
        }

        return false;
    }

    private static boolean isMapFull() {
        for (char[] chars : map) {
            for (char aChar : chars) {
                if (aChar == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkWinOnCell(final int x, final int y) {
        if (checkLine(x, y, Line.ROW))
            return true;
        if (checkLine(x, y, Line.COLUMN))
            return true;
        if (checkLine(x, y, Line.DIAGONAL_ONE))
            return true;
        if (checkLine(x, y, Line.DIAGONAL_TWO))
            return true;

        return false;
    }

    private static boolean checkLine(final int x, final int y, Line line) {
        char symbol = map[x][y];
        int k = 1, i = x, j = y;
        boolean firstSide = true;

        Point steps = getSteps(line);
        while (true) {
            i += steps.x;
            j += steps.y;
            if (getCell(i, j) == symbol) {
                if (++k == LINE_FOR_WIN)
                    return true;
            } else if (firstSide) {
                firstSide = false;
                steps.x = steps.x * -1;
                steps.y = steps.y * -1;
                i = x;
                j = y;
            } else
                return false;
        }
    }

    private static Point getSteps(Line line) {
        switch (line) {
            case ROW:
                return new Point(1, 0);
            case COLUMN:
                return new Point(0, 1);
            case DIAGONAL_ONE:
                return new Point(1, 1);
            default: //case DIAGONAL_TWO:
                return new Point(1, -1);
        }
    }

    private static char getCell(int x, int y) {
        if (x > -1 && x < SIZE && y > -1 && y < SIZE)
            return map[x][y];
        return DOT_NULL;
    }

}
