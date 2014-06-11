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
	
	public void addDirectionQueueData(DirectionQueueData dqd){
		if(!hasQueue(dqd)){
			directions.add(dqd);
		}
	}
	
	private boolean hasQueue(DirectionQueueData dqd) {
		for(DirectionQueueData d : directions){
			if(d.getPrincipal().getName().equals(dqd.getPrincipal().getName())){
				return true;
			}
		}
		return false;
	}

	public static ProcessQueue getInstance(){
		return instance;
	}

}
