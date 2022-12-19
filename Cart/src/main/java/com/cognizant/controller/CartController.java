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

import com.cognizant.dto.CartRequestDto;
import com.cognizant.dto.CartResponseDto;
import com.cognizant.exception.InvalidTokenException;
import com.cognizant.exception.ProductNotFoundException;
import com.cognizant.proxy.AuthProxy;
import com.cognizant.service.CartService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private AuthProxy authProxy;

	@PostMapping("/addProductToCart")
	@Operation(summary = "To add product to the cart")
	public String addProductToCart(@RequestHeader(name = "Authorization") String token,
			@RequestBody CartRequestDto cartRequestDto) throws InvalidTokenException {
		log.info("Add product to cart service started");
		if (authProxy.validate(token)) {
			log.info("Add product to cart service executed successfuly");
			return cartService.addProductToCart(cartRequestDto);
		} else
			throw new InvalidTokenException("You are not Logged In!!!");

	}

	@GetMapping("/{customerId}")
	@Operation(summary = "To get cart for user")
	public List<CartResponseDto> getCart(@RequestHeader(name = "Authorization") String token,
			@PathVariable String customerId) throws ProductNotFoundException, InvalidTokenException {
		log.info("get cart service started");
		if (authProxy.validate(token)) {
			log.info("get cartList by customer id service executed successfuly");
			return cartService.getCart(customerId);
		} else
			throw new InvalidTokenException("You are not Logged In!!!");
	}

	@DeleteMapping("/{email}/{productId}")
	@Operation(summary = "To delete product from the cart")
	public String deleteFromCart(@RequestHeader(name = "Authorization") String token, @PathVariable String email,
			@PathVariable String productId) throws InvalidTokenException {
		log.info("delete cart service started");
		if (authProxy.validate(token)) {
			log.info("delete cart service by customer id and product id executed successfuly");
			return cartService.removeFromCart(email, productId);
		} else
			throw new InvalidTokenException("You are not Logged In!!!");
	}

}
