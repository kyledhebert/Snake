package com.kyleh;

import java.util.TimerTask;

public class GameClock extends TimerTask {

	Snake snake;
	Kibble kibble;
	Score score;
	Wall wall;
	DrawSnakeGamePanel gamePanel;


		
	public GameClock(Snake snake, Kibble kibble, Score score, Wall wall, DrawSnakeGamePanel gamePanel){
		this.snake = snake;
		this.kibble = kibble;
		this.score = score;
		this.wall = wall;
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void run() {
		// This method will be called every clock tick
						
		int stage = SnakeGame.getGameStage();

		switch (stage) {
			case SnakeGame.BEFORE_GAME: {
				//don't do anything, waiting for user to press a key to start
				break;
			}
			case SnakeGame.DURING_GAME: {
				//
				snake.moveSnake();
				if (snake.wonClassicGame() || score.wonCampaign()) {
					SnakeGame.setGameStage(SnakeGame.GAME_WON);
				}

				if (snake.didEatKibble(kibble) == true) {		
					//tell kibble to update
					kibble.moveKibble(snake, wall);
					Score.increaseScore();
				}



				//if game mode is campaign, we need to change the level
				//based on score. Also need to make sure kibble isn't placed
				//within a wall once they appear, and reset the snake to starting
				//size and position
				if (!SnakeGame.gameTypeIsClassic()) {
					if (score.getScore() < 8) {
						SnakeGame.setGameLevel(SnakeGame.LEVEL_ONE);
					} else if (score.getScore() >= 8 && score.getScore() < 16) {
						SnakeGame.setGameLevel(SnakeGame.LEVEL_TWO);
					} else if (score.getScore() >= 16 && score.getScore() < 32) {
						SnakeGame.setGameLevel(SnakeGame.LEVEL_THREE);
					} else {
						SnakeGame.setGameLevel(SnakeGame.LEVEL_FOUR);
					}
				}
				while (wall.isWallSegment(kibble.getKibbleX(), kibble.getKibbleY())) {
					kibble.moveKibble(snake, wall);
				}

				break;
			}
			case SnakeGame.GAME_OVER: {
				this.cancel();		//Stop the Timer	
				break;	
			}
			case SnakeGame.GAME_WON: {
				this.cancel();   //stop timer
				break;
			}
			
		
		}
				
		gamePanel.repaint();		//In every circumstance, must update screen
		
	}
}
