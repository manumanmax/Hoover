package com.dydu.hoover.utils;

import java.util.Arrays;
import java.util.Collection;

import com.dydu.hoover.model.Direction;

public class LinearScan extends AbstractCleaningStrategy {
	private final Collection<Direction> scanPriorityDirections = Arrays.asList(Direction.UP, Direction.LEFT,
			Direction.DOWN, Direction.RIGHT);

	public LinearScan() {
		criticalDirection = Direction.RIGHT;
	}

	@Override
	public Direction getNextDirection(Collection<Direction> availablePositions) {
		if (availablePositions.isEmpty()) {
			return Direction.UNDEFINED;
		}
		for (Direction priority : scanPriorityDirections) {
			if (availablePositions.contains(priority)) {
				return priority;
			}
		}
		return Direction.UNDEFINED;
	}

}
