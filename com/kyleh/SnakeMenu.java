package com.kyleh;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kylehebert on 4/13/15.
 * This class is responsible for showing the menu before the
 * game begins. The menu allows the player to select game settings.
 */
public class SnakeMenu {
    public JPanel rootPanel;
    private JLabel snakelogo;


    private JLabel selectGameTypeLabel;
    private JRadioButton freeplayRadioButton;
    private JRadioButton progressModeRadioButton;

    static final  int FREEPLAY = 1;
    static final int PROGRESS_MODE = 2;
    private  int gameType = FREEPLAY;

    private JLabel selectSpeedLabel;
    private JRadioButton snailRadioButton;
    private JRadioButton snakeRadioButton;
    private JRadioButton rabbitRadioButton;
    static final int SNAIL = 1;
    static final int SNAKE = 2;
    static final int RABBIT = 3;
    private int snakeSpeed = SNAKE;

    private JButton startGameButton;
    private JButton quitSnakeButton;


    public SnakeMenu() {



        freeplayRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SnakeGame.setGameType(FREEPLAY);


            }
        });
        progressModeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SnakeGame.setGameType(PROGRESS_MODE);
            }
        });

        snailRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SnakeGame.setSnakeSpeed(SNAIL);

            }
        });
        snakeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SnakeGame.setSnakeSpeed(SNAKE);
            }
        });
        rabbitRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SnakeGame.setSnakeSpeed(RABBIT);
            }
        });



        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SnakeGame.createAndShowGUI();


            }
        });

        quitSnakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
