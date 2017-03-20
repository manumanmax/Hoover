package com.dydu.hoover.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.List;

import com.dydu.hoover.utils.AbstractCleaningStrategy;

public class Hoover {
	private AbstractCleaningStrategy strategy;
	private List<Direction> route = new ArrayList<Direction>();
	private Deque<Deque<Direction>> unlockPaths = new ArrayDeque<Deque<Direction>>();
	private boolean positionLeftOnCriticalDirection = false;

	public void setStrategy(AbstractCleaningStrategy strategy) {
		this.strategy = strategy;
	}

	public void scanArea(Area area) {
		int i = 0;
		while (area.doNeedToBeClean() && i++ < 40) {
			Collection<Direction> availableDirections = area.availableDirections();
			Direction nextDirection = strategy.getNextDirection(availableDirections);
			if (nextDirection == Direction.UNDEFINED) {
				if (!stepBack(area, route)) {
					break;
				}
			} else {
				area.roll(nextDirection, route);
				if (availableDirections.size() > 1 && availableDirections.contains(strategy.criticalDirection)) {
					positionLeftOnCriticalDirection = true;
				} else if (nextDirection == strategy.criticalDirection) {
					positionLeftOnCriticalDirection = false;
				}
				if (positionLeftOnCriticalDirection && area.wall(strategy.criticalDirection)) {
					stepBack(area, route);
				}
			}
			area.print("Before move");
		}

	}

	private boolean stepBack(Area area, List<Direction> route) {
		if (unlockPaths.isEmpty()) {
			return false;
		}
		Deque<Direction> backPath = unlockPaths.pop();
		while (!backPath.isEmpty()) {
			area.roll(backPath.pop().reverse(), route);
		}
		return true;
	}

}
