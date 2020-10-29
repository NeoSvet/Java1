package ru.neosvet.lesson6;

import java.util.Random;

public class Animal {
    private final int maxDeviation = 20;
    private int limitRun;
    private int limitSwim;
    private float limitJump;
    private String name;
    Random random = new Random();

    public Animal(String name, int limitRun, int limitSwim, float limitJump) {
        this.name = name;
        this.limitRun = getWithDeviation(limitRun);
        this.limitSwim = getWithDeviation(limitSwim);
        this.limitJump = getWithDeviation(limitJump);
    }

    private int getWithDeviation(int value) {
        return (int) getWithDeviation((float) value);
    }

    private float getWithDeviation(float value) {
        int procent = random.nextInt(maxDeviation);
        if (random.nextBoolean())
            value += value * procent / 100f;
        else
            value -= value * procent / 100f;
        return value;
    }

    public void run(int length) {
        if (length > limitRun)
            System.out.printf("%s не смог(ла) пробежать %d м. :(%n", name, length);
        else
            System.out.printf("%s пробежал(а) %d м. :)%n", name, length);
    }

    public void swim(int length) {
        if(limitSwim == 0) {
            System.out.printf("%s не умеет плавыть и не может проплыть %d м.%n", name, length);
            return;
        }
        if (length > limitSwim)
            System.out.printf("%s не смог(ла) проплыть %d м. :(%n", name, length);
        else
            System.out.printf("%s проплыл(а) %d м. :)%n", name, length);
    }

    public void jump(float height) {
        if (height > limitJump)
            System.out.printf("%s не смог(ла) прыгнуть на %.1f м. :(%n", name, height);
        else
            System.out.printf("%s прыгнул(а) %.1f м. :)%n", name, height);
    }

    public void printInfo() {
        if(limitSwim == 0) {
            System.out.printf("%s умеет бегать на %d м. и прыгать на %.1f м., а плавать не умеет.%n",
                    name, limitRun, limitJump);
            return;
        }
        System.out.printf("%s умеет бегать на %d м., плавать на %d м. и прыгать на %.1f м.%n",
                name, limitRun, limitSwim, limitJump);
    }
}
