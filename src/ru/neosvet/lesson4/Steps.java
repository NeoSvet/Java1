package ru.neosvet.lesson4;

public class Steps {
    public enum Direction {UP, DOWN, NONE}

    private Direction onX, onY;

    public Steps(Direction onX, Direction onY) {
        this.onX = onX;
        this.onY = onY;
    }

    public int getOnX() {
        if (onX == Direction.UP)
            return 1;
        if (onX == Direction.DOWN)
            return -1;
        return 0;
    }

    public int getOnY() {
        if (onY == Direction.UP)
            return 1;
        if (onY == Direction.DOWN)
            return -1;
        return 0;
    }

    public void invert() {
        onX = invert(onX);
        onY = invert(onY);
    }

    private Direction invert(Direction direction) {
        if (direction == Direction.UP)
            return Direction.DOWN;
        if (direction == Direction.DOWN)
            return Direction.UP;
        return direction;
    }
}
