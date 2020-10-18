package ru.neosvet.lesson4;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static final int SIZE = 3;
    private static final char DOT_EMPTY = '•', DOT_HUMAN = 'X', DOT_AI = 'O';
    private static final String EMPTY = " ";

    private static char[][] map = new char[SIZE][SIZE];
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        launchGame();
    }

    private static void launchGame() {
        while (true) {
            initMap();
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
            humanTurn();
            printMap();
            if (checkEnd(DOT_HUMAN))
                break;

            aiTurn();
            printMap();
            if (checkEnd(DOT_AI))
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
            } while (!isCoordValid(rowNumber));

            do {
                System.out.print("Столбец: ");
                colNumber = scanner.nextInt();
            } while (!isCoordValid(colNumber));

            if (isBusyCell(rowNumber, colNumber))
                System.out.println("Вы выбрали занятую ячейку.");
            else
                break;
        }

        map[rowNumber - 1][colNumber - 1] = DOT_HUMAN;
    }

    private static void aiTurn() {
        int rowNumber;
        int colNumber;

        System.out.println("\nХод компьютера!");
        do {
            rowNumber = random.nextInt(SIZE) + 1;
            colNumber = random.nextInt(SIZE) + 1;
        } while (isBusyCell(rowNumber, colNumber));

        map[rowNumber - 1][colNumber - 1] = DOT_AI;
    }

    private static boolean isCoordValid(int coord) {
        if (coord == 0)
            System.exit(0);
        if (coord < 1 || coord > SIZE) {
            System.out.printf("Координата должна быть от 1 до %d.%n", SIZE);
            return false;
        }
        return true;
    }

    private static boolean isBusyCell(int rowNumber, int colNumber) {
        return map[rowNumber - 1][colNumber - 1] != DOT_EMPTY;
    }

    private static boolean checkEnd(char symbol) {
        if (checkWin(symbol)) {
            System.out.println((symbol == DOT_HUMAN ? "УРА! Вы победили!"
                    : "Восстание близко! AI победил"));
            return true;
        }

        if (isMapFull()) {
            System.out.println("Ничья!");
            return true;
        }

        return false;
    }

    private static boolean checkWin(char symbol) {
        if (map[0][0] == symbol && map[0][1] == symbol && map[0][2] == symbol) return true;
        if (map[1][0] == symbol && map[1][1] == symbol && map[1][2] == symbol) return true;
        if (map[2][0] == symbol && map[2][1] == symbol && map[2][2] == symbol) return true;

        if (map[0][0] == symbol && map[1][0] == symbol && map[2][0] == symbol) return true;
        if (map[0][1] == symbol && map[1][1] == symbol && map[2][1] == symbol) return true;
        if (map[0][2] == symbol && map[1][2] == symbol && map[2][2] == symbol) return true;

        if (map[0][2] == symbol && map[1][1] == symbol && map[2][2] == symbol) return true;
        if (map[0][2] == symbol && map[1][1] == symbol && map[2][0] == symbol) return true;

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

}
