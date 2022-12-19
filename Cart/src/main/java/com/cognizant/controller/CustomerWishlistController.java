package com.cognizant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.dto.CustomerWishListRequestDTO;
import com.cognizant.dto.CustomerWishlistResponseDto;
import com.cognizant.exception.InvalidTokenException;
import com.cognizant.exception.ProductNotFoundException;
import com.cognizant.proxy.AuthProxy;
import com.cognizant.service.CustomerWishlistService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/wishlist")
@Slf4j
public class CustomerWishlistController {

	@Autowired
	private CustomerWishlistService cusWishlistService;

	@Autowired
	private AuthProxy authProxy;

	@PostMapping
	@Operation(summary = "To add product to the wishlist")
	public String addProductToWishlist(@RequestHeader(name = "Authorization") String token,
			@RequestBody CustomerWishListRequestDTO wishlistDto) throws InvalidTokenException {
		log.info("Add customer wishList service started");
		if (authProxy.validate(token)) {
			return cusWishlistService.addProductToWishlist(wishlistDto);
		} else
			throw new InvalidTokenException("You are not Logged In!!!");
	}

	@GetMapping("/{customerId}")
	@Operation(summary = "To get wishlist for the user")
	public List<CustomerWishlistResponseDto> getCustomerWishList(@RequestHeader(name = "Authorization") String token,
			@PathVariable String customerId) throws ProductNotFoundException, InvalidTokenException {
		log.info("get customer wishlist service started");
		if (authProxy.validate(token)) {
			log.info("get customer wishlist service call ended");
			return cusWishlistService.getCustomerWishList(customerId);
		}

		else
			throw new InvalidTokenException("You are not Logged In!!!");

	}

	@DeleteMapping("/{email}/{productId}")
	@Operation(summary = "To delete product from the wishlist")
	public String deleteFromWishlist(@RequestHeader(name = "Authorization") String token, @PathVariable String email,
			@PathVariable String productId) throws InvalidTokenException {
		log.info("delete product from wishlist by customer id and product id service started");
		if (authProxy.validate(token)) {
			log.info("delete product from wishlist by customer id and product id service executed successfuly");
			return cusWishlistService.removeFromWishlist(email, productId);
		}

		else
			throw new InvalidTokenException("You are not Logged In!!!");
	}

}
