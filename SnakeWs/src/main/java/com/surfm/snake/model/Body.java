package com.surfm.snake.model;

public class Body extends Cell {

	public Body(int x, int y) {
		super(x, y);
	}

	public Body(Cell cell) {
		super(cell.getX(), cell.getY());
	}
	
	public Cell getCell(){
		return new Cell(getX(),getY());
	}

}
