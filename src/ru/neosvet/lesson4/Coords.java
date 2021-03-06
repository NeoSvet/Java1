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

    public boolean exists() {
        return exists;
    }

    public boolean isValid(int max) {
        return x > -1 && x <= max && y > -1 && y <= max;
    }

    public void applySteps(Steps steps) {
        x += steps.getOnX();
        y += steps.getOnY();
    }

    public Coords clone() {
        if (exists)
            return new Coords(x, y);
        else
            return new Coords();
    }
}
