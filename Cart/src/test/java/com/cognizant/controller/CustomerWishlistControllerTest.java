package com.cognizant.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.cognizant.dto.CustomerWishListRequestDTO;
import com.cognizant.dto.CustomerWishlistResponseDto;
import com.cognizant.proxy.AuthProxy;
import com.cognizant.service.CustomerWishlistService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerWishlistController.class)
public class CustomerWishlistControllerTest {

	@Autowired
	private MockMvc mock;
	
	@MockBean
	private CustomerWishlistService customerWishlistService;
	
	@MockBean
	private AuthProxy authProxy;
	
	@Test
	void contextLoads() {

	}

	@BeforeEach()
	void setUp() {
		when(authProxy.validate("user1")).thenReturn(true);
	}
	
	@Test
	 void testAddToCustomerWishList() throws Exception {
		CustomerWishListRequestDTO customerWish = new CustomerWishListRequestDTO("priya@gmail.com", "001");
		String jsonCustomerWish = convertObjectToJsonString(customerWish);
		String mssg="Product added to wishlist SuccessFully!!!";
		when(customerWishlistService.addProductToWishlist(customerWish))
				.thenReturn(mssg);
		MvcResult mvcResult = mock.perform(
				post("/wishlist").header("Authorization", "user1").contentType(MediaType.APPLICATION_JSON).content(jsonCustomerWish))
				.andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());
		assertEquals(true, mvcResult.getResponse().getContentAsString().contains("Product added to wishlist SuccessFully!!!"));

	}
	
	@Test
	 void testViewAllWishlist() throws Exception {
		List<CustomerWishlistResponseDto> list = new ArrayList<CustomerWishlistResponseDto>();
		when(customerWishlistService.getCustomerWishList("priya@gmail.com")).thenReturn(list);
		MvcResult mvcResult = mock.perform(get("/wishlist/priya@gmail.com").header("Authorization", "user1")).andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());
	}
	
	public static String convertObjectToJsonString(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
