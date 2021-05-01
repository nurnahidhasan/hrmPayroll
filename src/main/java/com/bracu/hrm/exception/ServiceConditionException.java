package com.bracu.hrm.exception;

/**
 * ServiceConditionException
 * @author Ripon Rana <rana771@gmail.com>
 */
public class ServiceConditionException extends RuntimeException {

	private boolean needToAlert = false;
	
	/**
	 * @param message
	 */
	public ServiceConditionException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ServiceConditionException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceConditionException(String message, Throwable cause) {
		super(message, cause);
	}
		
	public boolean isNeedToAlert() {
		return needToAlert;
	}

	public void setNeedToAlert(boolean needToAlert) {
		this.needToAlert = needToAlert;
	}

}
