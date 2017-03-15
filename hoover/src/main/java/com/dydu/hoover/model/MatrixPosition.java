package com.dydu.hoover.model;

public class MatrixPosition {
	private final short MATRIX_SHIFT = 1;
	public int line;
	public int column;

	public MatrixPosition(int x, int y) {
		this.line = x;
		this.column = y;
	}

	public MatrixPosition(MatrixPosition position) {
		this.line = position.line;
		this.column = position.column;
	}

	public int getLine() {
		return line - MATRIX_SHIFT;
	}

	public int getColumn() {
		return column - MATRIX_SHIFT;
	}
}
