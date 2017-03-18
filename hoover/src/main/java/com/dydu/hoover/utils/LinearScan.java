package com.dydu.hoover.utils;

import java.util.Collection;

import com.dydu.hoover.model.Direction;
import com.dydu.hoover.model.MatrixPosition;

public class LinearScan extends AbstractCleaningStrategy {
	private Direction currentScanDirection;

	@Override
	public MatrixPosition getNextPosition(Collection<MatrixPosition> availablePositions) {

		return null;
	}

}
