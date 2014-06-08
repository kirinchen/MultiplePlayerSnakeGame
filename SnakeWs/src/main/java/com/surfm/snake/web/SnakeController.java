package com.surfm.snake.web;

import java.security.Principal;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import com.surfm.snake.config.GameSetting;
import com.surfm.snake.dto.JionInfo;
import com.surfm.snake.model.Direction;
import com.surfm.snake.service.GameService;

@Controller
public class SnakeController {

	@Autowired
	private GameService gameService;

	@SubscribeMapping("/hi")
	public JionInfo jionGame(Principal principal) {
		JionInfo ans = new JionInfo();
		ans.setHeight(GameSetting.MAX_HEIGHT);
		ans.setWidth(GameSetting.MAX_WIDTH);
		ans.setMyName(principal.getName());
		gameService.addPlayer(principal);
		return ans;
	}
	
	@PostConstruct
	public void init() {
	}

	@MessageMapping("/snake/direction/{direction}")
	public void receiveSendMessage(@DestinationVariable Direction direction,
			Principal principal) {
		System.out.println(principal.getName() + " direction = " + direction);
		gameService.changeDirection(principal, direction);
	}

}
