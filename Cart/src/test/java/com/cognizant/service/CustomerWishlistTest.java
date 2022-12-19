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

import com.cognizant.dto.CustomerWishListRequestDTO;
import com.cognizant.dto.CustomerWishlistResponseDto;
import com.cognizant.exception.ProductNotFoundException;
import com.cognizant.model.CustomerWishlist;
import com.cognizant.repository.CustomerWishlistRepo;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerWishlist.class)
public class CustomerWishlistTest {
	
	@Autowired
	private CustomerWishlistService customerWishlistService;
	
	@MockBean
	private CustomerWishlistService customerWishlistServiceMock;
	
	@MockBean
	private CustomerWishlistRepo customerWishlistRepo;
	
	@MockBean
	private ProductService productService;
	
	@Test
	void contextLoads() {
		
	}
	
	@Test
	 void testSave() {
		CustomerWishListRequestDTO customerWishListReq = new CustomerWishListRequestDTO("priya@gmail.com", "product-010");
		String mssg="SuccessFully Added to WishList";
		when(customerWishlistServiceMock.addProductToWishlist(customerWishListReq)).thenReturn(mssg);
		assertEquals(customerWishlistServiceMock.addProductToWishlist(customerWishListReq), customerWishlistService.addProductToWishlist(customerWishListReq));
	}
	
	@Test
	void testGetWishlist() throws ProductNotFoundException {
		List<CustomerWishlistResponseDto> customerResponseWishlist=new ArrayList<>();
		assertEquals(customerWishlistServiceMock.getCustomerWishList("priya@gmail.com"), customerResponseWishlist);
	}

}
