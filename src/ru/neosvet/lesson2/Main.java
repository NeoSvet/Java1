package ru.neosvet.lesson2;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] result = invertArray(new int[]{1, 1, 0, 0, 1, 0, 1, 1, 0, 0});
        System.out.println("Result of task 1: " + Arrays.toString(result));
        result = getNumsMultiplesThree(8);
        System.out.println("Result of task 2: " + Arrays.toString(result));
        result = increaseNums(new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1}, 6);
        System.out.println("Result of task 3: " + Arrays.toString(result));
        printMatrixCross(5);
        findMinAndMax(new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1});
        checkBalance(new int[]{2, 2, 2, 1, 2, 2, 10, 1});
        checkBalance(new int[]{2, 3, 1, 2, 1});
    }

    private static void checkBalance(int[] arr) {
        int iStart = 0, iEnd = arr.length - 1;
        int sumStart = arr[iStart], sumEnd = arr[iEnd];
        while (iStart != iEnd - 1) {
            if (sumStart > sumEnd)
                sumEnd += arr[--iEnd];
            else
                sumStart += arr[++iStart];
        }
        System.out.print("Result of task 6: ");
        if (sumStart == sumEnd)
            System.out.printf("balance on %d-%d", iStart, iEnd);
        else
            System.out.print("no balance");
        System.out.println(" in " + Arrays.toString(arr));
    }

    private static void findMinAndMax(int[] arr) {
        int min = arr[0], max = min;
        for (int i = 1; i < arr.length; i++) {
            if (min > arr[i])
                min = arr[i];
            if (max < arr[i]) //maybe else if?
                max = arr[i];
        }
        System.out.printf("Result of task 5: min=%d, max=%d\n", min, max);
    }

    private static void printMatrixCross(int len) {
        int[][] arr = new int[len][len];
        System.out.println("Result of task 4:");
        for (int i = 0; i < len; i++) {
            arr[i][i] = 1;
            arr[i][len - i - 1] = 1;
            System.out.println(Arrays.toString(arr[i]));
        }
    }

    private static int[] increaseNums(int[] nums, int lessThan) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < lessThan)
                nums[i] *= 2;
        }
        return nums;
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
