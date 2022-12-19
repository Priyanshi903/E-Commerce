package com.cognizant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.exception.ProductNotFoundException;
import com.cognizant.model.Product;
import com.cognizant.proxy.ProductProxy;

@Service
public class ProductService {
	
	@Autowired
	private ProductProxy productProxy;
	
	public Product getProductById(String productId) throws ProductNotFoundException {
		return productProxy.getProductById(productId);
	}

}
