package com.surfm.snake.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import com.surfm.snake.config.GameSetting;
import com.surfm.snake.model.Body;
import com.surfm.snake.model.Cell;
import com.surfm.snake.model.Egg;
import com.surfm.snake.model.GameDataStore;

@Service
public class EggDao implements SnakeMoveHandle {

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	private GameDataStore gameDataStore = GameDataStore.getInstance();
	private List<Egg> eggs = gameDataStore.getEggs();

	public void fillEgg() {
		if (eggs.size() <= GameSetting.EGG_FILL_COUNT) {
			createEggs();
		}
	}

	private void createEggs() {
		List<Cell> emptyCells = gameDataStore.getEmptyCells();
		List<Egg> eggs = new ArrayList<Egg>();
		for (int i = 0; i < GameSetting.EGG_FILL_COUNT; i++) {
			eggs.add(createEgg(emptyCells));
		}
		notifyCreateEggs(eggs);
	}

	private Egg createEgg(List<Cell> emptyCells) {
		Random r = new Random();
		int removeIndex = r.nextInt(emptyCells.size() - 1);
		Cell c = emptyCells.remove(removeIndex);
		Egg e = new Egg(c.getX(), c.getY());
		eggs.add(e);
		return e;
	}

	private void notifyCreateEggs(List<Egg> _eggs) {
		this.messagingTemplate.convertAndSend("/message/eggs/create", _eggs);
	}

	@Override
	public boolean hasEatEgg(Body body) {
		for (Egg e : eggs) {
			if (e.getX() == body.getX() && e.getY() == body.getY()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void removeEgg(int x, int y) {
		Egg removeE = null;
		for (Egg e : eggs) {
			if (e.getX() == x && e.getY() == y) {
				removeE = e;
				break;
			}
		}
		if(eggs.remove(removeE)){
		    notifyRemoveEgg(removeE);
		}
		
	}

    private void notifyRemoveEgg(Egg removeE) {
        this.messagingTemplate.convertAndSend("/message/eggs/remove", removeE);
    }

}
