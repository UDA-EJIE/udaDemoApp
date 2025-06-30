/**
 * The file AA79bRuntimeException.java.
 */
package com.ejie.aa79b.common.exceptions;

/**
 * The type .
 * 
 * @author llaparra
 * 
 */
public class AppRuntimeException extends RuntimeException {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The constructor for AA79bRuntimeException.
	 */
	public AppRuntimeException() {
		super();
	}

	/**
	 * The constructor for AA79bRuntimeException.
	 * 
	 * @param message
	 *            String
	 * @param cause
	 *            Throwable
	 */
	public AppRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * The constructor for AA79bRuntimeException.
	 * 
	 * @param message
	 *            String
	 */
	public AppRuntimeException(String message) {
		super(message);
	}

	/**
	 * The constructor for AA79bRuntimeException.
	 * 
	 * @param cause
	 *            Throwable
	 */
	public AppRuntimeException(Throwable cause) {
		super(cause);
	}

}
