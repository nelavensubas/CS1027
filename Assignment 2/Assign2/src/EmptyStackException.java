/*
 * Assignment 2: This file is the class of exceptions
 * that will be thrown by methods pop and peek when invoked
 * on a empty stack.
 */

public class EmptyStackException extends RuntimeException {
	/**
	 * Constructor will display a message if the stack is empty
	 * 
	 * @param message the message to display to the user
	 */
	public EmptyStackException(String message) {
		super(message);
	}
}
