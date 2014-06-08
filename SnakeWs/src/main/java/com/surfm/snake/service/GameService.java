package com.surfm.snake.service;

import java.security.Principal;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.surfm.snake.dto.GameInfoPacker;
import com.surfm.snake.model.Direction;

@Service
@Scope("singleton")
public class GameService {
	
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	@Autowired
	private SnakeDao snakeDao;
	@Autowired
	private EggDao eggDao ;
	@Autowired
	private WebApplicationContext context;
	
	private ProcessQueue processQueue = ProcessQueue.getInstance();
	
	

	
	public void addPlayer(Principal pr){
		processQueue.getCreatePlayer().add(pr);
	}
	
	public void changeDirection(Principal p,Direction d){
		DirectionQueueData dqd = new DirectionQueueData();
		dqd.setDirection(d);
		dqd.setPrincipal(p);
		processQueue.getDirections().add(dqd);
	}
	
	@Scheduled(fixedDelay=150)
	@Async
	public synchronized void runGame(){
		runGameScript();
	}
	
	
	private void runGameScript(){
		snakeDao.createSnakes(processQueue.getCreatePlayer());
		snakeDao.changeDirections(processQueue.getDirections());
		snakeDao.moveSnakes(eggDao);
		eggDao.fillEgg();
		sendDataToUser();
	}
	
	private void sendDataToUser(){
		GameInfoPacker gp = context.getBean(GameInfoPacker.class);
		gp.pack();
		this.messagingTemplate.convertAndSend("/message/game" , gp.getAns());
	}

}
