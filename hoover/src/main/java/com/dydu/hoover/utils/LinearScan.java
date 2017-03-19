package com.dydu.hoover.utils;

import java.util.Collection;
import java.util.LinkedList;

import com.dydu.hoover.model.Direction;
import com.dydu.hoover.model.MatrixPosition;

public class LinearScan extends AbstractCleaningStrategy {
	private Collection<MatrixPosition> scanPriorityPositions = new LinkedList<MatrixPosition>();

	@Override
	public Direction getNextDirection(Collection<Direction> availablePositions) {
		return Direction.UP;
	}

}
