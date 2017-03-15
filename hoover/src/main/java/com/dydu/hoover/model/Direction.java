package com.dydu.hoover.model;

public enum Direction {
	UP, DOWN, LEFT, RIGHT;

	public MatrixPosition from(MatrixPosition position) {
		MatrixPosition direction = position;
		switch (this) {
		case UP:
			direction.line -= 1;
			break;
		case DOWN:
			direction.line += 1;
			break;
		case RIGHT:
			direction.column += 1;
			break;
		case LEFT:
			direction.column -= 1;
		default:
		}
		return direction;
	}
}
