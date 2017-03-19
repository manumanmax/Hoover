package com.dydu.hoover.model;

import java.util.Collection;

import com.dydu.hoover.utils.AbstractCleaningStrategy;

public class Hoover {
	private MatrixPosition position;
	private AbstractCleaningStrategy strategy;
	private int numberOfPlacesToClean;
	private MatrixPosition lastPlaceWithAvailablePosition;

	public Hoover(MatrixPosition matrixPosition, int numberOfPlacesToClean) {
		this.position = matrixPosition;
		this.numberOfPlacesToClean = numberOfPlacesToClean;
	}

	public void setStrategy(AbstractCleaningStrategy strategy) {
		this.strategy = strategy;
	}

	public void scanArea(Area area) {
		while (isTherePlacesToClean()) {
			Collection<Direction> availableDirections = area.availableDirections(position);
			if (!availableDirections.isEmpty()) {
				move(strategy.getNextDirection(availableDirections));
			} else {
				if (lastPlaceWithAvailablePosition != null) {
					goBackTo(lastPlaceWithAvailablePosition);
				}
			}
			strategy.getNextDirection(availableDirections);
		}
	}

	private void move(Direction direction) {
		position = direction.from(position);
	}

	private void goBackTo(MatrixPosition lastPlaceWithAvailablePosition2) {
		// TODO Auto-generated method stub

	}

	private boolean isTherePlacesToClean() {
		if (strategy.numberOfCleanedSquares() == numberOfPlacesToClean)
			return false;
		return true;
	}
}
