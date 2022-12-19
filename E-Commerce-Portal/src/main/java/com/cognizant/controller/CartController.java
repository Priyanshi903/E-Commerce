package com.cognizant.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.bean.Product;
import com.cognizant.dto.CartRequestDto;
import com.cognizant.dto.CartResponseDto;
import com.cognizant.dto.CustomerWishListRequestDTO;
import com.cognizant.dto.CustomerWishlistResponseDto;
import com.cognizant.proxy.AuthProxy;
import com.cognizant.proxy.CartProxy;
import com.cognizant.proxy.ProductProxy;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CartController {

	@Autowired
	private ProductProxy productProxy;

	@Autowired
	CartProxy cartProxy;

	@GetMapping("/search")
	public String getProduct(@RequestParam("product_name") String name, Model model) {

		List<Product> productList = new ArrayList<>();
		
		try {

			if (name.contains("product")) {
				Product product = productProxy.getProductById(name);
				productList.add(product);
			} else
				productList = productProxy.searchProductByname(name);
			
		} catch (Exception e) {
			
			log.info(e.getMessage());
			
		}

		model.addAttribute("productList", productList);

		return "product";
		
	}

	@PostMapping("/add-rating")
	public String addRatingToProduct(@ModelAttribute("product") Product product) {

		productProxy.addProductRating(product.getProduct_id(), product.getProduct_rating());

		return "redirect:/view-cart";

	}

	@GetMapping("/view-cart")
	public String getCart(HttpSession httpSession, Model model) {
		
		String token = "Bearer " + (String) httpSession.getAttribute("jwt");
		String email = (String) httpSession.getAttribute("email");

		List<CartResponseDto> cartList = cartProxy.getCart(token, email);
		model.addAttribute("cartList", cartList);

		model.addAttribute("product", new Product());

		return "cart";
		
	}
	
	@GetMapping("/add-to-cart")
	public String getProductPage(@RequestParam String id, Model model, HttpServletRequest httpServletRequest,
			@ModelAttribute("cartRequestDTO") CartRequestDto cartRequestDto) {
		
		Product product = productProxy.getProductById(id);

		model.addAttribute("product", product);
		model.addAttribute("name", httpServletRequest.getSession().getAttribute("name"));

		return "add-to-cart";

	}


	@PostMapping("/add")
	public String String(@ModelAttribute("cartRequestDTO") CartRequestDto cartRequestDto, HttpServletRequest request,
			Model model) {

		String token = "Bearer " + (String) request.getSession().getAttribute("jwt");
		String email = (String) request.getSession().getAttribute("email");

		if (email == null)
			return "redirect:/login";

		cartRequestDto.setEmail(email);
		String response = cartProxy.addProductToCart(token, cartRequestDto);

		if (response.equals("Successfully added to Cart"))
			return "redirect:/";

		model.addAttribute("outOfStock", response);
		model.addAttribute("product", productProxy.getProductById(cartRequestDto.getProduct_id()));

		return "add-to-cart";

	}

	@GetMapping("/delete")
	public String deleteFromCart(@RequestParam String id, HttpServletRequest request) {

		String token = "Bearer " + (String) request.getSession().getAttribute("jwt");
		String email = (String) request.getSession().getAttribute("email");
		
		cartProxy.deleteFromCart(token, email, id);

		return "redirect:/view-cart";

	}

	@GetMapping("/checkout")
	public String checkout(HttpSession httpSession, Model model) {

		String token = "Bearer " + (String) httpSession.getAttribute("jwt");
		String email = (String) httpSession.getAttribute("email");

		List<CartResponseDto> cartList = cartProxy.getCart(token, email);
		
		model.addAttribute("cartList", cartList);
		model.addAttribute("product", new Product());

		return "checkout";
		
	}

	
	
}
