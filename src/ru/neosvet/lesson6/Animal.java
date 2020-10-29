package ru.neosvet.lesson6;

public class Animal {
    private int limitRun;
    private int limitSwim;
    private float limitJump;
    private String name;

    public Animal(String name, int limitRun, int limitSwim, float limitJump) {
        this.name = name;
        this.limitRun = limitRun;
        this.limitSwim = limitSwim;
        this.limitJump = limitJump;
    }

    public void run(int length) {
        if (length > limitRun)
            System.out.printf("%s не смог(ла) пробежать %d м. :(%n", name, length);
        else
            System.out.printf("%s пробежал(а) %d м. :)%n", name, length);
    }

    public void swim(int length) {
        if (length > limitSwim)
            System.out.printf("%s не смог(ла) проплыть %d м. :(%n", name, length);
        else
            System.out.printf("%s проплыл(а) %d м. :)%n", name, length);
    }

    public void jump(float height) {
        if (height > limitJump)
            System.out.printf("%s не смог(ла) прыгнуть на %d м. :(%n", name, height);
        else
            System.out.printf("%s прыгнул(а) %d м. :)%n", name, height);
    }

}
