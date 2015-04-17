package com.kyleh;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
    private  int gameType = 0;

    private JLabel selectSpeedLabel;
    private JRadioButton snailRadioButton;
    private JRadioButton snakeRadioButton;
    private JRadioButton rabbitRadioButton;
    static final int SNAIL = 1;
    static final int SNAKE = 2;
    static final int RABBIT = 3;
    private int snakeSpeed = 0;

    private JButton startGameButton;
    private JButton quitSnakeButton;
    private JTextField rulesTextField;


    public SnakeMenu() {



        freeplayRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (freeplayRadioButton.isSelected()) {
                    gameType = FREEPLAY; //used for selection validation
                    rulesTextField.setText("Eat Kibble until the snake fills the screen");
                    SnakeGame.setGameType(FREEPLAY);
                }

            }
        });
        progressModeRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (progressModeRadioButton.isSelected()) {
                    gameType = PROGRESS_MODE;
                    rulesTextField.setText("Score 50 points while avoiding obstacles.");
                    SnakeGame.setGameType(PROGRESS_MODE);
                }
            }
        });
        snailRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (snailRadioButton.isSelected()) {
                    snakeSpeed = SNAIL;
                    SnakeGame.setSnakeSpeed(SNAIL);
                }
            }
        });
        snakeRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (snakeRadioButton.isSelected()) {
                    snakeSpeed = SNAKE;
                    SnakeGame.setSnakeSpeed(SNAKE);
                }
            }
        });
        rabbitRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (rabbitRadioButton.isSelected()) {
                    snakeSpeed = RABBIT;
                    SnakeGame.setSnakeSpeed(RABBIT);
                }
            }
        });

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameType == 0) {
                    JOptionPane.showMessageDialog(null, "Please select a game type.", "Game Type Error", JOptionPane.ERROR_MESSAGE);
                } else if (snakeSpeed == 0) {
                    JOptionPane.showMessageDialog(null, "Please select a snake speed.", "Snake Speed Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    SnakeGame.createAndShowGUI();
                }

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
