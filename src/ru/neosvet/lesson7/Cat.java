package ru.neosvet.lesson7;

public class Cat {
    private String name;
    private int appetite;

    public Cat(String name, int appetite) {
        this.appetite = appetite;
        this.name = name;
    }

    public void eat(Plate plate) {
        if (plate.getFood() == 0) {
            System.out.println("Котик не может поесть, ибо кормушка пуста!");
            return;
        }
        System.out.print("Котик питается. ");

        doEat(plate);
    }

    private void doEat(Plate plate) {
        if (plate.getFood() >= appetite) {
            plate.decreaseFood(appetite);
            System.out.println("Кот наелся!");
        } else {
            plate.decreaseFood(plate.getFood());
            appetite -= plate.getFood();
            System.out.println("Кот поел, но не наелся!");
        }
    }
}
