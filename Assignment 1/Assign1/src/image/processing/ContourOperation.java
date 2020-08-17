/*
 * Assignment 1: This file will perform countour detection.
 */

package image.processing;

import java.awt.Color;

public class ContourOperation implements ImageOperation {

	private int numOfRows = 0;
	private int numOfColumns = 0;
	
	private int pointTwoX = 0;
	private int pointTwoY = 0;
	
	private double distance = 0;
	
	@Override
	public Color[][] doOperation(Color[][] imageArray) {
		numOfRows = imageArray.length; // Width of the image
		numOfColumns = imageArray[0].length; // Height of the image
		Color[][] result = new Color[numOfRows][numOfColumns];

		// Make the background of the new image white
		for (int i = 0; i < numOfRows; i++) {
			for (int j = 0; j < numOfColumns; j++) {
				result[i][j] = new Color(255, 255, 255);
			}
		}

		// Go through each pixel of the original image
		for (int i = 0; i < numOfRows; i++) {
			for (int j = 0; j < numOfColumns; j++) {
				// 1st neighbour
				// Checks to see if the pixel in the upper left corner of the current pixel exists
				if (i - 1 >= 0 && j - 1 >= 0) {
					pointTwoX = i - 1;
					pointTwoY = j - 1;

					// Calculate the color distance between two pixels
					distance = Math.sqrt(Math.pow(
							(imageArray[i][j].getRed() - imageArray[pointTwoX][pointTwoY].getRed()), 2)
							+ Math.pow((imageArray[i][j].getGreen()
									- imageArray[pointTwoX][pointTwoY].getGreen()), 2)
							+ Math.pow((imageArray[i][j].getBlue()
									- imageArray[pointTwoX][pointTwoY].getBlue()), 2));

					// If the color distance is greater than 65, change the color of the pixel to black
					if (distance > 65) {
						result[i][j] = new Color(0, 0, 0);
					}
				}

				// 2nd neighbour
				// Checks to see if the pixel above the current pixel exists
				if (j - 1 >= 0) {
					pointTwoX = i;
					pointTwoY = j - 1;

					distance = Math.sqrt(Math.pow(
							(imageArray[i][j].getRed() - imageArray[pointTwoX][pointTwoY].getRed()), 2)
							+ Math.pow((imageArray[i][j].getGreen()
									- imageArray[pointTwoX][pointTwoY].getGreen()), 2)
							+ Math.pow((imageArray[i][j].getBlue()
									- imageArray[pointTwoX][pointTwoY].getBlue()), 2));

					if (distance > 65) {
						result[i][j] = new Color(0, 0, 0);
					}
				}

				// 3rd neighbour
				// Checks to see if the pixel in the upper right corner of the current pixel exists
				if (i + 1 < numOfRows && j - 1 >= 0) {
					pointTwoX = i + 1;
					pointTwoY = j - 1;

					distance = Math.sqrt(Math.pow(
							(imageArray[i][j].getRed() - imageArray[pointTwoX][pointTwoY].getRed()), 2)
							+ Math.pow((imageArray[i][j].getGreen()
									- imageArray[pointTwoX][pointTwoY].getGreen()), 2)
							+ Math.pow((imageArray[i][j].getBlue()
									- imageArray[pointTwoX][pointTwoY].getBlue()), 2));

					if (distance > 65) {
						result[i][j] = new Color(0, 0, 0);
					}
				}

				// 4th neighbour
				// Checks to see if the pixel on the left side of the current pixel exists
				if (i - 1 >= 0) {
					pointTwoX = i - 1;
					pointTwoY = j;

					distance = Math.sqrt(Math.pow(
							(imageArray[i][j].getRed() - imageArray[pointTwoX][pointTwoY].getRed()), 2)
							+ Math.pow((imageArray[i][j].getGreen()
									- imageArray[pointTwoX][pointTwoY].getGreen()), 2)
							+ Math.pow((imageArray[i][j].getBlue()
									- imageArray[pointTwoX][pointTwoY].getBlue()), 2));

					if (distance > 65) {
						result[i][j] = new Color(0, 0, 0);
					}
				}

				// 5th neighbour
				// Checks to see if the pixel on right side of the current pixel exists
				if (i + 1 < numOfRows) {
					pointTwoX = i + 1;
					pointTwoY = j;

					distance = Math.sqrt(Math.pow(
							(imageArray[i][j].getRed() - imageArray[pointTwoX][pointTwoY].getRed()), 2)
							+ Math.pow((imageArray[i][j].getGreen()
									- imageArray[pointTwoX][pointTwoY].getGreen()), 2)
							+ Math.pow((imageArray[i][j].getBlue()
									- imageArray[pointTwoX][pointTwoY].getBlue()), 2));

					if (distance > 65) {
						result[i][j] = new Color(0, 0, 0);
					}
				}

				// 6th neighbour
				// Checks to see if the pixel on the bottom left corner from the current pixel exists
				if (i - 1 >= 0 && j + 1 < numOfColumns) {
					pointTwoX = i - 1;
					pointTwoY = j + 1;

					distance = Math.sqrt(Math.pow(
							(imageArray[i][j].getRed() - imageArray[pointTwoX][pointTwoY].getRed()), 2)
							+ Math.pow((imageArray[i][j].getGreen()
									- imageArray[pointTwoX][pointTwoY].getGreen()), 2)
							+ Math.pow((imageArray[i][j].getBlue()
									- imageArray[pointTwoX][pointTwoY].getBlue()), 2));

					if (distance > 65) {
						result[i][j] = new Color(0, 0, 0);
					}
				}

				// 7th neighbour
				// Checks to see if the pixel below the current pixel exists
				if (j + 1 < numOfColumns) {
					pointTwoX = i;
					pointTwoY = j + 1;

					distance = Math.sqrt(Math.pow(
							(imageArray[i][j].getRed() - imageArray[pointTwoX][pointTwoY].getRed()), 2)
							+ Math.pow((imageArray[i][j].getGreen()
									- imageArray[pointTwoX][pointTwoY].getGreen()), 2)
							+ Math.pow((imageArray[i][j].getBlue()
									- imageArray[pointTwoX][pointTwoY].getBlue()), 2));

					if (distance > 65) {
						result[i][j] = new Color(0, 0, 0);
					}
				}

				// 8th neighbour
				// Checks to see if the pixel in the bottom right corner of the current pixel exists
				if (i + 1 < numOfRows && j + 1 < numOfColumns) {
					pointTwoX = i + 1;
					pointTwoY = j + 1;

					distance = Math.sqrt(Math.pow(
							(imageArray[i][j].getRed() - imageArray[pointTwoX][pointTwoY].getRed()), 2)
							+ Math.pow((imageArray[i][j].getGreen()
									- imageArray[pointTwoX][pointTwoY].getGreen()), 2)
							+ Math.pow((imageArray[i][j].getBlue()
									- imageArray[pointTwoX][pointTwoY].getBlue()), 2));

					if (distance > 65) {
						result[i][j] = new Color(0, 0, 0);
					}
				}
			}
		}

		return result;
	}

}
