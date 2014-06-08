package com.surfm.snake.service;

import java.security.Principal;

import com.surfm.snake.model.Direction;

public class DirectionQueueData {

	private Direction direction;
	private Principal principal;

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}

}
