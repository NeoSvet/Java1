package ru.neosvet.lesson3;

import java.util.Random;
import java.util.Scanner;

public class GuessNumber {
    private static final int COUNT_TRY = 3, MIN_NUMBER = 0, BOUND_NUMBER = 10;
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        launchGame();
    }

    private static void launchGame() {
        Random random = new Random();
        while(true) {
            game(random.nextInt(BOUND_NUMBER) + MIN_NUMBER);
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
            if(!scan.next().equals("1"))
                break;
        }
    }

    private static void game(int n) {
        int k = COUNT_TRY;
        while (k > 0) {
            System.out.printf("Угадайте число! (%d попытки(ка))%n", k);
            try {
                if (scan.nextInt() == n) {
                    System.out.println("Молодец, вы угадали!");
                    return;
                } else
                    k--;
            } catch (Exception e) {
                System.out.println("Введите целое число!");
            }
        }
        System.out.println("Увы, попытки исчерпаны. Было загадано " + n);
    }
}
