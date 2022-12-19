package com.cognizant.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.model.Customer;
import com.cognizant.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller to handle the customer data
 * 
 * @author 2063201
 *
 */

@RestController()
@Slf4j
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AuthController authController;

	/**
	 * URL to register new users to the application
	 * 
	 * @param customer
	 * @return
	 */

	@PostMapping("/signup")
	@Operation(summary = "To create the Custoemr in the database")
	public Boolean registernewCustomer(@RequestBody @Valid Customer customer) {

		log.info("Start");

		customerService.registerCustomer(customer);

		log.info("End");
		return Boolean.TRUE;

	}

	/**
	 * Returns the customer data based on username present in the token.
	 * 
	 * @param token
	 * @return
	 */

	@GetMapping("/get")
	@Operation(summary = "To get the Customer")
	public Customer getCustomerUsingJwtToken(@RequestHeader(name = "Authorization") String token) {

		log.info("Start");

		if (authController.validate(token).hasBody()) {

			log.info("Token valid. Returing customer details");
			return customerService.getCustomer(token);

		}

		log.info("Token Invalid. Unable to get the customer");
		return null;

	}

	/**
	 * Update the details of the user
	 * 
	 * @param customer
	 * @return
	 */

	@PutMapping("/update")
	@Operation(summary = "To update the Customer details")
	public Boolean updateCustomer(@RequestHeader(name = "Authorization") String token,
			@RequestBody @Valid Customer customer) {

		log.info("Start");

		if (authController.validate(token).hasBody()) {

			log.info("Token valid. Upadating customer details");
			customerService.updateCustomer(customer);
			return Boolean.TRUE;

		}

		log.info("Token Invalid. Cannot update customer details.");
		return Boolean.FALSE;

	}

	@GetMapping("/get/{emailId}")
	public String getCustomerUsingEmailId(@PathVariable String emailId) {
		return customerService.getCustomerUsingEmailId(emailId);
	}

}
