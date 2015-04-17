package com.kyleh;

import java.util.Random;

/**
 * Created by kylehebert on 4/17/15.
 * Objects of this class represent prey that the snake can eat. Prey
 * will only appear for a few seconds and are worth double
 * the point value of kibble.
 */
public class Prey {

    //the coordinates where prey will appear
    private int preyX;
    private int preyY;

    //Prey needs to know where the Snake and Walls are
    //so the game does not place Prey inside of them

    public Prey(Snake s, Wall w) {

        movePrey(s,w);
    }

    protected void movePrey(Snake s, Wall w) {
        //this will pick a random location for Prey to appear,
        //if the snake or walls occupy that location, it will try
        //again until it finds an available spot
        Random random = new Random();
        boolean preyInSnake = true;
        boolean preyInWall= true;
        while (preyInSnake || preyInWall) {
            preyX = random.nextInt(SnakeGame.xSquares);
            preyY = random.nextInt(SnakeGame.ySquares);
            preyInSnake = s.isSnakeSegment(preyX, preyY);
            preyInWall = w.isWallSegment(preyX,preyY);

        }
    }

    public int getPreyX() {
        return preyX;
    }

    public int getPreyY() {
        return preyY;
    }
}
