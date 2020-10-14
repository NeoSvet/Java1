package ru.neosvet.lesson3;

import java.util.Random;
import java.util.Scanner;

public class GuessNumber {
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        launchGame();
    }

    private static void launchGame() {
        Random random = new Random();
        while(true) {
            game(random.nextInt(10));
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
            if(!scan.next().equals("1"))
                break;
        }
    }

    private static void game(int n) {
        int k = 3;
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
