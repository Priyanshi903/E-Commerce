package com.cognizant.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.exception.ProductAlreadyExistsException;
import com.cognizant.exception.ProductNotFoundException;
import com.cognizant.model.Product;
import com.cognizant.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/product")
@CrossOrigin()
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("searchProductById/{productId}")
	@Operation(summary = "To get Product by Id")
	public Product getProductById(@PathVariable String productId) throws ProductNotFoundException {
		return productService.getProductById(productId);
	}
	
	@GetMapping
	@Operation(summary = "Get all products")
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@PostMapping
	@Operation(summary = "Add product")
	public boolean addProduct(@RequestBody @Valid Product product) throws ProductAlreadyExistsException {
		return productService.addProduct(product);
	}
	
	@DeleteMapping("/{productId}")
	@Operation(summary = "Delete Product")
	public boolean deleteProduct(@PathVariable String productId) throws ProductNotFoundException {
		return productService.deleteProduct(productId);
	}
	
	@PutMapping
	@Operation(summary = "Update Product")
	public boolean updateProduct(@RequestBody @Valid Product product) throws ProductNotFoundException {
		return productService.updateProduct(product);
	}
	
	@GetMapping("searchProductByName/{productName}")
	@Operation(summary = "Search Product by Name")
	public List<Product> searchProductByname(@PathVariable String productName) throws ProductNotFoundException{
		return productService.searchProductByname(productName);
	}

}
