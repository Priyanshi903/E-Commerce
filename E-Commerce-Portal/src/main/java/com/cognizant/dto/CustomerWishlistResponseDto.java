package com.cognizant.dto;

import java.time.LocalDate;

import com.cognizant.bean.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is for sending customer wishlist
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerWishlistResponseDto {
	
	private long wishlist_id;

	private String email;
	
	private Product product;
	
	private LocalDate added_on;

}
