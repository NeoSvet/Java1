package ru.neosvet.lesson7;

public class Plate {

    private int capacity;
    private int food;

    public int getFood() {
        return food;
    }

    public Plate(int capacity) {
        this.capacity = capacity;
    }

    public void addFood(int food) {
        this.food += food;
    }

    @Override
    public String toString() {
        return "Plate{" + "capacity=" + capacity + ", food=" + food + '}';
    }


    public void printInfo() {
        System.out.println(this);
    }

    public void decreaseFood(int appetite) {
        this.food -= appetite;
    }
}
