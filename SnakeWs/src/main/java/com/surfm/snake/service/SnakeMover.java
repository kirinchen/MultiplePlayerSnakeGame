package com.surfm.snake.service;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.surfm.snake.config.GameSetting;
import com.surfm.snake.model.Body;
import com.surfm.snake.model.Direction;
import com.surfm.snake.model.GameDataStore;
import com.surfm.snake.model.Snake;
import com.surfm.snake.model.Snake.Status;

@Service
@Scope("prototype")
public class SnakeMover {
	
	private Snake snake = null;
	private SnakeMoveHandle snakeMoveHandle;
	
	public void init(Snake snake,SnakeMoveHandle snakeMoveHandle){
		this.snake = snake;
		this.snakeMoveHandle = snakeMoveHandle;
	}

	public void move(){
		if(snake.getStatus() != Status.DIE){
			moveBody();
			moveHead();
		}
	}

	private void moveBody() {
		List<Body> bs = snake.getBodys() ;
		for(int i=bs.size()-1; i>0 ;i--){
			Body b = bs.get(i);
			Body beforeB = bs.get(i-1);
			b.setX(beforeB.getX());
			b.setY(beforeB.getY());
		}
	}

	private void moveHead() {
		Body body = snake.getBodys().get(0);
		Body oldBody = new Body(body.getX(),body.getY());
		try {
			moveHead(snake.getDirection(), body);
			setupHitWall(body,oldBody);
			checkEatEgg(body);
		} catch (HitWallException e) {
		}
	}
	
	private void setupHitWall(Body body, Body oldBody) throws HitWallException {
		if(isHitWall(body)){
			if(snake.getBodys().size() <= GameSetting.DEDUCTION_POINT){
				snake.getBodys().clear();
				snake.setStatus(Status.DIE);
			}else{
				cutBody();
				body.setX(oldBody.getX());
				body.setY(oldBody.getY());
			}
			throw new HitWallException();
		}
	}
	
	private void cutBody() {
		for(int i=0;i<GameSetting.DEDUCTION_POINT;i++){
			int size = snake.getBodys().size();
			snake.getBodys().remove(size-1);
		}
	}

	private boolean isHitWall(Body body){
		HashMap<Principal, Snake> player = GameDataStore.getInstance().getPlayer();
		for(Principal p : player.keySet()){
			Snake s = player.get(p);
			if(isHitSomeSnake(s, body)){
				return true;
			}
				
		}
		return false;
	}
	
	private boolean isHitSomeSnake(Snake s,Body body){
		for(int i=0;i<s.getBodys().size();i++){
			if(!s.getUserName().equals(snake.getUserName()) || i != 0){
				if(s.getBodys().get(i).equals(body)){
					return true;
				}
			}
		}
		return false;
	}

	private void checkEatEgg(Body body) {
		if(snakeMoveHandle.hasEatEgg(body)){
			int x = body.getX();
			int y = body.getY();
			addBody(x,y);
			snakeMoveHandle.removeEgg(x,y);
		}
	}

	private void addBody(int x, int y) {
		Body b = new Body(x, y);
		snake.getBodys().add(b);
	}

	private void moveHead(Direction d,Body b){
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

	private void moveRight(Body b) {
		b.setX(b.getX() +1);
		if(b.getX() >= GameSetting.MAX_WIDTH){
			b.setX(0);
		}
	}
	
	private void moveLeft(Body b){
		b.setX(b.getX() -1);
		if(b.getX() <0 ){
			b.setX(GameSetting.MAX_WIDTH -1);
		}
	}
	
	private void moveDown(Body b){
		b.setY(b.getY()+1);
		if(b.getY() >= GameSetting.MAX_HEIGHT){
			b.setY(0);
		}
	}
	
	private void moveUp(Body b){
		b.setY(b.getY()-1);
		if(b.getY() < 0) {
			b.setY(GameSetting.MAX_HEIGHT -1);
		}
	}
	
	class HitWallException extends Exception{
		
	}
	
}
