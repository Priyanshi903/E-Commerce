package com.cognizant.proxy;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.bean.Customer;
import com.cognizant.dto.UserCredentials;


//@FeignClient(name = "authorization-service", url="http://13.57.30.36:8081/auth")
@FeignClient(name = "authorization-service", url="http://localhost:8081/auth")
public interface AuthProxy {

	
	@PostMapping("/login")
	public String login(@RequestBody UserCredentials userCredentials);
	
	@GetMapping("/get")
	public Customer getCustomerUsingJwtToken(@RequestHeader(name = "Authorization") String token);
	
	@PostMapping("/signup")
	public Boolean registernewCustomer(@RequestBody Customer customer);
	
	@GetMapping("/get/{emailId}")
	public String getCustomerUsingEmailId(@PathVariable String emailId);
	
}
