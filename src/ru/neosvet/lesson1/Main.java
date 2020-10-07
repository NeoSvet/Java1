package ru.neosvet.lesson1;

public class Main {

    public static void main(String[] args) {
        initVals();
        float result = countThreeTask(1, 2, 3, 4);
        System.out.printf("Result of task 3: %f \n", result);
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
