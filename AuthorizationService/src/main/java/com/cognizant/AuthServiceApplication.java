package com.cognizant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

/**
 * This is the main class for Authorisation class
 * 
 * @authorPOD 2
 * Project title : Retail Product Management 
 *
 */
@SpringBootApplication
@OpenAPIDefinition
public class AuthServiceApplication {

	/**
	 * Main class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

}
