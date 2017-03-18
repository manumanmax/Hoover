package com.dydu.hoover.model;

import com.dydu.hoover.utils.AbstractCleaningStrategy;

public class Hoover {

	private MatrixPosition position;
	private AbstractCleaningStrategy strategy;
	private int numberOfPlacesToClean;

	public Hoover(MatrixPosition matrixPosition, int numberOfPlacesToClean) {
		this.position = matrixPosition;
		this.numberOfPlacesToClean = numberOfPlacesToClean;
	}

	public void setStrategy(AbstractCleaningStrategy strategy) {
		this.strategy = strategy;
	}

	public void scanArea() {
		while (isPlacesToClean()) {

		}
	}

	private boolean isPlacesToClean() {
		if (strategy.numberOfCleanedSquares() == numberOfPlacesToClean)
			return false;
		return true;
	}
}
