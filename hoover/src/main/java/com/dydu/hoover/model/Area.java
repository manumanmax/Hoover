package com.dydu.hoover.model;

import java.util.ArrayList;
import java.util.Collection;

public class Area {
	private String[][] matrix;

	public Area(String[][] matrix) {
		this.matrix = matrix;
	}

	public Collection<MatrixPosition> availablePositions(MatrixPosition position) {

		Collection<MatrixPosition> availablePositions = new ArrayList<MatrixPosition>();
		for (Direction direction : Direction.values())
			if (isFree(direction.from(position)))
				availablePositions.add(direction.from(position));
		return availablePositions;
	}

	private boolean isFree(MatrixPosition position) {
		if (isInsideMatrix(position)) {
			if (matrix[position.line][position.column].equals(" ")) {
				return true;
			}
		}
		return false;
	}

	private boolean isInsideMatrix(MatrixPosition position) {
		if (position.line > 0 && position.column > 0 && position.line < matrix.length
				&& position.column < matrix[0].length) {
			return true;
		}
		return false;
	}
}
