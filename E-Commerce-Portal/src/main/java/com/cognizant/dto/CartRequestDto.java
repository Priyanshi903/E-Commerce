package com.cognizant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartRequestDto {

	private String email;
	private String product_id;
	private int zipcode;
	private int quantity;

}