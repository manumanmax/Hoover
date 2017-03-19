package com.dydu.hoover.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Area {
	private String[][] matrix;
	private MatrixPosition hooverPosition;
	private MatrixPosition startPosition;
	private int numberOfCleanedPlaces = 0;
	private int connexeNumberOfPlacesToClean;

	public Area(String[][] matrix, MatrixPosition hooverPosition, int positionsToClean) {
		this.matrix = matrix;
		this.hooverPosition = hooverPosition;
		this.startPosition = hooverPosition;
		connexeNumberOfPlacesToClean = positionsToClean;
		setValueAt(startPosition, "c");
	}

	/**
	 * this constructor is use for copy when counting the positions to clean.
	 * 
	 * @param matrix
	 * @param hooverPosition
	 */
	public Area(String[][] matrix, MatrixPosition hooverPosition) {
		this.matrix = matrix;
		this.hooverPosition = hooverPosition;
		this.startPosition = hooverPosition;
		setValueAt(startPosition, "c");
	}

	public Area(String[][] matrix, MatrixPosition hooverPosition, MatrixPosition startPosition) {
		this.matrix = matrix;
		this.hooverPosition = hooverPosition;
		this.startPosition = hooverPosition;
		setValueAt(startPosition, "c");
	}

	public Collection<Direction> availableDirections() {
		if (matrix[hooverPosition.line][hooverPosition.column].equals("M")) {
			return Collections.emptyList();
		}
		Collection<Direction> availablePositions = new ArrayList<Direction>();
		for (Direction direction : Direction.values()) {
			MatrixPosition directionCoordonates = direction.from(hooverPosition);
			if (isFree(directionCoordonates)) {
				availablePositions.add(direction);
			}
		}
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
		if (position == null) {
			return false;
		}
		if (position.line >= 0 && position.column >= 0 && position.line < matrix.length - 1
				&& position.column < matrix[0].length - 1) {
			return true;
		}
		return false;
	}

	public void setValueAt(MatrixPosition position, String value) {
		if (matrix[position.line][position.column].equals(value)) {
			return;
		}
		matrix[position.line][position.column] = value;
		numberOfCleanedPlaces++;
	}

	public void roll(Direction nextDirection, List<Direction> route) {
		hooverPosition = nextDirection.from(hooverPosition);
		setValueAt(hooverPosition, "c");

	}

	public void print(String sentence) {
		List<String[]> tmpMatrix = new ArrayList<String[]>();
		for (String[] line : matrix) {
			int pos = line.length - 1;
			if (line[pos].equals("\r")) {
				tmpMatrix.add(Arrays.copyOf(line, line.length - 1));
			}
		}

		// System.out.append(sentence);
		for (String[] line : tmpMatrix) {
			System.err.append(Arrays.toString(line) + "\n");
		}
		System.out.flush();

		System.err.flush();
	}

	public boolean wall(Direction direction) {
		MatrixPosition position = direction.from(hooverPosition);
		if (matrix[position.line][position.column].equals("M")) {
			return true;
		}
		return false;
	}

	public boolean doNeedToBeClean() {
		if (numberOfCleanedPlaces < connexeNumberOfPlacesToClean) {
			return true;
		}
		return false;

	}

	public int positionToClean() {
		int foundPlaces = 0;
		Collection<Direction> directions = new ArrayList<Direction>();
		Collection<MatrixPosition> positions = new ArrayList<MatrixPosition>();
		directions = availableDirections();
		for (Direction d : directions) {
			positions.add(d.from(hooverPosition));
		}
		for (MatrixPosition p : positions) {
			setValueAt(p, "s");
		}

		for (MatrixPosition p : positions) {
			hooverPosition = p;
			foundPlaces += positionToClean();
		}

		return directions.size() + foundPlaces;
	}

}
