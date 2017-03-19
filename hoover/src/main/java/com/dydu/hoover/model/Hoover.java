package com.dydu.hoover.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.List;

import com.dydu.hoover.utils.AbstractCleaningStrategy;

public class Hoover {
	private AbstractCleaningStrategy strategy;
	private int numberOfPlacesToClean;
	private List<Direction> route = new ArrayList<Direction>();
	private Deque<Direction> unlockPath = new ArrayDeque<Direction>();

	public Hoover(int numberOfPlacesToClean) {
		this.numberOfPlacesToClean = numberOfPlacesToClean;
	}

	public void setStrategy(AbstractCleaningStrategy strategy) {
		this.strategy = strategy;
	}

	public void scanArea(Area area) {
		for (int i = 0; i < 6; i++) {
			area.print("Before move", route);

			if (isTherePlacesToClean()) {
				Collection<Direction> availableDirections = area.availableDirections();
				area.roll(strategy.getNextDirection(availableDirections), route);
			}
			area.print("After move", route);
		}
	}

	private boolean isTherePlacesToClean() {
		if (strategy.numberOfCleanedSquares() == numberOfPlacesToClean)
			return false;
		return true;
	}
}
