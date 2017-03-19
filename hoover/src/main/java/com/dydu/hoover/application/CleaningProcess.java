package com.dydu.hoover.application;

import java.util.Arrays;

import org.apache.log4j.BasicConfigurator;
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
		System.out.println(Arrays.toString(args));
		BasicConfigurator.configure();
		try {
			Area tmpArea = new Area(new MatrixFileReader().readFile(args[0]),
					new MatrixPosition(Integer.parseInt(args[1]), Integer.parseInt(args[2])));
			area = new Area(new MatrixFileReader().readFile(args[0]),
					new MatrixPosition(Integer.parseInt(args[1]), Integer.parseInt(args[2])),
					getNumberOfConnexePlacesToClean(area));
			hoover = new Hoover(area.freePositions());
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

	private static MatrixPosition getNumberOfConnexePlacesToClean(Area area) {
		// TODO Auto-generated method stub
		return null;
	}

}
