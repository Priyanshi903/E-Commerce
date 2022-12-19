package com.cognizant.dto;

import java.time.LocalDate;

import com.cognizant.model.Product;
import com.cognizant.model.Vendor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is for getting cart for user
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDto {
	
	private long cart_id;
	
	private String email;
	
	private Product product;
	
	private Vendor vendor;
	
	private int zipcode;
	
	private LocalDate delivery_date;
	
	private int quantity;

}
