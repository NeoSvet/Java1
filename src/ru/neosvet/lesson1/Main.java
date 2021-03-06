package ru.neosvet.lesson1;

public class Main {

    public static void main(String[] args) {
        initVals();
        float result = countThreeTask(1, 2, 3, 4);
        System.out.printf("Result of task 3: %f \n", result);
        System.out.printf("Result of task 4: %b \n", checkNumbers(4, 8));
        checkPolarity(-5);
        System.out.printf("Result of task 6: %b \n", isNegativeNumber(-7));
        printName("Юрий");
        int year = 2020;
        if (isLeapYear(year))
            System.out.printf("Result of task 8: %d - високосный год\n", year);
        else
            System.out.printf("Result of task 8: %d - не високосный год\n", year);
    }

    private static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0 && year % 400 != 0)
                return false;
            return true;
        }
        return false;
    }

    private static void printName(String name) {
        System.out.printf("Result of task 7: Привет, %s!\n", name);
    }

    private static boolean isNegativeNumber(int i) {
        return i < 0;
    }

    private static void checkPolarity(int i) {
        if (i < 0)
            System.out.println("Result of task 5: передали отрицательное число");
        else
            System.out.println("Result of task 5: передали положительное число");
    }

    private static boolean checkNumbers(int a, int b) {
        int sum = a + b;
        if (sum > 9 && sum < 21)
            return true;
        else
            return false;
    }

    private static float countThreeTask(int a, int b, int c, int d) {
        return (float) a * (b + ((float) c / d));
    }

    private static void initVals() {
        byte byteVal = 4;
        short shortVal = 1000;
        int intVal = 99110;
        long longVal = 1010101L;
        float floatVal = 124.34f;
        double doubleVal = 33334.1313131;
        char charVal = 'C';
        boolean booleanVal = true;
    }
}
