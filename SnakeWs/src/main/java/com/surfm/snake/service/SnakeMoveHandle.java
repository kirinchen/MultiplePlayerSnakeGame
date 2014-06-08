package com.surfm.snake.service;

import com.surfm.snake.model.Body;

public interface SnakeMoveHandle {

	boolean hasEatEgg(Body body);

	void removeEgg(int x, int y);

}
