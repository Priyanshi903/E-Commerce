package com.cognizant.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cognizant.bean.Product;


//@FeignClient(name = "product-rest-api", url="http://54.153.120.110:8080")
@FeignClient(name = "product-rest-api", url="http://localhost:8081")
public interface ProductProxy {

	@GetMapping("/product")
	public List<Product> getAllProducts();
	
	@GetMapping("/product/searchProductById/{productId}")
	public Product getProductById(@PathVariable String productId);
	
	@GetMapping("/product/searchProductByName/{productName}")
	public List<Product> searchProductByname(@PathVariable String productName);
	
	@PostMapping("/addProductRating/{productId}/{rating}")
	public boolean addProductRating(@PathVariable String productId,@PathVariable int rating);
	
}
