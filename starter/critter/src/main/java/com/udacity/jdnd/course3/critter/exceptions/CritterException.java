package com.udacity.jdnd.course3.critter.exceptions;

/**
 * @author GiapNT
 *
 */
public class CritterException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String[] arguments;

	public CritterException() {
		super();
	}

	public CritterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CritterException(String message, Throwable cause) {
		super(message, cause);
	}

	public CritterException(String message) {
		super(message);
	}

	public CritterException(Throwable cause) {
		super(cause);
	}

	public String[] getArguments() {
		return arguments;
	}

	public void setArguments(String[] arguments) {
		this.arguments = arguments;
	}
}