package com.surfm.snake.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.surfm.snake.dto.GameInfoPacker;

@Service
public class GameService {
	
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	@Autowired
	private SnakeDao snakeDao;
	@Autowired
	private WebApplicationContext context;
	
	private ProcessQueue processQueue = ProcessQueue.getInstance();
	
	public void addPlayer(Principal pr){
		processQueue.getCreatePlayer().add(pr);
	}
	
	@Scheduled(fixedDelay=2000)
	public void runGame(){
		snakeDao.createSnakes(processQueue.getCreatePlayer());
		GameInfoPacker gp = context.getBean(GameInfoPacker.class);
		gp.pack();
		this.messagingTemplate.convertAndSend("/message/game" , gp.getAns());

	}

}
