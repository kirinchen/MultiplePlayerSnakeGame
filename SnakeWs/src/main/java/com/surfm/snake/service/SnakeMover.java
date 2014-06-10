package com.surfm.snake.service;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.surfm.snake.config.GameSetting;
import com.surfm.snake.model.Body;
import com.surfm.snake.model.GameDataStore;
import com.surfm.snake.model.Snake;
import com.surfm.snake.model.Snake.Status;

@Service
@Scope("prototype")
public class SnakeMover {

	private Snake snake = null;
	private SnakeMoveHandle snakeMoveHandle;

	public void init(Snake snake, SnakeMoveHandle snakeMoveHandle) {
		this.snake = snake;
		this.snakeMoveHandle = snakeMoveHandle;
	}

	public void move() {

		if (snake.getStatus() == Status.LIVE) {
			goToMove();
		} else if (snake.getStatus() == Status.HURT) {
			healHurt();
		}
	}

	private void goToMove() {
		if (isHited()) {
			setupHurt();
		} else {
			checkEatEgg();
			moveBody();
			moveHead();
		}
	}

	private boolean isHited() {
		Body b = new Body(snake.getBodys().get(0));
		MoveUtil.moveBody(snake.getDirection(), b);
		return isHitWall(b);
	}

	private void healHurt() {
		if (snake.getHurtWaitCount() > GameSetting.HURT_WAIT_COUNT) {
			snake.setStatus(Status.LIVE);
			snake.setHurtWaitCount(0);
		} else {
			snake.setHurtWaitCount(snake.getHurtWaitCount() + 1);
		}
	}

	private void moveBody() {
		List<Body> bs = snake.getBodys();
		for (int i = bs.size() - 1; i > 0; i--) {
			Body b = bs.get(i);
			Body beforeB = bs.get(i - 1);
			b.setX(beforeB.getX());
			b.setY(beforeB.getY());
		}
	}

	private void moveHead() {
		Body body = snake.getBodys().get(0);
		MoveUtil.moveBody(snake.getDirection(), body);
	}

	private void setupHurt() {
		snake.setHp(snake.getHp() - GameSetting.DEDUCTION_POINT);
		if(snake.getHp() > 0 ){
			snake.setStatus(Status.HURT);
		}else{
			snake.setStatus(Status.DIE);
		}
	}

	private boolean isHitWall(Body body) {
		HashMap<String, Snake> player = GameDataStore.getInstance()
				.getPlayer();
		for (String p : player.keySet()) {
			Snake s = player.get(p);
			if (isHitSomeSnake(s, body)) {
				return true;
			}

		}
		return false;
	}

	private boolean isHitSomeSnake(Snake s, Body body) {
		for (int i = 0; i < s.getBodys().size(); i++) {
			if (!s.getUserName().equals(snake.getUserName()) || i != 0) {
				if (s.getBodys().get(i).equals(body)) {
					return true;
				}
			}
		}
		return false;
	}

	private void checkEatEgg() {
		Body body = snake.getBodys().get(0);
		if (snakeMoveHandle.hasEatEgg(body)) {
			int x = body.getX();
			int y = body.getY();
			addBody(x, y);
			snake.setHp(snake.getHp()+1);
			snakeMoveHandle.removeEgg(x, y);
		}
	}

	private void addBody(int x, int y) {
		Body b = new Body(x, y);
		snake.getBodys().add(b);
	}

}
