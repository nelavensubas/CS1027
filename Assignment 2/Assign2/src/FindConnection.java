/*
 * Assignment 2: This file will find a possible connection
 * from the Western Power Company (WPC) to a new customer (C).
 */

import java.io.FileNotFoundException;
import java.io.IOException;

public class FindConnection {
	/**
	 * Reference the object representing the city map where WPC and C are located
	 */
	private Map cityMap;

	/**
	 * Constructor will the display the city map on the screen
	 * 
	 * @param filename name of the file containing the description of the city map
	 */
	public FindConnection(String filename) throws InvalidMapException, FileNotFoundException, IOException {
		cityMap = new Map(filename);
	}

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("You must provide the name of the input file.");
			System.exit(0);
		}
		String mapFileName = args[0];

		try {
			// Create an object of the class FindConnection
			FindConnection input = new FindConnection(mapFileName);
			ArrayStack<MapCell> stack = new ArrayStack<MapCell>();

			// Push the starting cell (WPC cell) to the stack
			stack.push(input.cityMap.getStart());
			input.cityMap.getStart().markInStack();

			// Use this boolean to check if the customer cell has been found
			boolean destination = false;
			while (!stack.isEmpty() && !destination) {
				MapCell best = input.bestCell(stack.peek());

				if (best != null) {
					stack.push(best);
					best.markInStack();
					if (best.isCustomer()) {
						destination = true;
					}
				} else if (best == null) {
					stack.peek().markOutStack();
					stack.pop();
				}
			}

			if (!destination) {
				System.out.println("The customer destination cannot be reached.");
			} else {
				System.out.println("The customer destination was reached.");
				System.out.println("Number of cells in the path: " + Integer.toString(stack.size()));
			}
		} catch (InvalidMapException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * bestCell method to figure out the best cell to continue the path from the
	 * current one
	 * 
	 * @param cell the current cell
	 * @return the best cell
	 */
	private MapCell bestCell(MapCell cell) {
		int c = 4;
		MapCell customer = null;

		int o = 4;
		MapCell omni = null;

		int h = 4;
		MapCell horizontalSwitch = null;

		int v = 4;
		MapCell verticalSwitch = null;

		for (int i = 0; i < 4; i++) {
			if (cell.getNeighbour(i) != null) {
				// Check if the neighbour is a customer cell and has not been marked yet
				if (cell.getNeighbour(i).isCustomer() && i < c && !cell.getNeighbour(i).isMarked()) {
					// If the current cell is a horizontal switch and the neighbour cell
					// has been indexed either 1 or 3. This checked is necessary as the
					// a horizontal switch can only travel to index 1 or 3.
					if (cell.isHorizontalSwitch() && (i == 1 || i == 3)) {
						c = i;
						customer = cell.getNeighbour(i);
						// If the current cell is a vertical switch and the neighbour cell
						// has been indexed either 0 or 2. This checked is necessary as the
						// a vertical switch can only travel to index 0 or 2.
					} else if (cell.isVerticalSwitch() && (i == 0 || i == 2)) {
						c = i;
						customer = cell.getNeighbour(i);
						// If the current cell is not a horizontal switch or vertical switch
						// it should not have a problem traveling from indexes between 0 and 3
					} else if (!cell.isHorizontalSwitch() && !cell.isVerticalSwitch()) {
						c = i;
						customer = cell.getNeighbour(i);
					}
					// Check if the neighbour is an omni cell and has not been marked yet
				} else if (cell.getNeighbour(i).isOmniSwitch() && i < o && !cell.getNeighbour(i).isMarked()) {
					if (cell.isHorizontalSwitch() && (i == 1 || i == 3)) {
						o = i;
						omni = cell.getNeighbour(i);
					} else if (cell.isVerticalSwitch() && (i == 0 || i == 2)) {
						o = i;
						omni = cell.getNeighbour(i);
					} else if (!cell.isHorizontalSwitch() && !cell.isVerticalSwitch()) {
						o = i;
						omni = cell.getNeighbour(i);
					}
					// Check if the neighbour is a horizontal switch and the current cell is not a
					// vertical switch since a vertical switch can't travel to a horizontal switch
				} else if (cell.getNeighbour(i).isHorizontalSwitch() && !cell.isVerticalSwitch() && i < h
						&& (i == 1 || i == 3) && !cell.getNeighbour(i).isMarked()) {
					h = i;
					horizontalSwitch = cell.getNeighbour(i);
					// Check if the neighbour is a vertical switch and the current cell is not a
					// horizontal switch since a horizontal switch can't travel to a vertical switch
				} else if (cell.getNeighbour(i).isVerticalSwitch() && !cell.isHorizontalSwitch() && i < v
						&& (i == 0 || i == 2) && !cell.getNeighbour(i).isMarked()) {
					v = i;
					verticalSwitch = cell.getNeighbour(i);
				}
			}
		}

		if (customer != null) {
			return customer;
		} else if (omni != null) {
			return omni;
		} else if (h < v) {
			return horizontalSwitch;
		} else if (v < h) {
			return verticalSwitch;
		} else {
			return null;
		}
	}
}