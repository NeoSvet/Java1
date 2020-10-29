package ru.neosvet.lesson6;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final int MAX_RUN = 600, MAX_SWIM = 14, MAX_JUMP = 3;
    private static final int MIN_RUN = 50, MIN_SWIM = 3;

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
            run = random.nextInt(MAX_RUN - MIN_RUN) + MIN_RUN;
            swim = random.nextInt(MAX_SWIM - MIN_SWIM) + MIN_SWIM;
            jump = (float) random.nextInt(MAX_JUMP) + random.nextFloat();
            for (i = 0; i < animals.length; i++) {
                animals[i].run(run);
                animals[i].swim(swim);
                animals[i].jump(jump);
            }
            System.out.println("Продолжить мучать зверушек? 1 – да / другое - закончить");
        } while (scanner.next().equals("1"));
    }
}
