package com.cognizant.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.cognizant.exception.ProductAlreadyExistsException;
import com.cognizant.exception.ProductNotFoundException;
import com.cognizant.model.Product;
import com.cognizant.repository.ProductRepo;

@RunWith(SpringRunner.class)
@WebMvcTest({ProductService.class,RatingService.class})
public class ProductServiceTest {
	
	@MockBean
	private ProductRepo productRepo;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private RatingService ratingService;

	@Test
	void contextLoads() {
		
	}
	
	@Test
	void testGetProductById() throws ProductNotFoundException {
		Product product=new Product("001", "Apple", 90000,"image", "Iphone", 4);
		when(productRepo.findById("001")).thenReturn(Optional.of(product));
		assertEquals(productService.getProductById("001").getProduct_name(), product.getProduct_name());
	}
	
	@Test
	void testGetProductByName() throws ProductNotFoundException {
		Product product=new Product("001", "Apple", 90000,"image", "Iphone", 4);
		Product product2=new Product("002", "Apple", 90000,"image", "Iphone", 4);
		List<Product> productList=new ArrayList<>();
		productList.add(product2);
		productList.add(product);
		when(productRepo.findProductByProductName("Apple")).thenReturn(productList);
		assertEquals(productService.searchProductByname("Apple"), productList);
	}
	
	@Test
	void testGetProductByIdException() throws ProductNotFoundException {
		when(productRepo.findById("001")).thenReturn(Optional.empty());
		Exception exception = assertThrows(ProductNotFoundException.class, () -> productService.getProductById("001"));
		assertEquals("Product Not Found", exception.getMessage());
	}
	
	@Test
	void testGetProductByNameException() {
		List<Product> productList=new ArrayList<>();
		when(productRepo.findProductByProductName("Apple")).thenReturn(productList);
		Exception exception = assertThrows(ProductNotFoundException.class, () -> productService.searchProductByname("Apple"));
		assertEquals("Product not found", exception.getMessage());
	}
	
	@Test 
	void getAllProducts() throws ProductNotFoundException {
		Product product=new Product("001", "Apple", 90000,"image", "Iphone", 4);
		Product product2=new Product("002", "Apple", 90000,"image", "Iphone", 4);
		List<Product> productList=new ArrayList<>();
		productList.add(product2);
		productList.add(product);
		when(productRepo.findAll()).thenReturn(productList);
		assertEquals(productService.getAllProducts(), productList);
	}
	
	@Test
	void testAddProduct() throws ProductNotFoundException, ProductAlreadyExistsException {
		Product product=new Product("001", "Apple", 90000,"image", "Iphone", 4);
		when(productRepo.findById("001")).thenReturn(Optional.empty());
		assertTrue(productService.addProduct(product));
	}
	
	@Test
	void testAddRating() {
		Product product=new Product("001", "Apple", 90000,"image", "Iphone", 0);
		when(productRepo.findById("001")).thenReturn(Optional.of(product));
		ratingService.addProductRating("001", 3);
		assertEquals(product.getProduct_rating(), 3);
		ratingService.addProductRating("001", 5);
		assertEquals(product.getProduct_rating(), 4);
		
	}

}