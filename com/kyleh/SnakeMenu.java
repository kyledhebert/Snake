package com.kyleh;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kylehebert on 4/13/15.
 * This class is responsible for showing the menu before the
 * game begins. The menu allows the player to select game settings.
 */
public class SnakeMenu extends JFrame {
    private JPanel rootPanel;
    private JLabel snakelogo;


    private JLabel selectGameTypeLabel;
    private JRadioButton freeplayRadioButton;
    private JRadioButton progressModeRadioButton;
    private JLabel rulesLabel;
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
        super ("Snake 2");
        setContentPane(rootPanel);
        pack();
        setSize(501,501);
        //setUndecorated(true);
        //setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


        freeplayRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameType = FREEPLAY;
                rulesLabel.setText("Eat kibble and make your snake as large as possible");


            }
        });
        progressModeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameType = PROGRESS_MODE;
                rulesLabel.setText("Score points to advance to the next level. Adds obstacles and different food types.");
            }
        });


        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SnakeGame snakeGame = new SnakeGame(gameType,snakeSpeed);
            }
        });

        quitSnakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        snailRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                snakeSpeed = SNAIL;

            }
        });
        snakeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                snakeSpeed = SNAKE;
            }
        });
        rabbitRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                snakeSpeed = RABBIT;
            }
        });
    }
}
