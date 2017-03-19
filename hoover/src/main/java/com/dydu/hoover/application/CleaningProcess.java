package com.dydu.hoover.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dydu.hoover.model.Area;
import com.dydu.hoover.model.Hoover;
import com.dydu.hoover.model.MatrixPosition;
import com.dydu.hoover.utils.LinearScan;
import com.dydu.hoover.utils.MatrixFileReader;

public class CleaningProcess {
	private static final Logger LOG = LoggerFactory.getLogger(MatrixFileReader.class);
	private static Area area;
	private static Hoover hoover;

	public static void main(String[] args) {
		try {
			area = new Area(new MatrixFileReader().readFile(args[1]));
			hoover = new Hoover(new MatrixPosition(Integer.parseInt(args[2]), Integer.parseInt(args[3])),
					area.freePositions());
		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error("Verify that you set the path of the matrix" + ", first, and the position of the hoover in "
					+ "argument to start cleaning.", e);
			return;
		} catch (NumberFormatException e) {
			LOG.error("The position in parameter is incorrect.");
		}

		hoover.setStrategy(new LinearScan());
		hoover.scanArea(area);

	}

}
