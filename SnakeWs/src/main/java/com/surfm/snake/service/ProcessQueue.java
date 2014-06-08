package com.surfm.snake.service;

import java.security.Principal;
import java.util.LinkedList;
import java.util.Queue;

public class ProcessQueue {

	private static final ProcessQueue instance = new ProcessQueue();
	
	private Queue<Principal> createPlayer = new LinkedList<Principal>();
	
	private Queue<DirectionQueueData> directions = new LinkedList<DirectionQueueData>();
	
	private ProcessQueue(){}

	public Queue<Principal> getCreatePlayer() {
		return createPlayer;
	}
	
	public Queue<DirectionQueueData> getDirections() {
		return directions;
	}
	
	public static ProcessQueue getInstance(){
		return instance;
	}

}
