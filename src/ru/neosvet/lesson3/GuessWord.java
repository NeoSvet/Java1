package ru.neosvet.lesson3;

import java.util.Random;
import java.util.Scanner;

public class GuessWord {
    private static Scanner scan = new Scanner(System.in);
    private static String[] words = {"apple", "orange", "lemon",
            "banana", "apricot", "avocado", "broccoli", "carrot",
            "cherry", "garlic", "grape", "melon", "leak", "kiwi",
            "mango", "mushroom", "nut", "olive", "pea", "peanut",
            "pear", "pepper", "pineapple", "pumpkin", "potato"};

    public static void main(String[] args) {
        showTip();
        launchGame();
    }

    private static void showTip() {
        System.out.println("В игре участвуют следующие слова:");
        for (int i = 0; i < words.length; i++) {
            System.out.print(words[i]);
            if (i > 0 && i % 5 == 0)
                System.out.println();
            else if (i < words.length - 1)
                System.out.print(", ");
        }
        System.out.println();
        System.out.println("Напишите help, чтобы повторить это сообщение.");
        System.out.println("Или exit, чтобы выйти.");
    }

    private static void launchGame() {
        Random random = new Random();
        while (true) {
            game(random.nextInt(words.length));
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
            if (!scan.next().equals("1"))
                break;
        }
    }

    private static void game(int n) {
        String s, w = words[n];
        int k = w.length() - 1;
        while (true) {
            System.out.println("Угадайте слово!");
            s = scan.next();
            if (s.equals(w)) {
                System.out.println("Молодец, вы угадали!");
                return;
            } else if (s.equals("help")) {
                showTip();
            } else if (s.equals("exit")) {
                System.exit(0);
            } else if (k == 0) {
                break;
            } else {
                showWord(w, w.length() - k);
                k--;
            }
        }
        System.out.println("Увы, попытки исчерпаны. Было загадано " + w);
    }

    private static void showWord(String word, int len) {
        System.out.print(word.substring(0, len));
        for (int i = len; i < 15; i++) {
            System.out.print("#");
        }
        System.out.println();
    }
}
