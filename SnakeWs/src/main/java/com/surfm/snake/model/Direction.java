package com.surfm.snake.model;

public enum Direction {
	UP(Type.VERTICAL), DOWN(Type.VERTICAL), LEFT(Type.HORIZONTAL), RIGHT(
			Type.HORIZONTAL);

	public enum Type {
		VERTICAL, HORIZONTAL;
	}

	private Type type;

	private Direction(Type t) {
		type = t;
	}

	public Type getType() {
		return type;
	}

}
