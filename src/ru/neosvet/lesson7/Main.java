package ru.neosvet.lesson7;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final int MIN_APPETITE = 2, MAX_APPETITE = 5;
    private static final int MIN_FOOD = 4;
    private static final int MIN_CAPACITY = 10, MAX_CAPACITY = 15;
    private static Random random = new Random();

    public static void main(String[] args) {
        startGame(getCats(), getPlates());
    }

    private static void startGame(Cat[] cats, Plate[] plates) {
        Scanner scanner = new Scanner(System.in);
        int i, day = 1;

        do {
            System.out.println("День №" + day);
            for (i = 0; i < plates.length; i++) {
                plates[i].addFood(random.nextInt(plates[i].getCapacity()) + MIN_FOOD);
                plates[i].printInfo();
            }
            for (i = 0; i < cats.length; i++) {
                cats[i].eat(plates);
                cats[i].addAppetite(random.nextInt(MAX_APPETITE) + MIN_APPETITE);
            }
            for (i = 0; i < plates.length; i++) {
                plates[i].printInfo();
            }
            day++;
            System.out.println("1 – продолжить / другое - закончить");
        } while (scanner.next().equals("1"));
    }

    private static Plate[] getPlates() {
        return new Plate[]{
                getRandomePlate(),
                getRandomePlate(),
                getRandomePlate()
        };
    }

    private static Plate getRandomePlate() {
        return new Plate(random.nextInt(MAX_CAPACITY - MIN_CAPACITY) + MIN_CAPACITY);
    }

    private static Cat[] getCats() {
        return new Cat[]{
                new Cat("Борис Николаевич", 4),
                new Cat("Мурзик", 2),
                new Cat("Юкуб", 3),
                new Cat("Матроскин", 2)
        };
    }
}