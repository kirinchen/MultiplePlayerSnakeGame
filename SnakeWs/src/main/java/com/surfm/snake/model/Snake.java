package com.surfm.snake.model;

import java.util.ArrayList;
import java.util.List;

public class Snake {

	public enum Status {
		BIRTH, LIVE, HURT , DIE;
	}

	private List<Body> bodys = new ArrayList<Body>();
	private String userName;
	private Direction direction ;
	private Status status = Status.BIRTH;
	private int hurtWaitCount = 0;
	private int hp = 1;
	

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
	
	public int getHurtWaitCount() {
		return hurtWaitCount;
	}

	public void setHurtWaitCount(int hurtWaitCount) {
		this.hurtWaitCount = hurtWaitCount;
	}
	
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

}
