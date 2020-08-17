/*
 * Assignment 1: This file will double the size of the original image.
 */

package image.processing;

import java.awt.Color;

public class MagnifyOperation implements ImageOperation {
	
	private int numOfRows = 0;
	private int numOfColumns = 0;
	
	private int newRow = 0;
	private int newColumn = 0;
	
	private int red = 0;
	private int green = 0;
	private int blue = 0;

	@Override
	public Color[][] doOperation(Color[][] imageArray) {
		numOfRows = imageArray.length; // Width of the image
		numOfColumns = imageArray[0].length; // Height of the image
		// This array will have twice as many rows and columns for storing the new image
		Color[][] newResult = new Color[numOfRows * 2][numOfColumns * 2];

		// Go through each pixel of the original image
		for (int i = 0; i < numOfRows; i++) {
			for (int j = 0; j < numOfColumns; j++) {
				newRow = i * 2;
				newColumn = j * 2;

				red = imageArray[i][j].getRed();
				green = imageArray[i][j].getGreen();
				blue = imageArray[i][j].getBlue();

				// To double the size of the image, for every pixel from the original image, we
				// will copy that pixel into four positions for the new image
				newResult[newRow][newColumn] = new Color(red, green, blue);
				newResult[newRow][newColumn + 1] = new Color(red, green, blue);
				newResult[newRow + 1][newColumn] = new Color(red, green, blue);
				newResult[newRow + 1][newColumn + 1] = new Color(red, green, blue);
			}
		}

		return newResult;
	}

}
