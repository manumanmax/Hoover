package com.dydu.hoover.model;

public enum Direction {
	UP, DOWN, LEFT, RIGHT;

	public MatrixPosition from(MatrixPosition position) {
		switch (this) {
		case UP:
			return new MatrixPosition(position.line - 1, position.column);
		case DOWN:
			return new MatrixPosition(position.line + 1, position.column);
		case RIGHT:
			return new MatrixPosition(position.line, position.column + 1);
		case LEFT:
			return new MatrixPosition(position.line, position.column - 1);
		default:
			return position;
		}
	}
}
