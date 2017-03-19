package com.dydu.hoover.model.test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import com.dydu.hoover.model.Direction;
import com.dydu.hoover.model.Hoover;
import com.dydu.hoover.model.MatrixPosition;

public class HooverTest {

	private Hoover hoover;
	private Class<? extends Hoover> hooverClass;
	private Method move;
	private Field position;

	@Before
	public void setUp() {
		hoover = new Hoover(new MatrixPosition(3, 3), 1);
		hooverClass = hoover.getClass();

		try {
			move = hooverClass.getDeclaredMethod("move", Direction.class);
		} catch (NoSuchMethodException e) {
			fail("NoSuchMethodException : move in Hoover.");
		} catch (SecurityException e) {
			fail("SecurityException : initializing move reflector.");
		}
		move.setAccessible(true);

		try {
			position = hooverClass.getDeclaredField("position");
		} catch (NoSuchFieldException e) {
			fail("NoSuchFieldException : position in Hoover.");
		} catch (SecurityException e) {
			fail("SecurityException : initializing position reflector.");
		}

		position.setAccessible(true);

	}

	@Test
	public void moveTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		move.invoke(hoover, Direction.UP);
		assertThat((MatrixPosition) position.get(hoover), is(new MatrixPosition(2, 3)));
	}

}
