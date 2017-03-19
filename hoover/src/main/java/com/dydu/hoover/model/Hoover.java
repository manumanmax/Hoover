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
	private boolean positionLeftOnCriticalDirection = false;

	public Hoover(int numberOfPlacesToClean) {
		this.numberOfPlacesToClean = numberOfPlacesToClean;
	}

	public void setStrategy(AbstractCleaningStrategy strategy) {
		this.strategy = strategy;
	}

	public void scanArea(Area area) {
		while (area.doNeedToBeClean()) {
			area.print("Before move");
			Collection<Direction> availableDirections = area.availableDirections();
			Direction nextDirection = strategy.getNextDirection(availableDirections);
			if (nextDirection == Direction.UNDEFINED) {
				stepBack(area, route);
			} else {
				area.roll(nextDirection, route);
				unlockPath.push(nextDirection);
				if (availableDirections.size() != 1 && availableDirections.contains(strategy.criticalDirection)) {
					positionLeftOnCriticalDirection = true;
				} else if (nextDirection == strategy.criticalDirection) {
					positionLeftOnCriticalDirection = false;
				}
				if (positionLeftOnCriticalDirection && area.wall(strategy.criticalDirection)) {
					stepBack(area, route);
				}
			}
		}

	}

	private void stepBack(Area area, List<Direction> route) {
		if (unlockPath.isEmpty()) {
			return;
		}
		area.roll(unlockPath.pop().reverse(), route);
	}

}
