package ru.neosvet.lesson2;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] task1 = invertArray(new int[]{1, 1, 0, 0, 1, 0, 1, 1, 0, 0});
        System.out.println("Result of task 1: " + Arrays.toString(task1));
        int[] task2 = getNumsMultiplesThree(8);
        System.out.println("Result of task 2: " + Arrays.toString(task2));
    }

    private static int[] getNumsMultiplesThree(int len) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = i * 3;
        }
        return arr;
    }

    private static int[] invertArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0 || arr[i] == 1)
                arr[i] = (arr[i] == 0 ? 1 : 0);
        }
        return arr;
    }
}
