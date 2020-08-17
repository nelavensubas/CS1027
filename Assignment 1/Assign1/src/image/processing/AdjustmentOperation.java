/*
 * Assignment 1: This file will adjust the brightness of each
 * pixel based on its distance to the upper-left corner of the image.
 */

package image.processing;

import java.awt.Color;

public class AdjustmentOperation implements ImageOperation {

	private int numOfRows = 0;
	private int numOfColumns = 0;

	private double maxD = 0;

	private double distance = 0;
	private double adjustBrightness = 0;

	private int red = 0;
	private int green = 0;
	private int blue = 0;

	@Override
	public Color[][] doOperation(Color[][] imageArray) {
		numOfRows = imageArray.length; // Width of the image
		numOfColumns = imageArray[0].length; // Height of the image
		Color[][] result = new Color[numOfRows][numOfColumns];

		// Calculate the maximum distance from any pixel to the upper left corner of the image
		maxD = Math.sqrt(Math.pow(numOfRows, 2) + Math.pow(numOfColumns, 2));

		// Go through each pixel of the original image
		for (int i = 0; i < numOfRows; i++) {
			for (int j = 0; j < numOfColumns; j++) {
				// Calculate the distance of the current pixel to the upper left corner of the image
				distance = Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2));

				adjustBrightness = distance / maxD;
				red = (int) (imageArray[i][j].getRed() * adjustBrightness);
				green = (int) (imageArray[i][j].getGreen() * adjustBrightness);
				blue = (int) (imageArray[i][j].getBlue() * adjustBrightness);

				result[i][j] = new Color(red, green, blue);
			}
		}

		return result;
	}

}
