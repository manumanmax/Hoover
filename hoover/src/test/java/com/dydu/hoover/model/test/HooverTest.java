package com.dydu.hoover.model.test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.NoSuchFileException;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.dydu.hoover.model.Area;
import com.dydu.hoover.model.Direction;
import com.dydu.hoover.model.Hoover;
import com.dydu.hoover.model.MatrixPosition;
import com.dydu.hoover.utils.LinearScan;
import com.dydu.hoover.utils.MatrixFileReader;

public class HooverTest {
	private String file = "/room.txt";
	private Area area;
	private Class<? extends Hoover> hooverClass;
	private Method stepBack;
	private Hoover hoover;
	private Field currentBackPath;

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
			stepBack = hooverClass.getDeclaredMethod("stepBack", Area.class, List.class);
		} catch (NoSuchMethodException e) {
			fail("NoSuchMethodException : stepBack in Hoover.");
		} catch (SecurityException e) {
			fail("SecurityException : initializing stepBack reflector.");
		}
		stepBack.setAccessible(true);

		try {
			currentBackPath = hooverClass.getDeclaredField("currentBackPath");
		} catch (NoSuchFieldException e) {
			fail("NoSuchFieldException : currentBackPath in Hoover.");
		} catch (SecurityException e) {
			fail("SecurityException : initializing currentBackPath reflector.");
		}

		currentBackPath.setAccessible(true);
	}

	@Test
	public void stepBackTest() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		List<Direction> route = new LinkedList<Direction>();
		ArrayDeque<Direction> backPath = new ArrayDeque<Direction>();
		for (int i = 0; i < 2; i++) {
			area.roll(Direction.DOWN, route);
			backPath.push(Direction.DOWN.reverse());
			currentBackPath.set(hoover, backPath);

		}

		for (int i = 0; i < 2; i++) {
			stepBack.invoke(hoover, area, route);
			assertThat(route.size(), is(4));
			assertThat(route.get(route.size() - 1), is(Direction.UP));
		}
	}

}
