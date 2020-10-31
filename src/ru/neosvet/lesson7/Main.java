package ru.neosvet.lesson7;

public class Main {

    public static void main(String[] args) {
        startGame(getCats(), getPlate());
    }

    private static void startGame(Cat[] cats, Plate plate) {
        plate.addFood(6);
        plate.printInfo();
        for (int i = 0; i < cats.length; i++) {
            cats[i].eat(plate);
        }
        plate.printInfo();
    }

    private static Plate getPlate() {
        return new Plate(10);
    }

    private static Cat[] getCats() {
        return new Cat[] {
                new Cat("Борис Николаевич", 3),
                new Cat("Мурзик", 1),
                new Cat("Юкуб", 2)
        };
    }
}