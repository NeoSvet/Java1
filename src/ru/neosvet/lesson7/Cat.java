package ru.neosvet.lesson7;

public class Cat {
    private String name;
    private int appetite;
    private boolean live = true;

    public Cat(String name, int appetite) {
        this.appetite = appetite;
        this.name = name;
    }

    public void eat(Plate[] plates) {
        if (appetite == 0) {
            System.out.printf("Котик %s не голоден!%n", name);
            return;
        }
        for (int i = 0; i < plates.length && appetite > 0; i++) {
            eat(plates[i]);
        }
    }

    public void eat(Plate plate) {
        if (plate.getFood() == 0) {
            System.out.printf("Котик %s не может поесть, ибо кормушка пуста!%n", name);
            return;
        }
        System.out.printf("Котик %s питается. ", name);

        doEat(plate);
    }

    private void doEat(Plate plate) {
        if (plate.getFood() >= appetite) {
            plate.decreaseFood(appetite);
            appetite = 0;
            System.out.println("Кот наелся!");
        } else {
            plate.decreaseFood(plate.getFood());
            appetite -= plate.getFood();
            System.out.println("Кот поел, но не наелся!");
        }
    }

    public void addAppetite(int add) {
        appetite += add;
    }

    public int getAppetite() {
        return appetite;
    }

    public void dead() {
        System.out.printf("Котик %s помер от голода! :(%n", name);
        live = false;
    }

    public boolean isDead() {
        return !live;
    }
}
