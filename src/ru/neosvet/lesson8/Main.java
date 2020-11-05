package ru.neosvet.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    public static void main(String[] args) {
        new Main();
    }

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
        JPanel panel = new JPanel();
        add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(5, 5));
        for (int i = 0; i < 25; i++)
            panel.add(createButton());
    }

    private void createMenu() {
        JMenuBar menu = new JMenuBar();
        JMenu game = new JMenu("Игра");
        JMenuItem first = new JMenuItem("Начать первым");
        first.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO start game (human as first)
            }
        });
        game.add(first);
        JMenuItem second = new JMenuItem("Начать вторым");
        second.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO start game (human as second)
            }
        });
        game.add(second);
        menu.add(game);
        add(menu, BorderLayout.PAGE_START);
    }

    private Component createButton() {
        JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO human turn
            }
        });
        return button;
    }
}
