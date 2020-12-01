package ru.neosvet.lesson4;

public class Steps {
    private Direction onX, onY;

    public Steps(Direction onX, Direction onY) {
        this.onX = onX;
        this.onY = onY;
    }

    public int getOnX() {
        return onX.getValue();
    }

    public int getOnY() {
        return onY.getValue();
    }

    public void invert() {
        onX = onX.invert();
        onY = onY.invert();
    }
}
