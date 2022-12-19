package com.cognizant.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.model.Customer;
import com.cognizant.repository.CustomerRepository;
import com.cognizant.util.JwtUtil;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

/**
 * Service class for the Customer
 * @author 2063201
 *
 */
@Service
@Slf4j
public class CustomerService {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomerRepository customerRepository;

	
	/**
	 * Used to get the customer based on the given token
	 * @param token
	 * @return
	 */
	
	public Customer getCustomer(String token) {
		
		token = token.substring(7);
		String email = jwtUtil.extractUsername(token);
		
		return customerRepository.findByEmail(email);
		
	}

	/**
	 * Registers new user to the application
	 * @param customer
	 */
	
	public void registerCustomer(@Valid Customer customer) {
		customerRepository.saveAndFlush(customer);
	}
	
	
	/**
	 * Used to update the existing user details
	 * @param customer
	 */

	public void updateCustomer(@Valid Customer customer) {
		customerRepository.saveAndFlush(customer);
	}

	public String getCustomerUsingEmailId(String emailId) {
		
		Customer customer = customerRepository.findByEmail(emailId);
		
		if(customer != null)
			return customer.getEmail();
		
		return "No user found";
		
	}


}
