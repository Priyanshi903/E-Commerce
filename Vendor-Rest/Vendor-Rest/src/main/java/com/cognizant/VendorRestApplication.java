package com.cognizant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
public class VendorRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendorRestApplication.class, args);
	}

}
