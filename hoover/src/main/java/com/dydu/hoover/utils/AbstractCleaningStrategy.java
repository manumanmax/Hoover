package com.dydu.hoover.utils;

import java.util.ArrayList;
import java.util.Collection;

import com.dydu.hoover.model.MatrixPosition;

public abstract class AbstractCleaningStrategy {
	protected Collection<MatrixPosition> previousPositions = new ArrayList<MatrixPosition>();

	public abstract MatrixPosition getNextPosition(Collection<MatrixPosition> availablePositions);

	public int numberOfCleanedSquares() {
		return previousPositions.size();
	}
}
