package com.dydu.hoover.utils;

import com.dydu.hoover.model.MatrixPosition;

public interface ICleanStrategy {

	public MatrixPosition getNextPosition(MatrixPosition currentPosition);

}
