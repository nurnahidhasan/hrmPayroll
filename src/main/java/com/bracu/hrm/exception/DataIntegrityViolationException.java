package com.bracu.hrm.exception;

import org.springframework.dao.DataAccessException;

public class DataIntegrityViolationException extends DataAccessException {

	/**
	 * Unique ID for Serialized object
	 */
	private static final long serialVersionUID = -8146834359701827537L;

	public DataIntegrityViolationException(String msg) {
		super(msg);
	}

	public DataIntegrityViolationException(String msg, Throwable t) {
		super(msg, t);
	}

}
