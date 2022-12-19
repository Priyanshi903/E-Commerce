package com.cognizant.exception;

@SuppressWarnings("serial")
public class VendorNotFoundException extends Exception {

	public VendorNotFoundException() {
		super();
	}

	public VendorNotFoundException(String message) {
		super(message);
	}
}
