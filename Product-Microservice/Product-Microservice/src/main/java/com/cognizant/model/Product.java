package com.cognizant.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is for product entity
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {
	
	@Id
	private String product_id;
	
	@NotNull(message = "Product Name Expected")
	private String product_name;
	
	@NotNull(message = "Product price can't be left empty")
	private double product_price;
	
//	@NotNull(message = "Please add Product image")
//	private String product_image;
	
	@NotNull(message = "Please provide product description")
	private String product_description;
	
	private int product_rating;
}