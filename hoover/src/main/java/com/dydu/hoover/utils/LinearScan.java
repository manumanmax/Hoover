package com.dydu.hoover.utils;

import java.util.Arrays;
import java.util.Collection;

import com.dydu.hoover.model.Direction;

public class LinearScan extends AbstractCleaningStrategy {
	private final Collection<Direction> scanPriorityDirections = Arrays.asList(Direction.UP, Direction.LEFT,
			Direction.DOWN, Direction.RIGHT);

	@Override
	public Direction getNextDirection(Collection<Direction> availablePositions) {
		return Direction.UP;
	}

}
