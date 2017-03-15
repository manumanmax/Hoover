package com.dydu.hoover.model;

public class MatrixPosition {
	private final short MATRIX_SHIFT = 1;
	public int line;
	public int column;

	public MatrixPosition(int x, int y) {
		this.line = x - MATRIX_SHIFT;
		this.column = x - MATRIX_SHIFT;
	}
}
