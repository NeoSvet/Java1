package ru.neosvet.lesson6;

public class Cat extends Animal {
    public Cat(String name, boolean male) {
        super(male ? "Кот " : "Кошка " + name, male, 200, 0, 2f);
    }

    @Override
    public void swim(int length) {
        System.out.printf("%s не умеет плавыть и не может проплыть %d м.%n", getName(), length);
    }
}
