package com.surfm.snake.web;

import java.awt.Point;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import com.surfm.snake.config.GameSetting;
import com.surfm.snake.dto.JionInfo;
import com.surfm.snake.model.Cell;
import com.surfm.snake.model.Direction;
import com.surfm.snake.model.GameDataStore;
import com.surfm.snake.service.GameService;

@Controller
public class SnakeController {

    @Autowired
    private GameService gameService;
    
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @SubscribeMapping("/hi")
    public JionInfo jionGame(Principal principal) {
        JionInfo ans = new JionInfo();
        ans.setHeight(GameSetting.MAX_HEIGHT);
        ans.setWidth(GameSetting.MAX_WIDTH);
        ans.setMyName(principal.getName());
        ans.setEggs(GameDataStore.getInstance().getEggs());
        gameService.addPlayer(principal);
        return ans;
    }

    @SubscribeMapping("/test")
    public Date test(Principal principal) {
        Thread t = new Thread(new TestRunnable());
        t.start();
        return new Date();
    }
    
    @PostConstruct
    public void init() {
    }

    @MessageMapping("/snake/direction/{direction}")
    public void receiveSendMessage(@DestinationVariable Direction direction, Principal principal) {
        System.out.println(principal.getName() + " direction = " + direction);
        gameService.changeDirection(principal, direction);
    }

    class TestRunnable implements Runnable{

        @Override
        public void run() {
            List<Cell> ps = new ArrayList<Cell>();
           for(int i=0;i<100;i++){
               for(int j=0;j<100;j++){
                   Cell p = new Cell(i, j);
                   ps.add(p);
               }
           } 
           messagingTemplate.convertAndSend("/message/test", ps);
        }
    }
    
}
