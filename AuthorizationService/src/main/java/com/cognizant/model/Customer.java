package com.cognizant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
@Entity(name = "customer")
public class Customer {


	@Column(name = "name")
	@NotBlank(message = "User name cannot be empty")
	private String username;

	@Id
	@NotBlank(message = "Email cannot be black")
	@Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
	private String email;
	
	@NotBlank(message = "Password cannot be blank")
	@Pattern(regexp = "^[A-Za-z0-9]+$")
	private String password;
	
	private long phoneNumber;

}
