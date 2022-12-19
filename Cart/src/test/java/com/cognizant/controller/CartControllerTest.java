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
import com.cognizant.dto.CartRequestDto;
import com.cognizant.dto.CartResponseDto;
import com.cognizant.proxy.AuthProxy;
import com.cognizant.service.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(CartController.class)
public class CartControllerTest {

	@MockBean
	private CartService cartService;

	@Autowired
	private MockMvc mock;

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
	void testAddProductToCart() throws Exception {
		CartRequestDto cartRequestDto = new CartRequestDto("priya@gmail.com", "product-010", 123456, 5);
		String jsonCartRequestDto = convertObjectToJsonString(cartRequestDto);
		when(cartService.addProductToCart(cartRequestDto)).thenReturn("Successfully added to Cart");
		MvcResult mvcResult = mock.perform(post("/cart/addProductToCart").header("Authorization", "user1")
				.contentType(MediaType.APPLICATION_JSON).content(jsonCartRequestDto)).andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());

	}

	@Test
	void testGetCartList() throws Exception {
		List<CartResponseDto> cartlist = new ArrayList<CartResponseDto>();
		when(cartService.getCart("priya@gmail.com")).thenReturn(cartlist);
		MvcResult mvcResult = mock.perform(get("/cart/{customerId}/", "priya@gmail.com")
				.header("Authorization", "user1")).andReturn();
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
