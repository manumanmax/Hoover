package com.dydu.hoover.utils;

import java.util.ArrayList;
import java.util.Collection;

import com.dydu.hoover.model.Direction;
import com.dydu.hoover.model.MatrixPosition;

public abstract class AbstractCleaningStrategy {
	protected Collection<MatrixPosition> previousPositions = new ArrayList<MatrixPosition>();

	public int numberOfCleanedSquares() {
		return previousPositions.size();
	}

	public abstract Direction getNextDirection(Collection<Direction> availablePositions);
}
