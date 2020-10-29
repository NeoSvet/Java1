package ru.neosvet.lesson6;

import java.util.Random;

public class Animal {
    private final int MAX_DEVIATION = 20;
    private int limitRun, limitSwim;
    private float limitJump;
    private String name;
    private boolean male;
    private Random random = new Random();

    public Animal(String name, boolean male, int limitRun, int limitSwim, float limitJump) {
        this.name = name;
        this.male = male;
        this.limitRun = getWithDeviation(limitRun);
        this.limitSwim = getWithDeviation(limitSwim);
        this.limitJump = getWithDeviation(limitJump);
    }

    public String getName() {
        return name;
    }

    private int getWithDeviation(int value) {
        return (int) getWithDeviation((float) value);
    }

    private float getWithDeviation(float value) {
        int procent = random.nextInt(MAX_DEVIATION);
        if (random.nextBoolean())
            value += value * procent / 100f;
        else
            value -= value * procent / 100f;
        return value;
    }

    public void run(int length) {
        if (length > limitRun)
            System.out.printf("%s не смог%s пробежать %d м. :(%n", name, getEndingLA(), length);
        else
            System.out.printf("%s пробежал%s %d м. :)%n", name, getEndingA(), length);
    }

    public void swim(int length) {
        if (length > limitSwim)
            System.out.printf("%s не смог%s проплыть %d м. :(%n", name, getEndingLA(), length);
        else
            System.out.printf("%s проплыл%s %d м. :)%n", name, getEndingA(), length);
    }

    public void jump(float height) {
        if (height > limitJump)
            System.out.printf("%s не смог%s прыгнуть на %.1f м. :(%n", name, getEndingLA(), height);
        else
            System.out.printf("%s прыгнул%s %.1f м. :)%n", name, getEndingA(), height);
    }

    private String getEndingA() {
        return male ? "" : "a";
    }

    private String getEndingLA() {
        return male ? "" : "лa";
    }

    public void printInfo() {
        if (limitSwim == 0) {
            System.out.printf("%s умеет бегать на %d м. и прыгать на %.1f м., а плавать не умеет.%n",
                    name, limitRun, limitJump);
            return;
        }
        System.out.printf("%s умеет бегать на %d м., плавать на %d м. и прыгать на %.1f м.%n",
                name, limitRun, limitSwim, limitJump);
    }
}
