package com.cognizant.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.cognizant.model.Product;
import com.cognizant.repository.ProductRepo;
import com.cognizant.service.ProductService;
import com.cognizant.service.RatingService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest({ProductController.class,RatingController.class})
public class ProductControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ProductService productService;

	@MockBean
	private ProductRepo productRepo;
	
	@MockBean
	private RatingService ratingService;

	@Test
	void testGetProductById() throws Exception {
		Product product = new Product("1", "Apple", 90000,"image", "Iphone", 4);
		doReturn(product).when(productService).getProductById("1");
		mvc.perform(get("/product/searchProductById/{productId}", "1").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.product_name", is("Apple")));
	}

	@Test
	void testGetProductByIdNotPresent() throws Exception {
		mvc.perform(get("/product/searchProductById/product-010").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isNotFound());
	}

	@Test
	void testGetProductByName() throws Exception {
		Product product = new Product("001", "Apple", 90000,"image", "Iphone", 4);
		Product product2 = new Product("002", "Apple", 45000, "image","8GB", 3);
		List<Product> productList = new ArrayList<>();
		productList.add(product2);
		productList.add(product);
		doReturn(productList).when(productService).searchProductByname("Apple");
		mvc.perform(get("/product/searchProductByName/Apple").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
		MvcResult mvcResult = mvc.perform(get("/product/searchProductByName/Apple")).andReturn();
		assertEquals(true, mvcResult.getResponse().getContentAsString().contains("8GB"));
		assertEquals(true, mvcResult.getResponse().getContentAsString().contains("Apple"));
	}

	@Test
	void testGetProductByNameException() throws Exception {
		mvc.perform(get("/product/searchProductById/Samsung F41").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isNotFound());
	}

	@Test
	void testGetAllProducts() throws Exception {
		mvc.perform(get("/product/").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void testAddProduct() throws Exception {
		mvc.perform(post("/product").content(convertObjectToJsonString(new Product("001", "Apple", 90000,"image", "Iphone", 4)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}

	public static String convertObjectToJsonString(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void testAddRating() throws Exception {
		doReturn(true).when(ratingService).addProductRating("001", 3);
		mvc.perform(post("/addProductRating/{productId}/{rating}","001",3)).andDo(print())
				.andExpect(status().isOk());

	}

}
