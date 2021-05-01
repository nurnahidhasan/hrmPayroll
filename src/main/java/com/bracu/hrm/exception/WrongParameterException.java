package com.bracu.hrm.exception;

/**
 * WrongParameterException
 * @author Ripon Rana <rana771@gmail.com>
 */
public class WrongParameterException extends Exception {
	
	/**
	 * @param message
	 */
	public WrongParameterException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public WrongParameterException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public WrongParameterException(String message, Throwable cause) {
		super(message, cause);
	}
}
