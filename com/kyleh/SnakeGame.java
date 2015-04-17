package com.kyleh;

import java.util.Timer;

import javax.swing.*;


public class SnakeGame {

	public final static int xPixelMaxDimension = 501;  //Pixels in window. 501 to have 50-pixel squares plus 1 to draw a border on last square
	public final static int yPixelMaxDimension = 501;

	public static int xSquares ;
	public static int ySquares ;

	public static int squareSize = 25;

	protected static Snake snake ;

	protected static Kibble kibble;

	protected static Score score;

	protected static Wall wall;

	//options selected by the player
	protected static int gameType;
	protected static int snakeSpeed;

	public static boolean warpWallsActive =true; //by default these are on for both game types


	//The levels are used to tell the game which version of the walls to draw
	//There are different wall layouts based on the level
	//Level is determined by current score
	static final int LEVEL_ONE = 1;
	static final int LEVEL_TWO = 2;
	static final int LEVEL_THREE = 3;
	static final int LEVEL_FOUR = 4;

	private static int gameLevel = LEVEL_ONE;

	static final int BEFORE_GAME = 1;
	static final int DURING_GAME = 2;
	static final int GAME_OVER = 3;
	static final int GAME_WON = 4;   //The values are not important. The important thing is to use the constants 
	//instead of the values so you are clear what you are setting. Easy to forget what number is Game over vs. game won
	//Using constant names instead makes it easier to keep it straight. Refer to these variables 
	//using statements such as SnakeGame.GAME_OVER 

	private static int gameStage = BEFORE_GAME;  //use this to figure out what should be happening. 
	//Other classes like Snake and DrawSnakeGamePanel will need to query this, and change it's value

	protected static long clockInterval = 250; //controls game speed
	//Every time the clock ticks, the snake moves
	//This is the time between clock ticks, in milliseconds
	//1000 milliseconds = 1  second.

	static JFrame snakeFrame;
	static DrawSnakeGamePanel snakePanel;
	static SnakeMenu snakeMenu;
	static GameOver gameOver;
	static GameWon gameWon;


	//Framework for this class adapted from the Java Swing Tutorial, FrameDemo and Custom Painting Demo. You should find them useful too.
	//http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/uiswing/examples/components/FrameDemoProject/src/components/FrameDemo.java
	//http://docs.oracle.com/javase/tutorial/uiswing/painting/step2.html

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				snakeGameStart();
				initializeGame();
				showSnakeMenu();

			}
		});

	}

	private static void snakeGameStart() {
		//Create and set up the window.
		snakeFrame = new JFrame();
		snakeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		snakeFrame.setSize(xPixelMaxDimension, yPixelMaxDimension);
		snakeFrame.setUndecorated(true); //hide title bar
		snakeFrame.setVisible(true);
		snakeFrame.setResizable(false);

		xSquares = xPixelMaxDimension / squareSize;
		ySquares = yPixelMaxDimension / squareSize;

		snakeMenu = new SnakeMenu();
	}


	private static void initializeGame() {
		//set up score, snake and first kibble and first maze stage
		//note: the first stage actually has no maze
		wall = new Wall(xSquares, ySquares, squareSize);
		snake = new Snake(xSquares, ySquares, squareSize, wall);
		kibble = new Kibble(snake,wall);
		score = new Score();

		gameStage = BEFORE_GAME;
	}

	protected static void resetGame() {
		snake.reset();
		wall.reset();
		Score.resetScore();
		snake.createStartSnake();

	}

	protected static void showSnakeMenu() {
		//displays the options menu to the player
		//at the beginning of game, and after a the game ends

		//if this method is called after Game Over
		//remove the game over panel
		if (gameStage == GAME_OVER) {
			snakeFrame.remove(gameOver.rootPanel);
			gameStage = BEFORE_GAME;
		}

		//if called after Game Won
		//remove the game won panel
		if (gameStage == GAME_WON) {
			snakeFrame.remove(gameWon.rootPanel);
			gameStage= BEFORE_GAME;
		}
		snakeFrame.add(snakeMenu.rootPanel);
		snakeFrame.repaint();
	}


	protected static void createAndShowGUI() {
		snakeFrame.remove(snakeMenu.rootPanel);
		snakePanel = new DrawSnakeGamePanel(snake, kibble, score, wall);
		snakeFrame.add(snakePanel);
		snakePanel.setFocusable(true);
		snakePanel.requestFocusInWindow(); //required to give this component the focus so it can generate KeyEvents

		snakeFrame.add(snakePanel);
		snakePanel.addKeyListener(new GameControls(snake, wall));

		setGameStage(BEFORE_GAME);

		snakeFrame.setVisible(true);
	}

	protected static void showGameWon() {
		snakeFrame.remove(snakePanel);
		gameWon = new GameWon();
		snakeFrame.add(gameWon.rootPanel);
		snakeFrame.validate();
		snakeFrame.repaint();



	}

	protected static void showGameOver() {
		snakeFrame.remove(snakePanel);
		gameOver = new GameOver();
		gameOver.displayScores();
		snakeFrame.add(gameOver.rootPanel);
		snakeFrame.validate();
		snakeFrame.repaint();

	}





	protected static void newGame() {
		Timer timer = new Timer();
		GameClock clockTick = new GameClock(snake, kibble, score, wall, snakePanel);
		if (snakeSpeed == 1)
			clockInterval = 500;
		else if (snakeSpeed == 2)
			clockInterval = 250;
		else if (snakeSpeed == 3)
			clockInterval = 125;
		timer.scheduleAtFixedRate(clockTick, 0, clockInterval);
	}


	public static int getGameStage() {
		return gameStage;
	}


	public static int getGameLevel() {
		return gameLevel;
	}

	public static boolean gameEnded() {
		if (gameStage == GAME_OVER || gameStage == GAME_WON){
			return true;
		}
		return false;
	}


	public static void setGameLevel(int gameLevel) {
		SnakeGame.gameLevel = gameLevel;
	}

	public static void setGameStage(int gameStage) {
		SnakeGame.gameStage = gameStage;
	}

	public static boolean gameTypeIsClassic() {
		if (gameType == 1) {
			return true;
		}
		return false;
	}

	//setters for options decided on SnakeMenu
	public static void setGameType(int gameType) {
		SnakeGame.gameType = gameType;
	}

	public static void setSnakeSpeed(int snakeSpeed) {
		SnakeGame.snakeSpeed = snakeSpeed;
	}
}

