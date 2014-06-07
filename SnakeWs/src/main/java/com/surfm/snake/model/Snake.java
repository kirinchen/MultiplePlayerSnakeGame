package com.surfm.snake.model;

import java.util.ArrayList;
import java.util.List;

public class Snake {

	public enum Status {
		BIRTH, LIVE, DIE;
	}

	private List<Body> bodys = new ArrayList<Body>();
	private String userName;
	private Direction direction = Direction.LEFT;
	private Status status;

	public Snake(String userName) {
		super();
		this.userName = userName;
	}

	public List<Body> getBodys() {
		return bodys;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
