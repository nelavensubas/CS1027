/*
 * Assignment 3: This class will implement a doubly linked list
 * in which the nodes are of the class DLNode.
 */

public class DLList<T> implements DLListADT<T> {
	/**
	 * Reference to the first node of the doubly linked list
	 */
	private DLNode<T> front;
	/**
	 * Reference to the last node of the doubly linked list
	 */
	private DLNode<T> rear;
	/**
	 * Number of data items in the linked list
	 */
	private int count;

	/**
	 * Constructor creates an empty list.
	 */
	public DLList() {
		front = rear = null;
		count = 0;
	}

	/**
	 * insert method add a new DLNode to the rear of doubly linked list
	 * 
	 * @param dataItem the new data item to be added
	 * @param value    the new value to be added
	 */
	@Override
	public void insert(T dataItem, int value) {
		// Create the new data item to put in the list
		DLNode<T> newItem = new DLNode<T>(dataItem, value);

		// If the doubly linked list is empty, then make the new node the front and the
		// rear since it's the first one
		if (front == null) {
			front = rear = newItem;
			front.setPrevious(null);
			rear.setNext(null);
		} else {
			// Make the node that rear is currently referencing make a reference to the new
			// node
			rear.setNext(newItem);
			newItem.setPrevious(rear);
			// Make rear reference the new node
			rear = newItem;
			rear.setNext(null);
		}

		count++;
	}

	/**
	 * getDataValue method gets the value associated to the specified data item
	 * 
	 * @param dataItem the specific data item
	 * @return the value of the specific data item
	 */
	@Override
	public int getDataValue(T dataItem) throws InvalidDataItemException {
		// Get a reference to the first node in the doubly linked list
		DLNode<T> start = front;

		boolean found = false;

		// Scan the doubly linked list using linear search to check whether the data
		// item exists or not
		while (start != null) {
			if (start.getData().equals(dataItem)) {
				found = true;
				break;
			}
			// Use this statement to go to the next node
			start = start.getNext();
		}

		// Invalid data item exception is thrown if the item cannot be found
		if (!found) {
			throw new InvalidDataItemException("The data item does not exist.");
		} else {
			return start.getValue();
		}
	}

	/**
	 * changeValue method changes the value of the data item to the new value
	 * 
	 * @param dataItem the specific data item to change the value
	 * @param newValue the new value for the specified data item
	 */
	@Override
	public void changeValue(T dataItem, int newValue) throws InvalidDataItemException {
		DLNode<T> start = front;

		boolean found = false;

		while (start != null) {
			if (start.getData().equals(dataItem)) {
				start.setValue(newValue);
				found = true;
				break;
			}
			start = start.getNext();
		}

		if (!found) {
			throw new InvalidDataItemException("The data item does not exist.");
		}
	}

	/**
	 * getSmallest method gets and removes the data item in the list with the
	 * smallest value
	 * 
	 * @return the data item with the smallest value
	 */
	@Override
	public T getSmallest() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException("The doubly linked list is empty.");
		}

		DLNode<T> start = front;
		DLNode<T> remove = front;

		T temp = front.getData();
		int smallestVal = start.getValue();

		while (start != null) {
			if (start.getValue() < smallestVal) {
				temp = start.getData();
				smallestVal = start.getValue();
				remove = start;
			}
			start = start.getNext();
		}

		// Removing the data item
		if (front == remove) {
			front = remove.getNext();
		}
		if (remove.getNext() != null) {
			// Get the next node and set it's previous to the node that is before the
			// node that will be removed
			remove.getNext().setPrevious(remove.getPrevious());
		}
		if (remove.getPrevious() != null) {
			// Get the previous node and set it's next to the node that is after the
			// node that will be removed
			remove.getPrevious().setNext(remove.getNext());
		}

		count--;

		return temp;
	}
	
	/**
	 * isEmpty method checks to see if the doubly linked list is
	 * empty or not
	 * 
	 * @return true if the stack is empty or return false if it's not
	 */
	@Override
	public boolean isEmpty() {
		return count == 0;
	}

	/**
	 * size method tells the user how many data items are in the
	 * doubly linked list
	 * 
	 * @return the amount of data items in the doubly linked list
	 */
	@Override
	public int size() {
		return count;
	}
	
	/**
	 * toString method displays a string representation of the
	 * doubly linked list
	 * 
	 * @return the string representation of the doubly linked list
	 */
	@Override
	public String toString() {
		DLNode<T> start = front;

		String str = "List: ";

		while (start != null) {
			str += start.getData() + ", " + start.getValue() + ";";
			start = start.getNext();
		}

		return str;
	}

}
