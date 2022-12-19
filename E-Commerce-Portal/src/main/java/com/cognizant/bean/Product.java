package com.cognizant.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	private String product_id;
	private String product_name;
	private double product_price;
	private String product_image;
	private String product_description;
	private int product_rating;
	
}