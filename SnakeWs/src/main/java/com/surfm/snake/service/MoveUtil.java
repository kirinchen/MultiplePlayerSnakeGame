package com.surfm.snake.service;

import static com.surfm.snake.service.MoveUtil.moveDown;
import static com.surfm.snake.service.MoveUtil.moveLeft;
import static com.surfm.snake.service.MoveUtil.moveRight;
import static com.surfm.snake.service.MoveUtil.moveUp;

import com.surfm.snake.config.GameSetting;
import com.surfm.snake.model.Body;
import com.surfm.snake.model.Direction;

public class MoveUtil {
	
	static void moveBody(Direction d,Body b){
		switch (d) {
		case RIGHT:
			moveRight(b);
			break;
		case LEFT:
			moveLeft(b);
			break;
		case DOWN:
			moveDown(b);
			break;
		case UP:
			moveUp(b);
			break;
		}
	}
	
	static void moveRight(Body b) {
		b.setX(b.getX() +1);
		if(b.getX() >= GameSetting.MAX_WIDTH){
			b.setX(0);
		}
	}
	
	static void moveLeft(Body b){
		b.setX(b.getX() -1);
		if(b.getX() <0 ){
			b.setX(GameSetting.MAX_WIDTH -1);
		}
	}
	
	static void moveDown(Body b){
		b.setY(b.getY()+1);
		if(b.getY() >= GameSetting.MAX_HEIGHT){
			b.setY(0);
		}
	}
	
	static void moveUp(Body b){
		b.setY(b.getY()-1);
		if(b.getY() < 0) {
			b.setY(GameSetting.MAX_HEIGHT -1);
		}
	}

}
