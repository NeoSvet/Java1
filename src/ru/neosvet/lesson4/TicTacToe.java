package ru.neosvet.lesson4;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    enum Line {ROW, COLUMN, DIAGONAL_ONE, DIAGONAL_TWO}

    private static final int SIZE = 5, LINE_FOR_WIN = 3, COORD_FOR_EXIT = -1;
    private static final char DOT_EMPTY = '•', DOT_NULL = 'n', DOT_FIRST = 'X', DOT_SECOND = 'O';
    private static final String EMPTY = " ";

    private static final char[][] map = new char[SIZE][SIZE];
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static boolean human_first;

    public static void main(String[] args) {
        launchGame();
    }

    private static void launchGame() {
        do {
            System.out.println("Вы желаете ходить первым или вторым? 1 – первым / другое - вторым");
            human_first = scanner.next().equals("1");
            initMap();
            playGame();
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
        } while (scanner.next().equals("1"));
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
        return checkEnd(new Coords(x, y), true);
    }

    private static boolean isEndAfterAiTurn() {
        Coords coords;
        System.out.println("\nХод компьютера!");
        do {
            coords = getPointForAiStep();
        } while (isBusyCell(coords));

        map[coords.getX()][coords.getY()] = human_first ? DOT_SECOND : DOT_FIRST;
        return checkEnd(coords, false);
    }

    private static Coords getPointForAiStep() {
        Coords coords;

        char ai = human_first ? DOT_SECOND : DOT_FIRST;
        coords = getPointFromCheckChance(ai);
        if (coords.isExists())
            return coords;

        char human = human_first ? DOT_FIRST : DOT_SECOND;
        coords = getPointFromCheckChance(human);
        if (coords.isExists())
            return coords;

        return new Coords(random.nextInt(SIZE), random.nextInt(SIZE));
    }

    private static Coords getPointFromCheckChance(char symbol) {
        Coords coords = new Coords();
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (map[x][y] == symbol) {
                    coords = getPointFromCheckChance(x, y, symbol);
                    if (coords.isExists())
                        return coords;
                }
            }
        }
        return coords;
    }

    private static Coords getPointFromCheckChance(int x, int y, char symbol) {
        Coords result, coords = new Coords(x, y);
        result = getPointFromCheckChanceLine(symbol, coords, Line.ROW);
        if (result.isExists())
            return result;
        result = getPointFromCheckChanceLine(symbol, coords, Line.COLUMN);
        if (result.isExists())
            return result;
        result = getPointFromCheckChanceLine(symbol, coords, Line.DIAGONAL_ONE);
        if (result.isExists())
            return result;
        result = getPointFromCheckChanceLine(symbol, coords, Line.DIAGONAL_TWO);
        return result;
    }

    private static Coords getPointFromCheckChanceLine(char symbol, Coords source, Line line) {
        char cell;
        boolean firstSide = true;
        boolean hasEmpty = false;
        Coords result = new Coords();
        Coords coords = source.clone();
        int k = 1;

        Steps steps = getSteps(line);
        while (true) {
            coords.putSteps(steps);
            cell = getCell(coords);
            if (cell == symbol) {
                if (++k == LINE_FOR_WIN)
                    return result;
            } else if (firstSide && (cell != DOT_EMPTY || hasEmpty)) {
                firstSide = false;
                steps.invert();
                coords = source.clone();
            } else if (cell == DOT_EMPTY) {
                if (hasEmpty)
                    break;
                result = coords.clone();
                if (++k == LINE_FOR_WIN)
                    return result;
                hasEmpty = true;
            } else
                break;
        }
        return result;
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

    private static boolean isBusyCell(Coords coords) {
        return isBusyCell(coords.getX(), coords.getY());
    }

    private static boolean isBusyCell(int x, int y) {
        return map[x][y] != DOT_EMPTY;
    }

    private static boolean checkEnd(Coords coords, boolean isHuman) {
        if (checkWinOnCell(coords)) {
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

    private static boolean checkWinOnCell(Coords coords) {
        if (checkLine(coords, Line.ROW))
            return true;
        if (checkLine(coords, Line.COLUMN))
            return true;
        if (checkLine(coords, Line.DIAGONAL_ONE))
            return true;
        return checkLine(coords, Line.DIAGONAL_TWO);
    }

    private static boolean checkLine(Coords source, Line line) {
        char symbol = getCell(source);
        Coords coords = source.clone();
        boolean firstSide = true;
        int k = 1;

        Steps steps = getSteps(line);
        while (true) {
            coords.putSteps(steps);
            if (getCell(coords) == symbol) {
                if (++k == LINE_FOR_WIN)
                    return true;
            } else if (firstSide) {
                firstSide = false;
                steps.invert();
                coords = source.clone();
            } else
                return false;
        }
    }

    private static Steps getSteps(Line line) {
        switch (line) {
            case ROW:
                return new Steps(Steps.Direction.UP, Steps.Direction.NONE);
            case COLUMN:
                return new Steps(Steps.Direction.NONE, Steps.Direction.UP);
            case DIAGONAL_ONE:
                return new Steps(Steps.Direction.UP, Steps.Direction.UP);
            default: //case DIAGONAL_TWO:
                return new Steps(Steps.Direction.UP, Steps.Direction.DOWN);
        }
    }

    private static char getCell(Coords coords) {
        if (coords.isValid(SIZE - 1))
            return map[coords.getX()][coords.getY()];
        return DOT_NULL;
    }

}
