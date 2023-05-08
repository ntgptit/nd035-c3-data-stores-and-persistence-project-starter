package com.udacity.jdnd.course3.critter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author GiapNT
 *
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Employee not found")
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1041220383152339297L;

	public ResourceNotFoundException() {
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
