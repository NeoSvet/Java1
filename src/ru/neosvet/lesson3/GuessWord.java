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
    private static char[] letters = new char[15];

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
            clearLetters();
            game(random.nextInt(words.length));
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
            if (!scan.next().equals("1"))
                break;
        }
    }

    private static void clearLetters() {
        for (int i = 0; i < letters.length; i++) {
            letters[i] = '#';
        }
    }

    private static void game(int n) {
        String s, w = words[n];
        int k = 5;
        while (k > 0) {
            System.out.printf("Угадайте слово! (%d попытки(ка))%n", k);
            s = scan.next();
            if (s.equals(w)) { //win
                System.out.println("Молодец, вы угадали!");
                return;
            } else if (s.equals("help")) {
                showTip();
            } else if (s.equals("exit")) {
                System.exit(0);
            } else { //not win
                showWord(w, s);
                k--;
            }
        }
        System.out.println("Увы, попытки исчерпаны. Было загадано " + w);
    }

    private static void showWord(String right_word, String entered_word) {
        for (int i = 0; i < letters.length; i++) {
            if (i < right_word.length() && i < entered_word.length()
                    && right_word.charAt(i) == entered_word.charAt(i)) {
                letters[i] = right_word.charAt(i);
            }
            System.out.print(letters[i]);
        }
        System.out.println();
    }
}
