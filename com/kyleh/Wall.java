package com.kyleh;

import java.awt.*;
import java.awt.image.PackedColorModel;
import java.util.LinkedList;

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

    public Wall(int maxX, int maxY, int squareSize) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.squareSize = squareSize;

        //create and fill wallSquares with zeros
        //a value of zero means the square does not contain a wall segment
        //a value of 1 means the square contains a wall segment
        //there are no walls in stage one
        wallSquares = new int[maxX][maxY];
        fillWallSquaresWithZeros();

    }
    //A zero means there is no wall segment in the square
    private void fillWallSquaresWithZeros() {
        for (int x = 0; x < this.maxX; x++){
            for (int y = 0 ; y < this.maxY ; y++) {
                wallSquares[x][y] = 0;
            }
        }
    }

    /**
     * These next few methods are used by DrawSnakeGamePanel to draw
     * each of the wall or maze segments as needed. Each method will return
     * a list of coordinates for a particular section of wall. They will also
     * add a value to wallSquares to indicate a wall segment is in that square.
     * @return a LinkedList of wall segments
     */

    //returns the coordinates for the north boundary
    public LinkedList<Point> northWallSegmentsToDraw() {
        LinkedList<Point> northWallSegmentCoordinates = new LinkedList<Point>();
        for (int x = 0; x < maxX; x++) {
            int y = 0;
            wallSquares[x][y] = 1;
            Point point = new Point(x*squareSize,y*squareSize);
            northWallSegmentCoordinates.add(point);

        }
        return northWallSegmentCoordinates;

    }
    //returns the coordinates for the south boundary
    public LinkedList<Point> southWallSegmentsToDraw() {
        LinkedList<Point> southWallSegmentCoordinates = new LinkedList<Point>();
        for (int x = 0; x < maxX; x++) {
            int y = maxY-1;
            wallSquares[x][y] = 1;
            Point point = new Point(x*squareSize, y*squareSize);
            southWallSegmentCoordinates.add(point);
        }
        return southWallSegmentCoordinates;
    }

    //returns the coordinates for the east boundary
    public LinkedList<Point> eastWallSegmentsToDraw() {
        LinkedList<Point> eastWallSegmentCoordinates = new LinkedList<Point>();
        for (int y = 0; y < maxY; y++) {
            int x = 0;
            wallSquares[x][y] = 1;
            Point point = new Point(x*squareSize, y*squareSize);
            eastWallSegmentCoordinates.add(point);
        }
        return eastWallSegmentCoordinates;
    }

    //returns the coordinates for the west boundary
    public LinkedList<Point> westWallSegmentsToDraw() {
        LinkedList<Point> westWallSegmentCoordinates = new LinkedList<Point>();
        for (int y = 0; y < maxY; y++) {
            int x = maxX - 1;
            wallSquares[x][y] = 1;
            Point point = new Point(x*squareSize, y*squareSize);
            westWallSegmentCoordinates.add(point);
        }
        return westWallSegmentCoordinates;
    }

    //returns the coordinates for the level 3 north spoke
    public LinkedList<Point> northSpokeSegmentsToDraw() {
        LinkedList<Point> northSpokeSegmentCoordinates = new LinkedList<Point>();
        for (int y = ((maxY/2) - 5) ; y >= 3 ; y--) {
            int x = (int) maxX/2; // this centers the spoke
            wallSquares[x][y] = 1;
            Point point = new Point(x*squareSize, y*squareSize);
            northSpokeSegmentCoordinates.add(point);
        }
        return northSpokeSegmentCoordinates;
    }

    //returns the coordinates for the level 3 south spoke
    public LinkedList<Point> southSpokeSegmentsToDraw() {
        LinkedList<Point> southSpokeSegmentCoordinates = new LinkedList<Point>();
        for (int y = ((maxY/2) + 5); y <= maxY-3; y++) {
            int x = (int) maxX/2; //this centers the spoke
            wallSquares[x][y] = 1;
            Point point = new Point(x*squareSize, y*squareSize);
            southSpokeSegmentCoordinates.add(point);
        }
        return  southSpokeSegmentCoordinates;
    }

    //returns the coordinates for the level 3 east spoke
    public LinkedList<Point> eastSpokeSegmentsToDraw() {
        LinkedList<Point> eastSpokeSegmentCoordinates = new LinkedList<Point>();
        for (int x = ((maxX/2) - 5); x >= 3; x-- ) {
            int y = (int) maxY/2;
            wallSquares[x][y] = 1;
            Point point = new Point(x*squareSize, y*squareSize);
            eastSpokeSegmentCoordinates.add(point);
        }
        return eastSpokeSegmentCoordinates;
    }

    //returns the coordinates for the level 3 west spoke
    public LinkedList<Point> westSpokeSegmentsToDraw() {
        LinkedList<Point> westSpokeSegmentCoordinates = new LinkedList<Point>();
        for (int x =((maxX/2) + 5); x <= maxX-3; x++) {
            int y = (int) maxY/2;
            wallSquares[x][y] = 1;
            Point point = new Point(x*squareSize, y*squareSize);
            westSpokeSegmentCoordinates.add(point);
        }
        return westSpokeSegmentCoordinates;
    }

    //returns the coordinates for the northwest cross
    public LinkedList<Point> nwCrossSegmentsToDraw() {
        LinkedList<Point> nwCrossSegmentCoordinates = new LinkedList<Point>();
        for (int x = 3; x <= 5; x++) {
            int y = 5;
            wallSquares[x][y] = 1;
            Point point = new Point(x*squareSize, y*squareSize);
            nwCrossSegmentCoordinates.add(point);
        }

        for (int y = 4; y <=6; y++) {
            int x = 4;
            wallSquares[x][y] = 1;
            Point point = new Point(x*squareSize, y*squareSize);
            nwCrossSegmentCoordinates.add(point);
        }
        return nwCrossSegmentCoordinates;
    }

    //returns the coordinates for the center cross
    public LinkedList<Point> cCrossSegmentsToDraw() {
        int screenXCenter = (int) maxX/2;  //Cast just in case we have an odd number
        int screenYCenter = (int) maxY/2;  //Cast just in case we have an odd number
        LinkedList<Point> cCrossSegmentCoordinates = new LinkedList<Point>();

        for (int x = screenXCenter - 1; x <= screenXCenter + 1; x++) {
            wallSquares[x][screenYCenter] = 1;
            Point point = new Point(x*squareSize, screenYCenter*squareSize);
            cCrossSegmentCoordinates.add(point);
        }

        for (int y = screenYCenter - 1; y <= screenYCenter + 1; y++) {
            wallSquares[screenXCenter][y] = 1;
            Point point = new Point(screenXCenter*squareSize,y*squareSize);
            cCrossSegmentCoordinates.add(point);
        }
        return cCrossSegmentCoordinates;
    }

    //returns the coordinates for the southeast cross
    public LinkedList<Point> seCrossSegmentsToDraw() {
        LinkedList<Point> seCrossSegmentCoordinates = new LinkedList<Point>();
        for (int x = maxX-3; x >= maxX -5; x--) {
            int y = maxY -5;
            wallSquares[x][y] = 1;
            Point point = new Point(x*squareSize, y*squareSize);
            seCrossSegmentCoordinates.add(point);
        }

        for (int y =maxY - 4; y >= maxY -6; y--) {
            int x = maxY -4;
            wallSquares[x][y] = 1;
            Point point = new Point(x*squareSize, y*squareSize);
            seCrossSegmentCoordinates.add(point);
        }
        return seCrossSegmentCoordinates;
    }

    public boolean isWallSegment(int kibbleX, int kibbleY) {
        if (wallSquares[kibbleX][kibbleY] == 0) {
            return false;
        }
        return true;
    }

    public boolean snakeHasHitTheMaze(int snakeHeadX, int snakeHeadY) {
        if (wallSquares[snakeHeadX][snakeHeadY] == 0) {
            return false;
        }
        return true;
    }

    public void reset() {
        fillWallSquaresWithZeros();
    }

    public int[][] getWallSquares() {
        return wallSquares;
    }

    public int getWallX() {
        return wallX;
    }

    public int getWallY() {
        return wallY;
    }
}
