package com.cognizant.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class VendorExceptionAop {

	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = {Exception.class})
	public ErrorInfo handleException(Exception exception, HttpServletRequest request) {
		String message = exception.getMessage();
		String uri = request.getRequestURI().toString();
		return new ErrorInfo(uri, message);
	}
}
