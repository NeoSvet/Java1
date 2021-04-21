package ru.neosvet.lesson8;

import ru.neosvet.lesson4.Coords;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class Main extends JFrame implements ActionListener {
    public static void main(String[] args) {
        new Main();
    }

    private final int SIZE = 5;
    private TicTacToe game;
    private JPanel field;

    public Main() {
        setTitle("TicTacToe");
        setSize(500, 530);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createMenu();
        createField();

        setVisible(true);
    }

    private void createField() {
        field = new JPanel();
        add(field, BorderLayout.CENTER);
        field.setLayout(new GridLayout(SIZE, SIZE));
        Font font = new Font(field.getFont().getName(),
                field.getFont().getStyle(),
                field.getFont().getSize() + 60);

        Cell cell;
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                cell = new Cell(x, y, font);
                cell.addActionListener(this);
                field.add(cell);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (game == null || game.isFinish()) {
            showMessageDialog(this, "Начните новую игру!");
            return;
        }
        Cell cell = (Cell) e.getSource();
        if (game.isBusyCell(cell.getCoords()))
            return;
        game.goHumanTurn(cell.getCoords());
        cell.setText(game.getText(true));
        if(game.isFinish()) {
            showFinishMessage();
        } else {
            goAiTurn();
        }
    }

    private void showFinishMessage() {
        showMessageDialog(this, game.getMsgFinish());
    }

    private void goAiTurn() {
        Coords coords = game.getCoordsAiTurn();
        Cell cell;
        for (int i = 0; i < field.getComponentCount(); i++) {
            cell = (Cell) field.getComponent(i);
            if(coords.getX() == cell.getCoords().getX() &&
                    coords.getY() == cell.getCoords().getY()) {
                cell.setText(game.getText(false));
                break;
            }
        }

        if(game.isFinish()) {
            showFinishMessage();
        }
    }

    private void createMenu() {
        JMenuBar menu = new JMenuBar();
        JMenu game = new JMenu("Игра");
        JMenuItem first = new JMenuItem("Начать первым");
        first.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(true);
            }
        });
        game.add(first);
        JMenuItem second = new JMenuItem("Начать вторым");
        second.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(false);
            }
        });
        game.add(second);
        menu.add(game);
        add(menu, BorderLayout.PAGE_START);
    }

    private void startGame(boolean human_first) {
        game = new TicTacToe(human_first);
        char[][] map = game.getMap();
        Cell cell;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                cell = (Cell) field.getComponent(i * SIZE + j);
                cell.setText(String.valueOf(map[i][j]));
            }
        }
    }
}
