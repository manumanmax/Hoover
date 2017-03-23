package com.dydu.hoover.application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dydu.hoover.model.Area;
import com.dydu.hoover.model.Hoover;
import com.dydu.hoover.model.MatrixPosition;
import com.dydu.hoover.utils.LinearScan;
import com.dydu.hoover.utils.MatrixFileReader;

public class CleaningProcess {
	private static Area area;
	private static Hoover hoover;
	private static final Logger LOG = LoggerFactory.getLogger(CleaningProcess.class);

	public static void main(String[] args) {
		if (args.length != 1) {
			LOG.info("One and one only argument is required.");
		}
		if (!new File(args[0]).isFile()) {
			LOG.error(args[0] + " is not a correct file location.");
		}
		String[][] matrix = new MatrixFileReader().readFile(args[0]);
		MatrixPosition startPosition = getRandomPosition(matrix);
		if (startPosition == null) {
			LOG.error("No position is available to start cleaning.");
		}
		init(matrix, startPosition);

		hoover.setStrategy(new LinearScan());
		hoover.scanArea(area);
		hoover.printRoute(area);
	}

	private static MatrixPosition getRandomPosition(String[][] matrix) {
		MatrixPosition position;
		List<MatrixPosition> availablePositions = new ArrayList<MatrixPosition>();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j].equals(" ")) {
					availablePositions.add(new MatrixPosition(i, j));
				}
			}
		}
		if (availablePositions.isEmpty()) {
			return null;
		}
		// get a random position in the list of positions
		position = new MatrixPosition(availablePositions.get((int) (Math.random() * availablePositions.size() + 1)));

		return position;
	}

	private static void init(String[][] matrix, MatrixPosition startPosition) {
		BasicConfigurator.configure();

		String[][] matrixTmp = new String[matrix.length][matrix[0].length];
		int i = 0, j = 0;
		for (String[] line : matrix) {
			for (String cell : line) {
				matrixTmp[i][j++] = cell;
			}
			i++;
			j = 0;
		}
		Area tmpArea = new Area(matrixTmp, startPosition);
		area = new Area(matrix, startPosition, getNumberOfConnexePlacesToClean(tmpArea));
		hoover = new Hoover();

	}

	private static int getNumberOfConnexePlacesToClean(Area area) {
		return area.positionToClean() + 1;

	}

}
