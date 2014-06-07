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
	private List<Cell> emptys = new ArrayList<Cell>();

	private GameDataStore() {
		initEmptys();
	}

	private void initEmptys() {
		for(int i=0;i<GameSetting.MAX_WIDTH;i++){
			for(int j=0;j<GameSetting.MAX_HEIGHT;j++){
				emptys.add(new Cell(i, j));
			}
		}
	}
	
	public Cell getRandomEmptyCell(){
		Random r = new Random();
		int index = r.nextInt(emptys.size() -1);
		Cell c = emptys.get(index);
		emptys.remove(index);
		return c;
	}
	
	public void registerEmpty(Cell cell){
		int removeIndex = -1;
		for(int i=0;i<emptys.size();i++){
			if(emptys.get(i).hashCode() == cell.hashCode()){
				removeIndex = i;
				break;
			}
		}
		emptys.remove(removeIndex);
	}

	public void unRegister(Cell cell){
		for(int i=0;i<emptys.size();i++){
			if(emptys.get(i).hashCode() == cell.hashCode()){
				throw new RuntimeException("this is has empty cell in unRegister" );
			}
		}
		emptys.add(cell);
	}

	public HashMap<Principal, Snake> getPlayer() {
		return player;
	}

	public List<Egg> getEggs() {
		return eggs;
	}

	public List<Cell> getEmptys() {
		return emptys;
	}

	public static GameDataStore getInstance() {
		return instance;
	}

}
