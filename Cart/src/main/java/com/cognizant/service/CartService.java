package com.cognizant.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.dto.CartRequestDto;
import com.cognizant.dto.CartResponseDto;
import com.cognizant.exception.ProductNotFoundException;
import com.cognizant.model.Cart;
import com.cognizant.model.Product;
import com.cognizant.model.Vendor;
import com.cognizant.repository.CartRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartService {

	@Autowired
	private CartRepo cartRepo;

	@Autowired
	private VendorService vendorService;

	@Autowired
	private ProductService productService;

	public String addProductToCart(CartRequestDto cartRequestDto) {
		log.info("addToCart service started execution");
		Vendor vendor = vendorService.getVendorForProduct(cartRequestDto.getProduct_id(),cartRequestDto.getQuantity());
		log.info("vendor:::::::::::{}",vendor);
		log.info("Vendore call got successfuly");
		if (vendor.getVendor_id() == null) {
			log.info("Vendore Detail is Empty");
			return "Product not in stock";
		}
		Cart cartExists = cartRepo.exists(cartRequestDto.getEmail(), cartRequestDto.getProduct_id());
		if (cartExists != null) {
			cartExists.setQuantity(cartExists.getQuantity() + cartRequestDto.getQuantity());
			cartRepo.saveAndFlush(cartExists);
			log.info("successfully added in the cart");
			return "Successfully added to Cart";
		}
		LocalDate delivery_date = LocalDate.now().plusDays(7);
		Cart newCart = new Cart(cartRequestDto.getEmail(), cartRequestDto.getProduct_id(), vendor.getVendor_id(),
				cartRequestDto.getZipcode(), delivery_date, cartRequestDto.getQuantity());
		log.info("new cart:::::::::::{}",newCart);
		cartRepo.saveAndFlush(newCart);
		return "Successfully added to Cart";

	}

	public List<CartResponseDto> getCart(String customerId) throws ProductNotFoundException {
		log.info("getCartList method started execution");
		log.debug("getCartList() called");
		List<CartResponseDto> cartResponseList=new ArrayList<>();
		List<Cart> cartList=cartRepo.findByCustomerEmail(customerId);
		for(Cart cart : cartList) {
			Product product =productService.getProductById(cart.getProduct_id());
			Vendor vendor=vendorService.getVendor(cart.getVendor_id());
			cartResponseList.add(new CartResponseDto(cart.getCart_id(), cart.getEmail(), product, vendor, cart.getZipcode(), cart.getDelivery_date(), cart.getQuantity()));
			
		}
		return cartResponseList;
	}
	
	public String removeFromCart(String email,String productId) {
		log.info("get customer wishlist method started execution");
		Cart cartExists = cartRepo.exists(email, productId);
		cartRepo.deleteById(cartExists.getCart_id());
		log.info("get customer wishlist method completed successfully");
		return "Item removed from cart";
	}

}
