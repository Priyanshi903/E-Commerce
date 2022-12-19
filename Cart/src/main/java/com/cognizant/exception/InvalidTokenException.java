package com.cognizant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "You are not authorized")
public class InvalidTokenException extends Exception {
	public InvalidTokenException(String mssg) {
		super(mssg);
	}

}
