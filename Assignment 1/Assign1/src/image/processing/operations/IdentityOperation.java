package image.processing.operations;

import java.awt.Color;

import image.processing.ImageOperation;

public class IdentityOperation implements ImageOperation {

	public Color[][] doOperation(Color[][] imageArray) {
		return imageArray;
	}

}
