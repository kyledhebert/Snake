package com.kyleh;

import java.util.Random;

/**
 * Created by kylehebert on 3/23/15.
 * Objects of this class will represent walls in the
 * game area that the snake cannot pass through.
 */
public class Wall {
    //these represent the positions on the grid where the wall can appear
    private int wallX;
    private int wallY;

    /**
     * Wall needs to know where the snake is so the game doesn't
     * place a piece of wall in the snake. Pick a random coordinate
     * to place the wall, if the snake is there try again, else
     * place the wall
      * @param snake
     */
   public Wall(Snake snake) {
        moveWall(snake);

   }

   protected void moveWall(Snake snake) {
       Random random = new Random();
       boolean snakeIsHere = true;
       while (snakeIsHere == true) {
           wallX = random.nextInt(SnakeGame.xSquares);
           wallY = random.nextInt(SnakeGame.ySquares);
           snakeIsHere = snake.isSnakeSegment(wallX,wallY);
       }

   }

    public int getWallX() {
        return wallX;
    }

    public int getWallY() {
        return wallY;
    }
}
