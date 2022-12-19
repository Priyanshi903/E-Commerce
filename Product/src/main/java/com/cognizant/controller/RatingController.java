package com.cognizant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.service.RatingService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/addProductRating/{productId}/{rating}")
public class RatingController {
	
	@Autowired
	private RatingService ratingService;
	
	@PostMapping
	@Operation(summary = "Add rating to the Product")
	public boolean addProductRating(@PathVariable String productId,@PathVariable int rating) {
		return ratingService.addProductRating(productId,rating);
	}

}
