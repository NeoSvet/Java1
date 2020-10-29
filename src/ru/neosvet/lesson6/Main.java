package ru.neosvet.lesson6;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final int maxRun = 600, maxSwim = 14, maxJump = 3;
    private static final int minRun = 50, minSwim = 3;

    public static void main(String[] args) {
        Dog dog = new Dog("Бобик", true);
        Cat cat = new Cat("Мурка", false);
        dog.printInfo();
        cat.printInfo();

        startObstacles(new Animal[]{dog, cat});
    }

    private static void startObstacles(Animal[] animals) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int run, swim, i;
        float jump;
        do {
            run = random.nextInt(maxRun - minRun) + minRun;
            swim = random.nextInt(maxSwim - minSwim) + minSwim;
            jump = (float) random.nextInt(maxJump) + random.nextFloat();
            for (i = 0; i < animals.length; i++) {
                animals[i].run(run);
                animals[i].swim(swim);
                animals[i].jump(jump);
            }
            System.out.println("Продолжить мучать зверушек? 1 – да / другое - закончить");
        } while (scanner.next().equals("1"));
    }
}
