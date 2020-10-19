package ru.neosvet.lesson4;

public class Coords {
    private int x, y;
    private boolean exists;

    public Coords() {
        exists = false;
    }

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
        exists = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isExists() {
        return exists;
    }

    public boolean isValid(int max) {
        return x > -1 && x <= max && y > -1 && y <= max;
    }
}
