package com.dydu.hoover.model.test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.nio.file.NoSuchFileException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import com.dydu.hoover.model.Area;
import com.dydu.hoover.model.Direction;
import com.dydu.hoover.model.MatrixPosition;
import com.dydu.hoover.utils.MatrixFileReader;

public class AreaTest {

	private String file = "/room.txt";

	// reflect
	private Area area;
	private Class<? extends Area> areaClass;
	private Field position;

	@Before
	public void setUp() throws Exception {
		String[][] matrix = new MatrixFileReader().readFile(file);
		if (matrix == null) {
			throw new NoSuchFileException(file);
		}
		area = new Area(matrix, new MatrixPosition(3, 3), 0);

		areaClass = area.getClass();

		try {
			position = areaClass.getDeclaredField("hooverPosition");
		} catch (NoSuchFieldException e) {
			fail("NoSuchFieldException : hooverPosition in area.");
		} catch (SecurityException e) {
			fail("SecurityException : initializing position reflector.");
		}

		position.setAccessible(true);
	}

	@Test
	public void cornersLimitTests() throws IllegalArgumentException, IllegalAccessException {
		MatrixPosition[] positions = { new MatrixPosition(2, 2), new MatrixPosition(5, 2), new MatrixPosition(5, 19),
				new MatrixPosition(2, 19) };

		for (MatrixPosition pos : positions) {
			position.set(area, pos);
			assertThat(area.availableDirections().size(), is(2));
		}
	}

	/**
	 * This test fails if the last character of the last line in not a carriage
	 * return. Simply because the method will count the '\r' at the end of each
	 * line and set ' ' to fill the last one.
	 * 
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void outsideLimitTest() throws IllegalArgumentException, IllegalAccessException {
		MatrixPosition[] positions = { new MatrixPosition(0, 0), new MatrixPosition(7, 0), new MatrixPosition(7, 21),
				new MatrixPosition(0, 21) };

		for (MatrixPosition pos : positions) {
			position.set(area, pos);
			assertThat(area.availableDirections().size(), is(0));
		}
	}

	@Test
	public void randomSituationTest() throws IllegalArgumentException, IllegalAccessException {
		HashMap<MatrixPosition, Integer> positions = new HashMap<MatrixPosition, Integer>();
		positions.put(new MatrixPosition(4, 13), 0);
		positions.put(new MatrixPosition(3, 8), 4);
		positions.put(new MatrixPosition(4, 5), 1);
		for (Iterator<Entry<MatrixPosition, Integer>> iterator = positions.entrySet().iterator(); iterator.hasNext();) {
			MatrixPosition currentPos = iterator.next().getKey();
			position.set(area, currentPos);
			assertThat(area.availableDirections().size(), is(positions.get(currentPos)));

		}

	}

	@Test
	public void enclavedPositionGivesDownDirection() throws IllegalArgumentException, IllegalAccessException {
		position.set(area, new MatrixPosition(4, 5));
		assertThat(area.availableDirections().iterator().next(), is(Direction.DOWN));
	}

	@Test
	public void numberOfFreeSpaceInRoomsTest() {
		assertThat(area.positionToClean(), is(63));
	}

	@Test
	public void rollTest() {
		fail("not yet implemented");
	}

}
