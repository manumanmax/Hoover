package com.dydu.hoover.model.test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.nio.file.NoSuchFileException;

import org.junit.Before;
import org.junit.Test;

import com.dydu.hoover.model.Area;
import com.dydu.hoover.model.Direction;
import com.dydu.hoover.model.MatrixPosition;
import com.dydu.hoover.utils.MatrixFileReader;

public class AreaTest {

	private Area area;
	private String file = "/room.txt";

	@Before
	public void setUp() throws Exception {
		String[][] matrix = new MatrixFileReader().readFile(file);
		if (matrix == null) {
			throw new NoSuchFileException(file);
		}
		area = new Area(matrix);
	}

	@Test
	public void cornersLimitTests() {
		assertThat(area.availableDirections(new MatrixPosition(2, 2)).size(), is(2));
		assertThat(area.availableDirections(new MatrixPosition(5, 2)).size(), is(2));
		assertThat(area.availableDirections(new MatrixPosition(5, 19)).size(), is(2));
		assertThat(area.availableDirections(new MatrixPosition(2, 19)).size(), is(2));
	}

	/**
	 * This test fails if the last character of the last line in not a carriage
	 * return. Simply because the method will count the '\r' at the end of each
	 * line and set ' ' to fill the last one.
	 */
	@Test
	public void outsideLimitTest() {
		assertThat(area.availableDirections(new MatrixPosition(0, 0)).size(), is(0));
		assertThat(area.availableDirections(new MatrixPosition(7, 0)).size(), is(0));
		assertThat(area.availableDirections(new MatrixPosition(7, 21)).size(), is(0));
		assertThat(area.availableDirections(new MatrixPosition(0, 21)).size(), is(0));
	}

	@Test
	public void randomSituationTest() {
		assertThat(area.availableDirections(new MatrixPosition(4, 13)).size(), is(0));
		assertThat(area.availableDirections(new MatrixPosition(3, 8)).size(), is(4));
		assertThat(area.availableDirections(new MatrixPosition(4, 5)).size(), is(1));
	}

	@Test
	public void enclavedPositionGivesDownDirection() {
		assertThat(area.availableDirections(new MatrixPosition(4, 5)).iterator().next(), is(Direction.DOWN));
	}

	@Test
	public void numberOfFreeSpaceInRoomsTest() {
		assertThat(area.freePositions(), is(65));
	}
}
