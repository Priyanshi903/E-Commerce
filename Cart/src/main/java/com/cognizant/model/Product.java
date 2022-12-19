package com.cognizant.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * This class is for product entity from product microservice
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {
	
	@Id
	private String product_id;
	
	private String product_name;
	
	private double product_price;
	
	private String product_image;
	
	private String product_description;
	
	private int product_rating;
}
