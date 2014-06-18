package com.surfm.snake.dto;

import java.util.List;

import com.surfm.snake.model.Egg;

public class JionInfo {

	private int width;
	private int height;
	private String myName;
	private List<Egg> eggs;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public List<Egg> getEggs() {
		return eggs;
	}

	public void setEggs(List<Egg> eggs) {
		this.eggs = eggs;
	}
	
}
