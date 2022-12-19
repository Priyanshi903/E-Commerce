package com.cognizant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.model.Product;
import com.cognizant.repository.ProductRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RatingService {

	@Autowired
	private ProductRepo productRepo;

	public boolean addProductRating(String productId, int rating) {
		log.info("Going to add rating to the product.....");
		Product product = productRepo.findById(productId).get();
		if (product.getProduct_rating() == 0)
			product.setProduct_rating(rating);
		else
			product.setProduct_rating((product.getProduct_rating() + rating) / 2);
		productRepo.saveAndFlush(product);
		log.info("Product rating added Successfully.....");
		return true;
	}

}
