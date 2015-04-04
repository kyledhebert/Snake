package com.kyleh;

/**
 * Created by kylehebert on 3/23/15.
 * Objects of this class will represent walls in the
 * game area that the snake cannot pass through.
 */
public class Wall {
    //these represent the positions on the grid where the wall can appear
    private int wallX;
    private int wallY;
    private int gameLevel, maxX, maxY, squareSize;


    /**
     * This represents all of the possible squares the wall can occupy
     * A zero means there is no wall in that square, a 1 means a wall piece
     * is present in that square
     */
    private int wallSquares[][];


    /**
     * Wall needs to know what level the player is on, there are
     * four different wall layouts, based on the level, which is
     * determined by the current score
     */

    public Wall(int gameLevel, int maxX, int maxY, int squareSize) {
        this.gameLevel = gameLevel;
        this.maxX = maxX;
        this.maxY = maxY;
        this.squareSize = squareSize;

        //create and fill wallSquares with zeros
        //there are no walls in stage one
        wallSquares = new int[maxX][maxY];
        fillWallSquaresWithZeros();

    }

    private void fillWallSquaresWithZeros() {
        for (int x = 0; x < this.maxX; x++){
            for (int y = 0 ; y < this.maxY ; y++) {
                wallSquares[x][y] = 0;
            }
        }
    }

    

    public int getWallX() {
        return wallX;
    }

    public int getWallY() {
        return wallY;
    }
}
