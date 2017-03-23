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
	Deque<Direction> currentBackPath = new ArrayDeque<Direction>();

	public void setStrategy(AbstractCleaningStrategy strategy) {
		this.strategy = strategy;
	}

	public void scanArea(Area area) {
		area.print("");
		while (area.doNeedToBeCleaned()) {
			Collection<Direction> availableDirections = area.availableDirections();
			if (availableDirections.size() == 1) {
				area.roll(availableDirections.iterator().next(), route);
				currentBackPath.push(availableDirections.iterator().next().reverse());
				continue;
			}
			Direction nextDirection = strategy.getNextDirection(availableDirections);
			if (nextDirection == Direction.UNDEFINED) {
				boolean canGoBack = stepBack(area, route);
				if (!canGoBack) {
					System.out.println("can't go back...");
					break;
				}
			} else {
				unlockPaths.push(new ArrayDeque<Direction>(currentBackPath));
				currentBackPath.clear();
				currentBackPath.push(nextDirection.reverse());
				area.roll(nextDirection, route);
			}
		}
		area.print("");
	}

	private boolean stepBack(Area area, List<Direction> route) {
		if (currentBackPath.isEmpty())
			if (unlockPaths.isEmpty()) {
				return false;
			}
		Deque<Direction> backPath = new ArrayDeque<Direction>(currentBackPath);
		while (!backPath.isEmpty()) {
			Direction d = backPath.pop();
			area.roll(d, route);
		}
		if (unlockPaths.isEmpty()) {
			currentBackPath = new ArrayDeque<Direction>();
		} else {
			currentBackPath = new ArrayDeque<Direction>(unlockPaths.pop());
		}
		return true;
	}

	public void printRoute(Area area) {
		area.printRoute(route);

	}

}
