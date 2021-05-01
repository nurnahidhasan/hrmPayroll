package com.bracu.hrm.exception;

/**
 * NotFoundException
 * @author Ripon Rana <rana771@gmail.com>
 */
public class NotFoundException extends Exception {

	/**
	 * @param message
	 */
	public NotFoundException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public NotFoundException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
