package com.dydu.hoover.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Area {
	private String[][] matrix;
	private MatrixPosition hooverPosition;
	private MatrixPosition startPosition;

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
		Collection<Direction> availablePositions = new ArrayList<Direction>();
		for (Direction direction : Direction.values()) {
			MatrixPosition directionCoordonates = direction.from(hooverPosition);
			if (isFree(directionCoordonates))
				availablePositions.add(direction);
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

	public void roll(Direction nextDirection, List<Direction> route) {
		hooverPosition = nextDirection.from(hooverPosition);
		setValueAt(hooverPosition, "c");

	}

	public void print(String sentence, List<Direction> route) {
		List<String[]> tmpMatrix = new ArrayList<String[]>();
		for (String[] line : matrix) {
			int pos = line.length - 1;
			if (line[pos].equals("\r")) {
				tmpMatrix.add(Arrays.copyOf(line, line.length - 1));
			}
		}

		System.out.println(sentence);
		for (String[] line : tmpMatrix) {
			System.err.print(Arrays.toString(line) + "\n");
		}
		System.out.flush();
	}

}
