/*
 * Assignment 1: This file will perform threshold filtering.
 */

package image.processing;

import java.awt.Color;

public class ThresholdingOperation implements ImageOperation {

	private int numOfRows = 0;
	private int numOfColumns = 0;

	private int red = 0;
	private int green = 0;
	private int blue = 0;

	private double brightness = 0;

	@Override
	public Color[][] doOperation(Color[][] imageArray) {
		numOfRows = imageArray.length; // Width of the image
		numOfColumns = imageArray[0].length; // Height of the image
		Color[][] result = new Color[numOfRows][numOfColumns];

		// Make the background of the new image black
		for (int i = 0; i < numOfRows; i++) {
			for (int j = 0; j < numOfColumns; j++) {
				result[i][j] = new Color(0, 0, 0);
			}
		}

		// Go through each pixel of the original image
		for (int i = 0; i < numOfRows; i++) {
			for (int j = 0; j < numOfColumns; j++) {
				red = imageArray[i][j].getRed();
				green = imageArray[i][j].getGreen();
				blue = imageArray[i][j].getBlue();

				// Calculate the brightness score of a pixel
				brightness = (red * 0.21) + (0.71 * green) + (0.07 * blue);

				// If the brightness score of a pixel is greater than 100, change the color of the pixel to white
				if (brightness > 100) {
					result[i][j] = new Color(255, 255, 255);
				}
			}
		}
		return result;
	}

}
