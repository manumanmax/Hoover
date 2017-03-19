package com.dydu.hoover.model;

public class MatrixPosition {
	private final short MATRIX_SHIFT = 1;
	public int line;
	public int column;

	/**
	 * Be careful, this constructor is reserved for translation from user
	 * coordonates to matrix coordonates
	 * 
	 * @param x
	 * @param y
	 */
	public MatrixPosition(int x, int y) {
		this.line = x - MATRIX_SHIFT;
		this.column = y - MATRIX_SHIFT;
	}

	public MatrixPosition(MatrixPosition position) {
		this.line = position.line;
		this.column = position.column;
	}

	public int getLine() {
		return line + MATRIX_SHIFT;
	}

	public int getColumn() {
		return column + MATRIX_SHIFT;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + MATRIX_SHIFT;
		result = prime * result + column;
		result = prime * result + line;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatrixPosition other = (MatrixPosition) obj;
		if (MATRIX_SHIFT != other.MATRIX_SHIFT)
			return false;
		if (column != other.column)
			return false;
		if (line != other.line)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[" + getLine() + "," + getColumn() + "]";
	}

}
