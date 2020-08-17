/*
 * Assignment 3: This class represents the nodes of a doubly
 * linked list, where each node stores a data item and an
 * associated integer value.
 */

public class DLNode<T> {
	/**
	 * Reference to the data item stored in this node
	 */
	private T dataItem;
	/**
	 * Reference to the next node in the linked list
	 */
	private DLNode<T> next;
	/**
	 * Reference to the previous node in the linked list
	 */
	private DLNode<T> previous;
	/**
	 * The value of the data item stored in this node
	 */
	private int value;

	/**
	 * Constructor initializes a node storing the given data and value.
	 * 
	 * @param data  the data item for the node
	 * @param value the value for the node
	 */
	public DLNode(T data, int value) {
		dataItem = data;
		this.value = value;
		next = previous = null;
	}

	/**
	 * getValue method to get the value of the node
	 * 
	 * @return the value of the node
	 */
	public int getValue() {
		return value;
	}

	/**
	 * getData method to get the data item of the node
	 * 
	 * @return the data item of the node
	 */
	public T getData() {
		return dataItem;
	}

	/**
	 * getNext method to get the node after the current node
	 * 
	 * @return the node after the the current node
	 */
	public DLNode<T> getNext() {
		return next;
	}

	/**
	 * getPrevious method to get the node before the current node
	 * 
	 * @return the before the current node
	 */
	public DLNode<T> getPrevious() {
		return previous;
	}

	/**
	 * setData method to change the data item for the current node
	 * 
	 * @param dataItem the new data item
	 */
	public void setData(T dataItem) {
		this.dataItem = dataItem;
	}

	/**
	 * setValue method to change the value of the current node
	 * 
	 * @param value the new value for the current node
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * setNext method to change the node after the current node
	 * 
	 * @param next the new node that will created after the current node
	 */
	public void setNext(DLNode<T> next) {
		this.next = next;
	}

	/**
	 * setPrevious method to change the node before the current node
	 * 
	 * @param previous the new node that will be created before the current node
	 */
	public void setPrevious(DLNode<T> previous) {
		this.previous = previous;
	}
}
