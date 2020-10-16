package ru.neosvet.lesson2;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] result = invertArray(new int[]{1, 1, 0, 0, 1, 0, 1, 1, 0, 0});
        System.out.println("Result of task 1: " + Arrays.toString(result));
        result = getNumsMultiplesThree(8);
        System.out.println("Result of task 2: " + Arrays.toString(result));
        int[] arr = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        result = increaseNums(arr, 6);
        System.out.println("Result of task 3: " + Arrays.toString(result));
        printMatrixCross(5);
        findMinAndMax(arr);
        checkBalance(new int[]{2, 2, 2, 1, 2, 2, 10, 1});
        checkBalance(new int[]{2, 3, 1, 2, 1});
        result = moveItemsInArr(arr, -4);
        System.out.println("Result of task 7: " + Arrays.toString(result));
        result = moveItemsInArr2(arr, -4);
        System.out.println("Result of task 7 (without addition array): " + Arrays.toString(result));
    }

    private static int[] moveItemsInArr2(int[] arr, int step) {
        System.out.println("Array in task 7 (without addition array): " + Arrays.toString(arr));
        int ni, item1, item2, count_moved = 0, i = 0;
        step = step % arr.length;
        while (count_moved < arr.length) {
            ni = i;
            item1 = arr[i];
            do {
                ni = GetNewIndex(ni, step, arr.length);
                item2 = arr[ni];
                arr[ni] = item1;
                item1 = item2;
                count_moved++;
            } while (ni != i);
            i++;
        }
        return arr;
    }

    private static int GetNewIndex(int i, int step, int limit) {
        i += step;
        if (i < 0)
            i += limit;
        else if (i >= limit)
            i -= limit;
        return i;
    }

    private static int[] moveItemsInArr(int[] arr, int step) {
        System.out.println("Array in task 7: " + Arrays.toString(arr));
        int[] narr = new int[arr.length];
        int ni;
        step = step % arr.length;
        for (int i = 0; i < arr.length; i++) {
            ni = GetNewIndex(i, step, arr.length);
            narr[ni] = arr[i];
        }
        return narr;
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
        int[] arr = nums.clone();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < lessThan)
                arr[i] *= 2;
        }
        return arr;
    }

    private static int[] getNumsMultiplesThree(int len) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = i * 3;
        }
        return arr;
    }

    private static int[] invertArray(int[] arr) {
        int[] narr = arr.clone();
        for (int i = 0; i < narr.length; i++) {
            if (narr[i] == 0 || narr[i] == 1)
                narr[i] = (narr[i] == 0 ? 1 : 0);
        }
        return narr;
    }
}
