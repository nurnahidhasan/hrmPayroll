package com.bracu.hrm.exception;

/**
 * SystemException
 * @author Ripon Rana <rana771@gmail.com>
 */
public class SystemException extends RuntimeException {

	private boolean needToAlert = true;
	
	/**
	 * @param message
	 */
	public SystemException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public SystemException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public boolean isNeedToAlert() {
		return needToAlert;
	}

	public void setNeedToAlert(boolean needToAlert) {
		this.needToAlert = needToAlert;
	}

}
