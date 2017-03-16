package com.dydu.hoover.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Area {
	private String[][] matrix;

	public Area(String[][] matrix) {
		this.matrix = matrix;
	}

	public Collection<MatrixPosition> availablePositions(MatrixPosition position) {
		Collection<MatrixPosition> availablePositions = new ArrayList<MatrixPosition>();
		for (Direction direction : Direction.values()) {
			MatrixPosition directionCoordonates = direction.from(position);
			if (isFree(directionCoordonates))
				availablePositions.add(directionCoordonates);
		}
		return availablePositions;
	}

	private boolean isFree(MatrixPosition position) {

		if (isInsideMatrix(position)) {
			if (matrix[position.getLine()][position.getColumn()].equals(" ")) {
				return true;
			}
		}
		return false;
	}

	private boolean isInsideMatrix(MatrixPosition position) {
		if (position.getLine() > 0 && position.getColumn() > 0 && position.getLine() < matrix.length
				&& position.getColumn() < matrix[0].length) {
			return true;
		}
		return false;
	}

	public void setValueAt(MatrixPosition position, String value) {
		matrix[position.getLine()][position.getColumn()] = value;
	}

	public int freePositions() {
		int freePos = 0;
		for (String[] line : matrix) {
			for (String cell : line) {
				if (cell.equals(" ")) {
					freePos++;
				}
			}
		}
		return freePos;
	}

	public void print() {
		for (String[] line : matrix) {
			System.err.println(Arrays.toString(line) + "\n");
		}
	}

}
