package com.cognizant.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.dto.CustomerWishListRequestDTO;
import com.cognizant.dto.CustomerWishlistResponseDto;
import com.cognizant.exception.ProductNotFoundException;
import com.cognizant.model.CustomerWishlist;
import com.cognizant.model.Product;
import com.cognizant.repository.CustomerWishlistRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerWishlistService {

	@Autowired
	private CustomerWishlistRepo wishlistRepo;
	
	@Autowired
	private ProductService productService;

	public String addProductToWishlist(CustomerWishListRequestDTO customerWishListReqDto) {
		log.info("Add product to wishlist service started");
		CustomerWishlist customerWishlistExists = wishlistRepo
				.findByEmailAndProduct(customerWishListReqDto.getCustomerId(), customerWishListReqDto.getProductId());
		if (customerWishlistExists != null) {
			log.info("Add product to wishlist service completed");
			return "Product added to wishlist SuccessFully!!!";
		} else {
			customerWishlistExists = new CustomerWishlist(customerWishListReqDto.getCustomerId(),
					customerWishListReqDto.getProductId(), LocalDate.now());
			wishlistRepo.saveAndFlush(customerWishlistExists);
			log.info("Add product to wishlist service completed");
			return "Product added to wishlist SuccessFully!!!";
		}

	}

	public List<CustomerWishlistResponseDto> getCustomerWishList(String customerId) throws ProductNotFoundException {
		log.info("get wishlist service method starts");
		List<CustomerWishlist> customerWishlist = new ArrayList<>();
		customerWishlist = wishlistRepo.findByEmail(customerId);
		List<CustomerWishlistResponseDto> customerResponseWishlist=new ArrayList<>();
		for(CustomerWishlist c:customerWishlist) {
			Product product=productService.getProductById(c.getProduct_id());
			customerResponseWishlist.add(new CustomerWishlistResponseDto(c.getWishlist_id(),c.getEmail(), product, c.getAdded_on()));
		}
		log.info("get wishlist service method completed");
		return customerResponseWishlist;
	}
	
	public String removeFromWishlist(String email,String productId) {
		log.info("delete from wishlist service method starts");
		CustomerWishlist customerWishlistExists = wishlistRepo
				.findByEmailAndProduct(email,productId);
		wishlistRepo.deleteById(customerWishlistExists.getWishlist_id());
		log.info("delete wishlist service method completed");
		return "Item removed from wishlist";
	}

}
