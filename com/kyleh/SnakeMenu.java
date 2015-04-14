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
    static final  int FREEPLAY = 1;
    static final int PROGRESS_MODE = 2;
    private  int gameType = FREEPLAY;


    private JLabel selectPlayAreaSizeLabel;
    private JRadioButton smallRadioButton;
    private JRadioButton mediumRadioButton;
    private JRadioButton largeRadioButton;
    static final int SMALL_SQUARES = 12;
    static final int MEDIUM_SQUARES = 25;
    static final int LARGE_SQUARES = 50;
    private int squareSize = MEDIUM_SQUARES;


    private JButton startGameButton;




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

            }
        });
        progressModeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameType = PROGRESS_MODE;
            }
        });

        smallRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                squareSize = SMALL_SQUARES;

            }
        });
        largeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                squareSize = LARGE_SQUARES;
            }
        });
        mediumRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                squareSize = MEDIUM_SQUARES;
            }
        });

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SnakeGame snakeGame = new SnakeGame(gameType,squareSize);
            }
        });
    }
}
