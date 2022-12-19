package com.cognizant.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognizant.model.Customer;
import com.cognizant.repository.CustomerRepository;

/**
 * Service implementation class for UserDetailsService
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * Overriding method to load the user details from database with user name
	 * 
	 * @param userName
	 * @return This returns the user name and password
	 */
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		
		Customer customer = customerRepository.findByEmail(username);
		
		if(customer == null)
			throw new UsernameNotFoundException("User doesnt exits.");
		
		return new User(customer.getEmail(), customer.getPassword(), new ArrayList<>());
		
	}

}
