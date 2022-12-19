package com.cognizant.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Model class for fetching user credentials while login
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentials {

	@NotBlank(message = "Email cannot be black")
	@Pattern(regexp=".+@.+\\..+",message = "Please provide valid Email Id.")
	private String email;

	@NotBlank(message = "Password cannot be black.")
	@Pattern(regexp = "^[A-Za-z0-9]+$", message = "Password should contain alpha numeric values")
	private String password;
	
}
