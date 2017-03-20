package com.dydu.hoover.model.test;

import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.NoSuchFileException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.dydu.hoover.model.Area;
import com.dydu.hoover.model.Direction;
import com.dydu.hoover.model.Hoover;
import com.dydu.hoover.model.MatrixPosition;
import com.dydu.hoover.utils.AbstractCleaningStrategy;
import com.dydu.hoover.utils.LinearScan;
import com.dydu.hoover.utils.MatrixFileReader;

public class HooverTest {
	private String file = "/room.txt";
	private Area area;
	private Class<? extends Hoover> hooverClass;
	private Method stepBack;
	private Hoover hoover;
	private Field strategy;

	@Before
	public void setUp() throws NoSuchFileException {
		String[][] matrix = new MatrixFileReader().readFile(file);
		if (matrix == null) {
			throw new NoSuchFileException(file);
		}
		area = new Area(matrix, new MatrixPosition(3, 3), 0);
		hoover = new Hoover();
		hoover.setStrategy(new LinearScan());
		hooverClass = hoover.getClass();

		try {
			Method[] methods = hooverClass.getDeclaredMethods();
			stepBack = hooverClass.getMethod("stepBack", Area.class, List.class);
		} catch (NoSuchMethodException e) {
			fail("NoSuchMethodException : stepBack in Hoover.");
		} catch (SecurityException e) {
			fail("SecurityException : initializing stepBack reflector.");
		}
		stepBack.setAccessible(true);

		try {
			strategy = hooverClass.getDeclaredField("strategy");
		} catch (NoSuchFieldException e) {
			fail("NoSuchFieldException : strategy in Hoover.");
		} catch (SecurityException e) {
			fail("SecurityException : initializing position reflector.");
		}

		strategy.setAccessible(true);
	}

	@Test
	public void stepBackTest() throws IllegalArgumentException, IllegalAccessException {
		List<Direction> route = new LinkedList<Direction>();

		AbstractCleaningStrategy areaStrategy = ((AbstractCleaningStrategy) strategy.get(area));

		for (int i = 0; i < 3; i++) {
			area.roll(areaStrategy.getNextDirection(area.availableDirections()), route);
		}
		area.print("");
	}

}
