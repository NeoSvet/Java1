package ru.neosvet.lesson7;

public class Cat {
    private String name;
    private int appetite;

    public Cat(String name, int appetite) {
        this.appetite = appetite;
        this.name = name;
    }

    public void eat(Plate plate) {
        System.out.println("Котик питается");

        if (plate.getFood() >= appetite) {
            doEat(plate);
            System.out.println("Кот доволен");
        } else {
            System.out.println("Мне нужна еда!");
        }
    }

    private void doEat(Plate plate) {
        plate.decreaseFood(appetite);
    }
}
