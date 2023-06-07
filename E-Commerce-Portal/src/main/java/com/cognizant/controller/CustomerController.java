package com.cognizant.controller;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cognizant.bean.Customer;
import com.cognizant.bean.Product;
import com.cognizant.dto.UserCredentials;
import com.cognizant.proxy.AuthProxy;
import com.cognizant.proxy.ProductProxy;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CustomerController implements ErrorController {

	@Autowired
	private ProductProxy productProxy;

	@Autowired
	private AuthProxy authProxy;
	
//	@GetMapping("/")
//	public String getHomePage() {
//		return "hello";
//	}

	@GetMapping("/")
	public String getHomePage(Model model, HttpServletRequest session) {

		String name = (String) session.getSession().getAttribute("name");
		List<Product> allProducts = productProxy.getAllProducts();
		
		model.addAttribute("allProducts", allProducts);
		model.addAttribute("name", name);

		return "home";
		
	}
	
	@GetMapping("/login")
	public String getLoginPage(@ModelAttribute("userCerdentials") UserCredentials userCredentials) {
		
		return "login";
		
	}

	@PostMapping("/login")
	public String loginTheUser(@ModelAttribute("userCerdentials") UserCredentials userCredentials,
			HttpServletRequest request, Model model) {
		
		try {

			String jwt = authProxy.login(userCredentials);
			request.getSession().setAttribute("jwt", jwt);

			Customer customer = authProxy.getCustomerUsingJwtToken("Bearer " + jwt);
			
			request.getSession().setAttribute("name", customer.getUsername());
			request.getSession().setAttribute("email", customer.getEmail());

			return "redirect:/";

		} catch (Exception e) {
			
			model.addAttribute("error", "Check email and password");
			return "login";
			
		}
	}
	
	
	
	@GetMapping("/logout")
	public String getHome(HttpServletRequest httpServletRequest) throws ServletException {
		
		httpServletRequest.getSession().setAttribute("name", null);
		httpServletRequest.getSession().setAttribute("jwt", null);
		httpServletRequest.getSession().setAttribute("email", null);
		
		return "redirect:/";
		
	}

	@GetMapping("/signup")
	public String getSignUpPage(@ModelAttribute("customer") Customer customer) {
		
		return "signup";
		
	}

	@PostMapping("/register")
	public String registerNewCutomer(@ModelAttribute("customer") Customer customer,Model model) {
		
		try {
			
			String email = authProxy.getCustomerUsingEmailId(customer.getEmail());
			log.info(email);
			
			if(!email.equals("No user found"))
				throw new Exception();
			
			authProxy.registernewCustomer(customer);
			return "redirect:/login";
			
		}catch(Exception e) {
			
			model.addAttribute("CustomerExits", "Mail Id already exists.");
			return "signup";
			
		}		
		
	}
	
	@RequestMapping(value="/404")
	public String errprPage() {
		
		return "404";
		
	}

}
