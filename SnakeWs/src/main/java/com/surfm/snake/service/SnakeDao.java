package com.surfm.snake.service;

import java.security.Principal;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import org.springframework.stereotype.Repository;

import com.surfm.snake.model.Body;
import com.surfm.snake.model.Cell;
import com.surfm.snake.model.GameDataStore;
import com.surfm.snake.model.Snake;

@Repository
public class SnakeDao {

	private GameDataStore gameDataStore = GameDataStore.getInstance();

	public void createSanke(Principal p) {
		Cell cell = gameDataStore.getRandomEmptyCell();
		Body b = new Body(cell);
		Snake snake = new Snake(p.getName());
		snake.getBodys().add(b);
		gameDataStore.getPlayer().put(p, snake);
	}

	public void createSnakes(Queue<Principal> queue) {
		Principal p = null;
		while ((p = queue.poll()) != null) {
			createSanke(p);
		}
	}

}
