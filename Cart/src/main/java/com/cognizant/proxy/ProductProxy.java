package com.cognizant.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cognizant.exception.ProductNotFoundException;
import com.cognizant.model.Product;

@FeignClient(name = "product-rest-api",url = "http://localhost:9000")
public interface ProductProxy {
	
	@GetMapping("/product/searchProductById/{productId}")
	public Product getProductById(@PathVariable String productId) throws ProductNotFoundException;
	
	@GetMapping("/product")
	public List<Product> getAllProducts();

}
