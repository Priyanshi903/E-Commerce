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
 * This class is for Customer wishlist
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer_wishlist")
public class CustomerWishlist {
	
	@Id
	@GeneratedValue
	private long wishlist_id;
	
	public CustomerWishlist(String email, String product_id, LocalDate added_on) {
		super();
		this.email = email;
		this.product_id = product_id;
		this.added_on = added_on;
	}

	private String email;
	
	private String product_id;
	
	private LocalDate added_on;
	
}
