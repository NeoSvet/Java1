package ru.neosvet.lesson7;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final int MAX_APPETITE = 4;
    private static final int CAPACITY = 10;

    public static void main(String[] args) {
        startGame(getCats(), getPlate());
    }

    private static void startGame(Cat[] cats, Plate plate) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int day = 1;

        do {
            System.out.println("День №" + day);
            plate.addFood(random.nextInt(plate.getCapacity()));
            plate.printInfo();
            for (int i = 0; i < cats.length; i++) {
                cats[i].eat(plate);
                cats[i].addAppetite(random.nextInt(MAX_APPETITE));
            }
            plate.printInfo();
            day++;
            System.out.println("1 – продолжить / другое - закончить");
        } while (scanner.next().equals("1"));
    }

    private static Plate getPlate() {
        return new Plate(CAPACITY);
    }

    private static Cat[] getCats() {
        return new Cat[]{
                new Cat("Борис Николаевич", 3),
                new Cat("Мурзик", 1),
                new Cat("Юкуб", 2)
        };
    }
}