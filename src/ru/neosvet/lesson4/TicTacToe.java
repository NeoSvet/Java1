package ru.neosvet.lesson4;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static final int SIZE = 5, LINE_FOR_WIN = 3;
    private static final char DOT_EMPTY = '•', DOT_FIRST = 'X', DOT_SECOND = 'O';
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
            if (human_first)
                printMap();
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
        while (true) {
            if (human_first)
                humanTurn();
            else
                aiTurn();
            printMap();
            if (checkEnd(DOT_FIRST))
                break;

            if (human_first)
                aiTurn();
            else
                humanTurn();
            printMap();
            if (checkEnd(DOT_SECOND))
                break;
        }
    }

    private static void humanTurn() {
        int rowNumber;
        int colNumber;

        System.out.println("\nВведите номер строки и столбца! (0 - для выхода)");
        while (true) {
            do {
                System.out.print("Строка: ");
                rowNumber = scanner.nextInt();
            } while (isNotValid(rowNumber));

            do {
                System.out.print("Столбец: ");
                colNumber = scanner.nextInt();
            } while (isNotValid(colNumber));

            if (isBusyCell(rowNumber, colNumber))
                System.out.println("Вы выбрали занятую ячейку.");
            else
                break;
        }

        map[rowNumber - 1][colNumber - 1] = human_first ? DOT_FIRST : DOT_SECOND;
    }

    private static void aiTurn() {
        int rowNumber;
        int colNumber;

        System.out.println("\nХод компьютера!");
        do {
            rowNumber = random.nextInt(SIZE) + 1;
            colNumber = random.nextInt(SIZE) + 1;
        } while (isBusyCell(rowNumber, colNumber));

        map[rowNumber - 1][colNumber - 1] = human_first ? DOT_SECOND : DOT_FIRST;
    }

    private static boolean isNotValid(int coord) {
        if (coord == 0)
            System.exit(0);
        if (coord < 1 || coord > SIZE) {
            System.out.printf("Координата должна быть от 1 до %d.%n", SIZE);
            return true;
        }
        return false;
    }

    private static boolean isBusyCell(int rowNumber, int colNumber) {
        return map[rowNumber - 1][colNumber - 1] != DOT_EMPTY;
    }

    private static boolean checkEnd(char symbol) {
        if (checkWin(symbol)) {
            System.out.println((symbol == DOT_FIRST && human_first) || (symbol != DOT_FIRST && !human_first)
                    ? "УРА! Вы победили!" : "Восстание близко! AI победил");
            return true;
        }

        if (isMapFull()) {
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

    private static boolean checkWin(char symbol) {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (map[x][y] == symbol) {
                    if (checkOnCell(x, y))
                        return true;
                }
            }
        }
        return false;
    }

    private static boolean checkOnCell(final int x, final int y) {
        if (checkLine(x, y, 1, 0)) //check row
            return true;
        if (checkLine(x, y, 0, 1)) //check column
            return true;
        if (checkLine(x, y, 1, 1)) //check first diagonal
            return true;
        if (checkLine(x, y, 1, -1)) //check second diagonal
            return true;

        return false;
    }

    private static boolean checkLine(final int x, final int y, int step_x, int step_y) {
        char cell, symbol = map[x][y];
        int k = 1, i = x, j = y;
        boolean firstSide = true;
        while (true) {
            i += step_x;
            j += step_y;
            cell = getCell(i, j);
            if (cell == symbol) {
                if (++k == LINE_FOR_WIN)
                    return true;
            } else if (firstSide) {
                firstSide = false;
                step_x = step_x * -1;
                step_y = step_y * -1;
                i = x;
                j = y;
            } else
                return false;
        }
    }

    private static char getCell(int x, int y) {
        if (x > -1 && x < SIZE && y > -1 && y < SIZE)
            return map[x][y];
        return DOT_EMPTY;
    }

}
