package com.surfm.snake.dto;

import java.util.ArrayList;
import java.util.List;

import com.surfm.snake.model.Egg;
import com.surfm.snake.model.Snake;

public class GameInfo {

	private List<Snake> snakes = new ArrayList<Snake>();
	private List<Egg> eggs = new ArrayList<Egg>();

	public List<Snake> getSnakes() {
		return snakes;
	}

	public void setSnakes(List<Snake> snakes) {
		this.snakes = snakes;
	}

	public List<Egg> getEggs() {
		return eggs;
	}

	public void setEggs(List<Egg> eggs) {
		this.eggs = eggs;
	}
}
