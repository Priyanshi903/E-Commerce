package com.cognizant.exception;

@SuppressWarnings("serial")
public class VendorStockNotFoundException extends Exception {

	public VendorStockNotFoundException() {
		super();
	}

	public VendorStockNotFoundException(String message) {
		super(message);
	}
}