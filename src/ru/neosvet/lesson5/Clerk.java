package ru.neosvet.lesson5;

public class Clerk {
    private String name, position, email, phone;
    private int salary, age;

    public Clerk(String name, String position, String email, String phone, int salary, int age) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public void print() {
        System.out.printf("Сотрудник: %s, должность: %s, email: %s, телефон: %s, " +
                "зарплата: %,d, возраст: %d.%n", name, position, email, phone, salary, age);
    }
}

