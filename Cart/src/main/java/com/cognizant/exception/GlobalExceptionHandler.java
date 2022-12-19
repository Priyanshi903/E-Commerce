package com.cognizant.exception;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cognizant.model.CustomErrorResponse;

import feign.FeignException.FeignClientException;

/**
 * Global Exception Handler class
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handles user name null exception
	 *
	 * @param ex
	 * @return This returns the custom error message and the time stamp and the HTTP
	 *         status code
	 */

	/**
	 * Handles user name numeric exception
	 *
	 * @param ex
	 * @return This returns the custom error message and the time stamp and the HTTP
	 *         status code
	 */

	@ExceptionHandler({ ProductNotFoundException.class})
	public ResponseEntity<CustomErrorResponse> handlesProductNotFoundException(Exception exception) {

		CustomErrorResponse response = new CustomErrorResponse();

		response.setTimestamp(new Date());
		response.setStatus(HttpStatus.NOT_FOUND);
		response.setMessage(exception.getMessage());

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler({ InvalidTokenException.class,FeignClientException.class })
	public ResponseEntity<CustomErrorResponse> handlesFeignException(Exception exception) {

		CustomErrorResponse response = new CustomErrorResponse();

		response.setTimestamp(new Date());
		response.setStatus(HttpStatus.FORBIDDEN);
		response.setMessage("You are not Logged In!!!");

		return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();

		body.put("timestamp", new Date());

		body.put("status", status.value());

// Get all validation errors

		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

// Add errors to the response map

		body.put("errors", errors);

		return new ResponseEntity<>(body, headers, status);

	}

}