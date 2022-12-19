package com.cognizant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is for adding and deleting product from cart
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequestDto {
	
	private String email;
	private String product_id;
	private int zipcode;
	private int quantity;

}

/*
 * { "email":"priya@gmail.com", "product_id":"product-013", "zipcode":1234,
 * "quantity":2 }
 */