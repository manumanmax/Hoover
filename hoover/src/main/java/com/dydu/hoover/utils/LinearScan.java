package com.dydu.hoover.tools;

import java.util.ArrayList;
import java.util.Collection;

import com.dydu.hoover.model.MatrixPosition;
import com.dydu.hoover.utils.ICleanStrategy;

public class LinearScan implements ICleanStrategy {
	private Collection<MatrixPosition> previousPositions = new ArrayList<MatrixPosition>();

	public MatrixPosition getNextPosition(MatrixPosition currentPosition) {
		return null;
	}

}
