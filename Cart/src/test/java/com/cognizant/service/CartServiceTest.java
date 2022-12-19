package com.cognizant.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.dto.CartRequestDto;
import com.cognizant.dto.CartResponseDto;
import com.cognizant.exception.ProductNotFoundException;
import com.cognizant.repository.CartRepo;

@RunWith(SpringRunner.class)
@WebMvcTest(CartService.class)
public class CartServiceTest {
	
	@Autowired
	private CartService cartService;
	
	@MockBean
	private CartService cartServiceMock;
	
	@MockBean
	private CartRepo cartRepo;
	
	@MockBean
	private VendorService vendorService;
	
	@MockBean
	private ProductService productService;
	
	@Test
	void contextLoads() {
		
	}
	
	@Test
	void testCartAddedSuccessfully() {
		CartRequestDto cartRequestDto = new CartRequestDto("priya@gmail.com", "001", 123456, 2);
		String msg = "Successfully added to Cart";
		when(cartServiceMock.addProductToCart(cartRequestDto)).thenReturn(msg);
		assertEquals(cartServiceMock.addProductToCart(cartRequestDto), cartService.addProductToCart(cartRequestDto));
	}
	
	@Test
	void testVendorIsNotHavingEnoughStocks() {
		CartRequestDto cartRequestDto = new CartRequestDto("priya@gmail.com", "001", 123456, 2);
		String msg = "Not Enough Stocks";
		when(cartServiceMock.addProductToCart(cartRequestDto)).thenReturn(msg);
		assertEquals(cartServiceMock.addProductToCart(cartRequestDto), cartService.addProductToCart(cartRequestDto));
	}

	@Test
	void testGetCartList() throws ProductNotFoundException {
		String customerId ="priya@gmail.com" ;
		List<CartResponseDto> list = new ArrayList<>();
		assertEquals(list, cartService.getCart(customerId));
	}


}
