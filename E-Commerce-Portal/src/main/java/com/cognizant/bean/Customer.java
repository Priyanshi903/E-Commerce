package com.cognizant.bean;

import org.springframework.format.annotation.NumberFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Model class for storing user data in database
 *
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	private String username;
	private String email;
	private String password;
	private long phoneNumber;

}
