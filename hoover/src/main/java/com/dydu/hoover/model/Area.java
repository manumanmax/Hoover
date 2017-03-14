package com.dydu.hoover.model;

import java.awt.Point;
import java.util.Collection;
import java.util.Collections;

public class Area {
	private String[][] matrix;

	public Area(String[][] matrix) {
		this.matrix = matrix;
	}

	public Collection<Point> availablePositions() {
		return Collections.emptyList();
	}
}
