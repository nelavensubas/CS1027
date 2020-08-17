/*
 * Assignment 3: This is the file that will find the shortest path
 * from the Western Power Company (WPC) to a new customer (C).
 */

import java.io.FileNotFoundException;
import java.io.IOException;

public class ShortestPath {
	/**
	 * Reference the object representing the city map where WPC and C are located
	 */
	private Map cityMap;

	/**
	 * Constructor will the display the city map on the screen
	 * 
	 * @param filename name of the file containing the description of the city map
	 */
	public ShortestPath(String filename) throws InvalidMapException, FileNotFoundException, IOException {
		cityMap = new Map(filename);
	}

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("You must provide the name of the input file.");
			System.exit(0);
		}
		String mapFileName = args[0];

		try {
			// Create an object of the class ShortestPath
			ShortestPath shortest = new ShortestPath(mapFileName);
			DLList<MapCell> nodes = new DLList<MapCell>();

			// Push the starting cell (WPC cell) to the doubly linked list
			MapCell current = shortest.cityMap.getStart();
			nodes.insert(current, 0);

			// smallest will contain the cell with the smallest distance value
			MapCell smallest = current;

			current.markInList();

			// Use this boolean to check if the customer cell has been found
			boolean destination = false;

			while (!nodes.isEmpty() && !destination) {
				// Remove from the doubly linked list the cell with the smallest distance
				smallest = nodes.getSmallest();
				smallest.markOutList();

				if (current != null) {
					if (smallest.isCustomer()) {
						destination = true;
					} else {
						while (shortest.nextCell(smallest) != null) {
							// Get the best neighbouring cell
							MapCell currNeighbour = nextCell(smallest);
							// Get the distance from the cell between the smallest one and the initial cell
							int distance = 1 + smallest.getDistanceToStart();

							// Check if the distance between the best neighbour and the power station is
							// greater than distance
							if (currNeighbour.getDistanceToStart() > distance) {
								currNeighbour.setDistanceToStart(distance);
								currNeighbour.setPredecessor(smallest);
							}

							// Store the distance from the power station to neighbour
							int neighbourDist = currNeighbour.getDistanceToStart();
							if (currNeighbour.isMarkedInList() && neighbourDist < nodes.getDataValue(currNeighbour)) {
								nodes.changeValue(currNeighbour, neighbourDist);
							}
							if (!currNeighbour.isMarkedInList()) {
								nodes.insert(currNeighbour, neighbourDist);
								currNeighbour.markInList();
							}
						}
					}
				}
			}

			if (!destination) {
				System.out.println("The customer destination cannot be reached.");
			} else {
				System.out.println("The customer destination was reached.");
				System.out.println("Number of cells in the path: " + (smallest.getDistanceToStart() + 1));
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
	 * nextCell method to figure out the cell that has the lowest index and can
	 * continue the path from the current one
	 * 
	 * @param cell the current cell
	 * @return the next cell
	 */
	private static MapCell nextCell(MapCell cell) {
		MapCell smallestCell = null;

		for (int i = 0; i < 4; i++) {
			if (cell.getNeighbour(i) != null) {
				// Check if the neighbour is a customer cell and has not been marked yet
				if (cell.getNeighbour(i).isCustomer() && !cell.getNeighbour(i).isMarked()) {
					// If the current cell is a horizontal switch and the neighbour cell
					// has been indexed either 1 or 3. This checked is necessary as the
					// a horizontal switch can only travel to index 1 or 3.
					if (cell.isHorizontalSwitch() && (i == 1 || i == 3)) {
						smallestCell = cell.getNeighbour(i);
						break;
						// If the current cell is a vertical switch and the neighbour cell
						// has been indexed either 0 or 2. This checked is necessary as the
						// a vertical switch can only travel to index 0 or 2.
					} else if (cell.isVerticalSwitch() && (i == 0 || i == 2)) {
						smallestCell = cell.getNeighbour(i);
						break;
						// If the current cell is not a horizontal switch or vertical switch
						// it should not have a problem traveling from indexes between 0 and 3
					} else if (!cell.isHorizontalSwitch() && !cell.isVerticalSwitch()) {
						smallestCell = cell.getNeighbour(i);
						break;
					}
					// Check if the neighbour is an omni cell and has not been marked yet
				} else if (cell.getNeighbour(i).isOmniSwitch() && !cell.getNeighbour(i).isMarked()) {
					if (cell.isHorizontalSwitch() && (i == 1 || i == 3)) {
						smallestCell = cell.getNeighbour(i);
						break;
					} else if (cell.isVerticalSwitch() && (i == 0 || i == 2)) {
						smallestCell = cell.getNeighbour(i);
						break;
					} else if (!cell.isHorizontalSwitch() && !cell.isVerticalSwitch()) {
						smallestCell = cell.getNeighbour(i);
						break;
					}
					// Check if the neighbour is a horizontal switch and the current cell is not a
					// vertical switch since a vertical switch can't travel to a horizontal switch
				} else if (cell.getNeighbour(i).isHorizontalSwitch() && !cell.isVerticalSwitch() && (i == 1 || i == 3)
						&& !cell.getNeighbour(i).isMarked()) {
					smallestCell = cell.getNeighbour(i);
					break;
					// Check if the neighbour is a vertical switch and the current cell is not a
					// horizontal switch since a horizontal switch can't travel to a vertical switch
				} else if (cell.getNeighbour(i).isVerticalSwitch() && !cell.isHorizontalSwitch() && (i == 0 || i == 2)
						&& !cell.getNeighbour(i).isMarked()) {
					smallestCell = cell.getNeighbour(i);
					break;
				}
			}
		}

		return smallestCell;
	}
}
