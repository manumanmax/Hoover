package com.dydu.hoover.model;

public enum Direction {
	UP, DOWN, LEFT, RIGHT, UNDEFINED;

	public MatrixPosition from(MatrixPosition position) {

		MatrixPosition futurPosition = new MatrixPosition(position);
		switch (this) {
		case UP:
			futurPosition.line--;
			return futurPosition;
		case DOWN:
			futurPosition.line++;
			return futurPosition;
		case RIGHT:
			futurPosition.column++;
			return futurPosition;
		case LEFT:
			futurPosition.column--;
			return futurPosition;
		default:
			return null;
		}
	}

	public Direction reverse() {
		switch (this) {
		case UP:
			return Direction.DOWN;
		case DOWN:
			return Direction.UP;
		case RIGHT:
			return Direction.LEFT;
		case LEFT:
			return Direction.RIGHT;
		default:
			return Direction.UNDEFINED;
		}
	}

}
