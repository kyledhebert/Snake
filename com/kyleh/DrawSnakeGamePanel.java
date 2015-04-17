package com.kyleh;

import java.awt.*;
import java.awt.image.PackedColorModel;
import java.util.LinkedList;

import javax.swing.JPanel;

/** This class responsible for displaying the graphics, so the snake, grid, kibble, instruction text and high score
 * 
 * @author Clara
 *
 */
public class DrawSnakeGamePanel extends JPanel {
	
	private static int gameStage = SnakeGame.BEFORE_GAME;  //use this to figure out what to paint

	private static int gameLevel = SnakeGame.LEVEL_ONE;
	
	private Snake snake;
	private Kibble kibble;
	private Score score;
    private Wall wall;


	DrawSnakeGamePanel(Snake s, Kibble k, Score sc, Wall w){
		this.snake = s;
		this.kibble = k;
		this.score = sc;
        this.wall = w;
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(SnakeGame.xPixelMaxDimension, SnakeGame.yPixelMaxDimension);
    }

    public void paintComponent(Graphics g) {

		super.paintComponent(g);
		setBackground(new Color(171,182,48));

        /* Where are we at in the game? 4 phases.. 
         * 1. Before game starts
         * 2. During game
         * 3. Game lost aka game over
         * 4. or, game won
         */

        gameStage = SnakeGame.getGameStage();
        
        switch (gameStage) {
        case SnakeGame.BEFORE_GAME: {
        	displayInstructions(g);
        	break;
        } 
        case SnakeGame.DURING_GAME : {
        	displayGame(g);
			if (!SnakeGame.gameTypeIsClassic()) {
				displayScore(g);
			}
        	break;
        }
        case SnakeGame.GAME_OVER: {
        	SnakeGame.showGameOver();
        	break;
        }
        case SnakeGame.GAME_WON: {
        	SnakeGame.showGameWon();
        	break;
        }
        }
        
        
        
    }

	private void displayGameWon(Graphics g) {
		// TODO Replace this with something really special!
		g.clearRect(100, 100, 350, 350);
		g.drawString("YOU WON SNAKE!!!", 150, 150);
		
	}

	private void displayScore(Graphics g) {
		int fontSize = 30;
		g.setFont(new Font("TimesRoman", Font.PLAIN,fontSize));
		String textScore = score.getStringScore();
		g.drawString(textScore,440,50);
	}
	private void displayGameOver(Graphics g) {

		g.clearRect(100,100,350,350);
		g.drawString("GAME OVER", 150, 150);
		
		String textScore = score.getStringScore();
		String textHighScore = score.getStringHighScore();
		String newHighScore = score.newHighScore();
		
		g.drawString("SCORE = " + textScore, 150, 250);
		
		g.drawString("HIGH SCORE = " + textHighScore, 150, 300);
		g.drawString(newHighScore, 150, 400);
		
		g.drawString("press a key to play again", 150, 350);
		g.drawString("Press q to quit the game",150,400);		
    			
	}

	private void displayGame(Graphics g) {
		//displayGameGrid(g);
		displaySnake(g);
		displayKibble(g);
        displayWalls(g);
	}

	private void displayGameGrid(Graphics g) {

		int maxX = SnakeGame.xPixelMaxDimension;
		int maxY= SnakeGame.yPixelMaxDimension;
		int squareSize = SnakeGame.squareSize;
		
		g.clearRect(0, 0, maxX, maxY);

		g.setColor(Color.RED);

		//Draw grid - horizontal lines
		for (int y=0; y <= maxY ; y+= squareSize){			
			g.drawLine(0, y, maxX, y);
		}
		//Draw grid - vertical lines
		for (int x=0; x <= maxX ; x+= squareSize){			
			g.drawLine(x, 0, x, maxY);
		}
	}

    private void displayWalls(Graphics g) {

		gameLevel = SnakeGame.getGameLevel();


        g.setColor(new Color(64,64,64));

		switch (gameLevel) {

			case 1:
				break;
			case 2:
				displayLevelTwoMaze(g);
				break;

			case 3:
				displayLevelTwoMaze(g);
				displayLevelThreeMaze(g);
				break;

			case 4:
				displayLevelTwoMaze(g);
				displayLevelThreeMaze(g);
				displayLevelFourMaze(g);
				break;


		}
	}

	private void displayKibble(Graphics g) {

		//Draw the kibble in green
		g.setColor(Color.GREEN);

		int x = kibble.getKibbleX() * SnakeGame.squareSize;
		int y = kibble.getKibbleY() * SnakeGame.squareSize;

		g.fillRect(x + 1, y + 1, SnakeGame.squareSize - 2, SnakeGame.squareSize - 2);
		
	}

	private void displaySnake(Graphics g) {

		LinkedList<Point> coordinates = snake.segmentsToDraw();
		
		//Draw head
		g.setColor(Color.LIGHT_GRAY);
		Point head = coordinates.pop();
		g.fillRect((int)head.getX(), (int)head.getY(), SnakeGame.squareSize, SnakeGame.squareSize);
		
		//Draw rest of snake
		g.setColor(new Color(32,139,50));
		for (Point p : coordinates) {
			g.fillRect((int)p.getX(), (int)p.getY(), SnakeGame.squareSize, SnakeGame.squareSize );
		}

	}

	private void displayInstructions(Graphics g) {
        g.drawString("Press any key to begin!", 100, 200);
        g.drawString("Press q to quit the game", 100, 300);
	}

	private void displayLevelTwoMaze(Graphics g) {
		//boundary walls for stage two
		LinkedList<Point> northWall = wall.northWallSegmentsToDraw();
		LinkedList<Point> southWall = wall.southWallSegmentsToDraw();
		LinkedList<Point> eastWall = wall.eastWallSegmentsToDraw();
		LinkedList<Point> westWall = wall.westWallSegmentsToDraw();

		for (Point p : northWall) {
			g.fillRect((int) p.getX(), (int) p.getY(), SnakeGame.squareSize, SnakeGame.squareSize);
		}

		for (Point p : southWall) {
			g.fillRect((int) p.getX(), (int) p.getY(), SnakeGame.squareSize, SnakeGame.squareSize);
		}

		for (Point p : eastWall) {
			g.fillRect((int) p.getX(), (int) p.getY(), SnakeGame.squareSize, SnakeGame.squareSize);
		}

		for (Point p : westWall) {
			g.fillRect((int) p.getX(), (int) p.getY(), SnakeGame.squareSize, SnakeGame.squareSize);
		}


	}

	private void displayLevelThreeMaze(Graphics g) {
		//additional spokes for stage three
		LinkedList<Point> northSpoke = wall.northSpokeSegmentsToDraw();
		LinkedList<Point> southSpoke = wall.southSpokeSegmentsToDraw();
		LinkedList<Point> eastSpoke = wall.eastSpokeSegmentsToDraw();
		LinkedList<Point> westSpoke = wall.westSpokeSegmentsToDraw();

		for (Point p : northSpoke) {
			g.fillRect((int) p.getX(), (int) p.getY(), SnakeGame.squareSize, SnakeGame.squareSize);
		}

		for (Point p : southSpoke) {
			g.fillRect((int) p.getX(), (int) p.getY(), SnakeGame.squareSize, SnakeGame.squareSize);
		}

		for (Point p : eastSpoke) {
			g.fillRect((int) p.getX(), (int) p.getY(), SnakeGame.squareSize, SnakeGame.squareSize);
		}

		for (Point p : westSpoke) {
			g.fillRect((int) p.getX(), (int) p.getY(), SnakeGame.squareSize, SnakeGame.squareSize);
		}

	}

	private void displayLevelFourMaze(Graphics g) {
		//final maze additions for stage 4
		LinkedList<Point> nwCross = wall.nwCrossSegmentsToDraw();
		LinkedList<Point> cCross = wall.cCrossSegmentsToDraw();
		LinkedList<Point> seCross = wall.seCrossSegmentsToDraw();

		for (Point p : nwCross) {
			g.fillRect((int) p.getX(), (int) p.getY(), SnakeGame.squareSize, SnakeGame.squareSize);
		}

		for (Point p : cCross) {
			g.fillRect((int) p.getX(), (int) p.getY(), SnakeGame.squareSize, SnakeGame.squareSize);
		}

		for (Point p : seCross) {
			g.fillRect((int) p.getX(), (int) p.getY(), SnakeGame.squareSize, SnakeGame.squareSize);
		}
	}


}

