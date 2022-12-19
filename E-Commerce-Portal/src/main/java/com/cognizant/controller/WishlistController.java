package com.cognizant.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.dto.CustomerWishListRequestDTO;
import com.cognizant.dto.CustomerWishlistResponseDto;
import com.cognizant.proxy.CartProxy;

@Controller
public class WishlistController {
	
	@Autowired
	CartProxy cartProxy;

	@GetMapping("/wishlist")
	public String getWishList(HttpSession httpSession, Model model) {

		String token = "Bearer " + (String) httpSession.getAttribute("jwt");
		String email = (String) httpSession.getAttribute("email");

		List<CustomerWishlistResponseDto> cartList = cartProxy.getCustomerWishList(token, email);
		model.addAttribute("cartList", cartList);

		return "wishlist";

	}

	@GetMapping("/add-to-wishlist")
	public String addToWishList(@RequestParam String id, Model model, HttpServletRequest request) {

		String token = "Bearer " + (String) request.getSession().getAttribute("jwt");
		String email = (String) request.getSession().getAttribute("email");

		if (email == null)
			return "redirect:/login";

		CustomerWishListRequestDTO customerWishListRequestDTO = new CustomerWishListRequestDTO(email, id);

		cartProxy.addProductToWishlist(token, customerWishListRequestDTO);

		return "redirect:/";

	}

	@GetMapping("/remove-from-wishlist")
	public String removeFromWishList(@RequestParam String id, Model model, HttpServletRequest request) {

		String token = "Bearer " + (String) request.getSession().getAttribute("jwt");
		String email = (String) request.getSession().getAttribute("email");
		
		cartProxy.deleteFromWishlist(token, email, id);

		return "redirect:/wishlist";

	}
}
