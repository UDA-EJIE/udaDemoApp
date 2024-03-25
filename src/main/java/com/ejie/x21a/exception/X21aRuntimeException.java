package com.ejie.x21a.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class X21aRuntimeException extends RuntimeException{
	private static final Logger LOGGER = LoggerFactory.getLogger(X21aRuntimeException.class);
	
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 * @param cause
	 */
	public X21aRuntimeException(String message, Throwable cause) {
		super(message, cause);
		LOGGER.error(message, cause);
	}

	/**
	 * @param message
	 */
	public X21aRuntimeException(String message) {
		super(message);
		LOGGER.error(message);
	}

	/**
	 * @param cause
	 */
	public X21aRuntimeException(Throwable cause) {
		super(cause);
		LOGGER.error("ERROR", cause);
	}

}
