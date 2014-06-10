package com.surfm.snake.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.surfm.snake.model.Egg;
import com.surfm.snake.model.GameDataStore;
import com.surfm.snake.model.Snake;

@Service
@Scope("prototype")
public class GameInfoPacker {

	private GameDataStore gameDataStore = GameDataStore.getInstance();
	private GameInfo ans = new GameInfo();

	private List<Snake> snakes = new ArrayList<Snake>();

	public void pack() {
		setupSnakes();
		setupEggs();
		ans.setSnakes(snakes);
	}

	public GameInfo getAns() {
		return ans;
	}

	private void setupSnakes() {
		Set<String> keys = gameDataStore.getPlayer().keySet();
		for (String key : keys) {
			snakes.add(gameDataStore.getPlayer().get(key));
		}
	}
	
	private void setupEggs(){
		for(Egg e : gameDataStore.getEggs()){
			ans.getEggs().add(e);
		}
	}

}
