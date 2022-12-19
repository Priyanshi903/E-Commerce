package com.cognizant.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * This class is for cart entity
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart")
public class Cart {
	
	@Id
	@GeneratedValue
	private long cart_id;
	
	private String email;
	
	private String product_id;
	
	private String vendor_id;
	
	private int zipcode;
	
	private LocalDate delivery_date;
	
	private int quantity;

	public Cart(String email, String product_id, String vendor_id, int zipcode, LocalDate delivery_date,
			int quantity) {
		super();
		this.email = email;
		this.product_id = product_id;
		this.vendor_id = vendor_id;
		this.zipcode = zipcode;
		this.delivery_date = delivery_date;
		this.quantity = quantity;
	}
	
}

