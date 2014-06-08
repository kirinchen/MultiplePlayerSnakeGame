package com.surfm.snake.model;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.surfm.snake.config.GameSetting;

public class GameDataStore {

	private static final GameDataStore instance = new GameDataStore();

	private HashMap<Principal, Snake> player = new HashMap<Principal, Snake>();
	private List<Egg> eggs = new ArrayList<Egg>();

	private GameDataStore() {
	}

	public boolean hasPlayer(Principal p) {
		return player.containsKey(p);
	}

	public Cell getRandomEmptyCell() {
		Random r = new Random();
		List<Cell> emptys = getEmptyCells();
		int index = r.nextInt(emptys.size() - 1);
		Cell c = emptys.get(index);
		emptys.remove(index);
		return c;
	}

	public List<Cell> getEmptyCells() {
		List<Cell> ans = new ArrayList<Cell>();
		for (int i = 0; i < GameSetting.MAX_WIDTH; i++) {
			for (int j = 0; j < GameSetting.MAX_HEIGHT; j++) {
				if (isEmpty(i, j))
					ans.add(new Cell(i, j));
			}
		}
		return ans;
	}

	private boolean isEmpty(int i, int j) {
		if (!isEmptyForSnakes(i, j))
			return false;
		if (!isEmptyForEggs(i, j))
			return false;
		return true;
	}

	private boolean isEmptyForEggs(int i, int j) {
		for (Egg e : eggs) {
			if (e.getX() == i && e.getY() == j)
				return false;
		}
		return true;
	}

	private boolean isEmptyForSnakes(int i, int j) {
		for (Principal p : player.keySet()) {
			Snake s = player.get(p);
			for (Body b : s.getBodys()) {
				if (b.getX() == i && b.getY() == j)
					return false;
			}
		}
		return true;
	}

	public HashMap<Principal, Snake> getPlayer() {
		return player;
	}

	public List<Egg> getEggs() {
		return eggs;
	}

	public static GameDataStore getInstance() {
		return instance;
	}

}
