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
		int level = SnakeGame.getGameLevel();

		switch (stage) {
			case SnakeGame.BEFORE_GAME: {
				//don't do anything, waiting for user to press a key to start
				break;
			}
			case SnakeGame.DURING_GAME: {
				//
				snake.moveSnake();
				if (snake.didEatKibble(kibble) == true) {		
					//tell kibble to update
					kibble.moveKibble(snake, wall);
					Score.increaseScore();
				}
				if (score.getScore() < 2) {
					SnakeGame.setGameLevel(SnakeGame.LEVEL_ONE);
				}else if (score.getScore() >= 2 && score.getScore() < 4) {
					SnakeGame.setGameLevel(SnakeGame.LEVEL_TWO);
					while (wall.isWallSegment(kibble.getKibbleX(),kibble.getKibbleY())) {
						kibble.moveKibble(snake, wall);
					}
				}else if (score.getScore() >=4 && score.getScore() < 6) {
					SnakeGame.setGameLevel(SnakeGame.LEVEL_THREE);
					while (wall.isWallSegment(kibble.getKibbleX(),kibble.getKibbleY())) {
						kibble.moveKibble(snake, wall);
					}

				}else {
					SnakeGame.setGameLevel(SnakeGame.LEVEL_FOUR);
					while (wall.isWallSegment(kibble.getKibbleX(),kibble.getKibbleY())) {
						kibble.moveKibble(snake, wall);
					}

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
