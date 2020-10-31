package ru.neosvet.lesson7;

public class Plate {
    private final int capacity;
    private int food;

    public Plate(int capacity) {
        this.capacity = capacity;
    }

    public int getFood() {
        return food;
    }

    public void addFood(int food) {
        if (this.food + food > capacity)
            this.food = capacity;
        else
            this.food += food;
    }

    @Override
    public String toString() {
        if (food == 0)
            return "Тарелка пуста!";
        return "В тарелке " + food + " из " + capacity;
    }

    public void printInfo() {
        System.out.println(this);
    }

    public void decreaseFood(int appetite) {
        this.food -= appetite;
    }
}
