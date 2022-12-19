package com.cognizant.exception;

/**
 * Class for handling UserNotFoundException
 */
public class InvalidPasswordException extends RuntimeException {

	public InvalidPasswordException(String msg) {
		super(msg);
	}

	/**
	 * This method sets the custom error message
	 * 
	 * @param message
	 */
	private static final long serialVersionUID = 1L;

}
