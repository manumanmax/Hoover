package com.dydu.hoover.utils;

import java.util.Collection;

import com.dydu.hoover.model.Direction;

public abstract class AbstractCleaningStrategy {
	public Direction criticalDirection;

	public abstract Direction getNextDirection(Collection<Direction> availablePositions);
}
