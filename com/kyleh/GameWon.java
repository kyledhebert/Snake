package com.kyleh;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kylehebert on 4/17/15.
 */
public class GameWon {
    private JButton menuButton;
    private JButton quitButton;
    public JPanel rootPanel;

    public GameWon() {
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SnakeGame.resetGame();
                SnakeGame.showSnakeMenu();

            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
