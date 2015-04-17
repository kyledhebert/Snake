package com.kyleh;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kylehebert on 4/16/15.
 */
public class GameOver {
    public JPanel rootPanel;
    private JButton menuButton;
    private JButton quitButton;
    private JLabel yourScoreLabel;
    private JLabel highScoreLabel;


    public GameOver() {
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

    public void displayScores() {
        yourScoreLabel.setText("Your Score: " + SnakeGame.score.getStringScore());
        highScoreLabel.setText("High Score: " + SnakeGame.score.getStringHighScore());
    }
}
