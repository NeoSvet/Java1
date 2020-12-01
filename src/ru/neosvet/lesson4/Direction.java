package ru.neosvet.lesson4;

public enum Direction {
    UP(1), DOWN(-1), NONE(0);

    private int value;

    Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Direction invert() {
        if (this == UP)
            return DOWN;
        if (this == DOWN)
            return UP;
        return NONE;
    }
}
