package com.cognizant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.model.Customer;

/**
 * Repository class for storing, fetching and manipulating Customer data
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	// Method to find a user details with user name
	public Customer findByEmail(String email);
	
}
