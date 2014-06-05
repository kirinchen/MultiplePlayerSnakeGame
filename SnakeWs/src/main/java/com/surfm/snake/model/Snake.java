package com.surfm.snake.model;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class Snake {

	private List<Cell> cells = new ArrayList<Cell>();
	private Principal principal;
	private Direction direction = Direction.LEFT;
	

	public Snake(Principal principal) {
		super();
		this.principal = principal;
	}

}
