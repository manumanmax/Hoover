package com.dydu.hoover.model.test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.nio.file.NoSuchFileException;

import org.junit.Before;
import org.junit.Test;

import com.dydu.hoover.model.Area;
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
		assertThat(area.availablePositions(new MatrixPosition(2, 2)).size(), is(2));
		assertThat(area.availablePositions(new MatrixPosition(5, 2)).size(), is(2));
		assertThat(area.availablePositions(new MatrixPosition(5, 19)).size(), is(2));
		assertThat(area.availablePositions(new MatrixPosition(2, 19)).size(), is(2));
	}

}
