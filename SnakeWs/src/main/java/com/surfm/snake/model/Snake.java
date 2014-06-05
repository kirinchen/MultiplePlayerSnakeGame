package com.surfm.snake.model;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class Snake {

	private List<Cell> cells = new ArrayList<Cell>();
	private String userName;
	private Direction direction = Direction.LEFT;
	

	public Snake(String userName) {
		super();
		this.userName = userName;
	}

}
