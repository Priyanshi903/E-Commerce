package com.cognizant.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is for adding and deleting product from wishlist
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerWishListRequestDTO {


	private String customerId;
	private String productId;

}
