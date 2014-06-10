package com.surfm.snake.service;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

import com.surfm.snake.model.Body;
import com.surfm.snake.model.Cell;
import com.surfm.snake.model.Direction;
import com.surfm.snake.model.GameDataStore;
import com.surfm.snake.model.Snake;
import com.surfm.snake.model.Snake.Status;

@Repository
public class SnakeDao {

	@Autowired
	private WebApplicationContext context;

	private GameDataStore gameDataStore = GameDataStore.getInstance();

	public void createSanke(Principal p) {
		if (!gameDataStore.hasPlayer(p)) {
			Cell cell = gameDataStore.getRandomEmptyCell();
			Body b = new Body(cell);
			Snake snake = new Snake(p.getName());
			snake.getBodys().add(b);
			gameDataStore.getPlayer().put(p.getName(), snake);
		}
	}

	public void createSnakes(Queue<Principal> queue) {
		Principal p = null;
		while ((p = queue.poll()) != null) {
			createSanke(p);
		}
	}

	public void changeDirections(Queue<DirectionQueueData> directions) {
		DirectionQueueData dp = null;
		while ((dp = directions.poll()) != null) {
			changeDirection(dp.getPrincipal(), dp.getDirection());
		}
	}

	private void changeDirection(Principal p, Direction direction) {
		Snake s = gameDataStore.getPlayer().get(p.getName());
		if(s.getStatus() == Status.BIRTH){
			s.setStatus(Status.LIVE);
			s.setDirection(direction);
		}else if (  s.getDirection().getType() != direction.getType()) {
			s.setDirection(direction);
		}
	}


	public void moveSnakes(SnakeMoveHandle smh) {
		for (Snake s : getSnakes()) {
			SnakeMover sm = context.getBean(SnakeMover.class);
			sm.init(s,smh);
			sm.move();
		}
	}

	private Collection<Snake> getSnakes() {
		return gameDataStore.getPlayer().values();
	}

	public void removeDieSnakes() {
		Set<String> keys = gameDataStore.getPlayer().keySet();
		for(String key : keys){
			Snake s = gameDataStore.getPlayer().get(key);
			if(s.getHp() <= 0){
				gameDataStore.getPlayer().remove(key);
			}
		}
	}

}
