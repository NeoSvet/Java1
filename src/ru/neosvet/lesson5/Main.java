package ru.neosvet.lesson5;

public class Main {
    public static void main(String[] args) {
        Clerk[] clerks = new Clerk[]{
                new Clerk("Александр Шевелев", "Инженер",
                        "a.shevelev@clerk.ru", "89111111111", 35000, 38),
                new Clerk("Никита Силаев", "Программист",
                        "n.silaev@clerk.ru", "89222222222", 40000, 42),
                new Clerk("Сергей Велеско", "Механик",
                        "s.velesko@clerk.ru", "89333333333", 30000, 44),
                new Clerk("Варвара Агапова", "Лабарант",
                        "v.agapova@clerk.ru", "89444444444", 25000, 22),
                new Clerk("Юлия Силаева", "Секретарь",
                        "y.silaeva@clerk.ru", "89555555555", 30000, 30),
        };
        for (int i = 0; i < clerks.length; i++) {
            if (clerks[i].getAge() > 40)
                clerks[i].print();
        }
    }
}
