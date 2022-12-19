package com.cognizant.proxy;

import java.util.List;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;



import com.cognizant.dto.CartRequestDto;
import com.cognizant.dto.CartResponseDto;
import com.cognizant.dto.CustomerWishListRequestDTO;
import com.cognizant.dto.CustomerWishlistResponseDto;

//@FeignClient(name = "cart-rest-api",url="http://54.67.94.211:8083")
@FeignClient(name = "cart-rest-api",url="http://localhost:8083")
public interface CartProxy {

@GetMapping("cart/{customerId}")
public List<CartResponseDto> getCart(@RequestHeader(name = "Authorization") String token,
@PathVariable String customerId);

@PostMapping("cart/addProductToCart")
public String addProductToCart(@RequestHeader(name = "Authorization") String token,
@RequestBody CartRequestDto cartRequestDto);

@DeleteMapping("cart/{email}/{productId}")
public String deleteFromCart(@RequestHeader(name = "Authorization") String token, @PathVariable String email,
@PathVariable String productId);

@PostMapping("/wishlist")
public String addProductToWishlist(@RequestHeader(name = "Authorization") String token,
@RequestBody CustomerWishListRequestDTO wishlistDto);

@GetMapping("wishlist/{customerId}")
public List<CustomerWishlistResponseDto> getCustomerWishList(@RequestHeader(name = "Authorization") String token,
@PathVariable String customerId);

@DeleteMapping("wishlist/{email}/{productId}")
public String deleteFromWishlist(@RequestHeader(name = "Authorization") String token, @PathVariable String email,
@PathVariable String productId);



}