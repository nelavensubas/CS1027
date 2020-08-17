/*
 * Assignment 2: This file will implement a stack using
 * an array.
 */

public class ArrayStack<T> implements ArrayStackADT<T> {
	/**
	 * Store the data items in the stack
	 */
	private T[] stack;
	/**
	 * Stores the position of the last data item in the stack
	 */
	private int top;

	/**
	 * Constructor creates an empty stack with a default size for the array to store
	 * items for the stack.
	 */
	public ArrayStack() {
		stack = (T[]) new Object[20];
		top = -1;
	}

	/**
	 * Constructor creates an empty stack with an array of length equal to the value
	 * of the parameter.
	 * 
	 * @param initialCapacity length of the array
	 */
	public ArrayStack(int initialCapacity) {
		stack = (T[]) new Object[initialCapacity];
		top = -1;
	}

	/**
	 * push method to add data items to the stack
	 * 
	 * @param dataItem the item to add to the stack
	 */
	@Override
	public void push(T dataItem) {
		// Increase the capacity of the array
		// Double the size of the array if the current capacity is less than 100
		if (length() == size() && stack.length < 100) {
			T[] expandArray = (T[]) (new Object[stack.length * 2]);
			// Copy all the data items from the old stack to the new stack
			for (int index = 0; index < stack.length; index++) {
				expandArray[index] = stack[index];
			}
			stack = expandArray;
			// Increase the size of the array by 50 if the current capacity is greater
			// than or equal to 100
		} else if (length() == size() && stack.length >= 100) {
			T[] expandArray = (T[]) (new Object[stack.length + 50]);
			for (int index = 0; index < stack.length; index++) {
				expandArray[index] = stack[index];
			}
			stack = expandArray;
		}

		top++;
		stack[top] = dataItem;
	}

	/**
	 * pop method to remove the data item at the top of the stack
	 * 
	 * @return the data item that has been removed
	 */
	@Override
	public T pop() throws EmptyStackException {
		// EmptyStackException is thrown if the stack is empty
		if (isEmpty()) {
			throw new EmptyStackException("The stack is empty.");
		}

		T result = stack[top];
		stack[top] = null;

		top--;

		// Shrink the size of the array by half if the number of the data items
		// remaining is smaller than one third of the length of the array
		// and the length of the array is larger than 20
		if (top + 1 == Math.floor((stack.length / 3)) && stack.length > 20) {
			T[] expandArray = (T[]) (new Object[stack.length / 2]);
			for (int index = 0; index < top + 1; index++) {
				expandArray[index] = stack[index];
			}
			stack = expandArray;
		}

		return result;
	}

	/**
	 * peek method shows the data item at the top of the stack without doing
	 * anything to it
	 * 
	 * @return the data item that is at the top of the stack
	 */
	@Override
	public T peek() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException("The stack is empty.");
		}
		return stack[top];
	}

	/**
	 * isEmpty method tells the user if the stack is empty or not
	 * 
	 * @return true if the stack is empty or return false if it's not
	 */
	@Override
	public boolean isEmpty() {
		return top == -1;
	}

	/**
	 * size method shows the amount of data items in the stack
	 * 
	 * @return the number of data items in the stack
	 */
	@Override
	public int size() {
		return top + 1;
	}

	/**
	 * toString method displays a string representation of the stack
	 * 
	 * @return string representation of the stack
	 */
	public String toString() {
		String result = "Stack: ";

		// Go through each data item and print it out
		for (int i = 0; i <= top; i++) {
			if (i != top) {
				result += stack[i] + ", ";
			} else if (i == top) {
				result += stack[i];
			}
		}

		return result;
	}

	/**
	 * length method shows the capacity of the array stack
	 * 
	 * @return the length of the array stack
	 */
	public int length() {
		return stack.length;
	}
}
