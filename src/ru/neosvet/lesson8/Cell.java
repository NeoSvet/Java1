package ru.neosvet.lesson8;

import ru.neosvet.lesson4.Coords;

import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {
    private final Coords coords;

    public Cell(int x, int y, Font font) {
        super();
        this.setFont(font);
        coords = new Coords(x, y);
    }

    public Coords getCoords() {
        return coords;
    }
}
