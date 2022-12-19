package com.cognizant.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.exception.InvalidPasswordException;
import com.cognizant.model.UserCredentials;
import com.cognizant.service.UserDetailsServiceImpl;
import com.cognizant.util.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

/**
 * Class for Authorization Controller
 */
@RestController
@Slf4j
public class AuthController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	/**
	 * To check whether the authorization service is running fine or not
	 * @return Status ok.
	 */
	
	@GetMapping("/health-check")
	@Operation(description="Used to check the status of authorization service",summary="check status of the service")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<>("The auth service is working fine.", HttpStatus.OK);
	}

	/**
	 * Method to validate the token
	 * 
	 * @param token1 This is the token send for authentication
	 * @return This returns true/false based on token validity
	 */
	@GetMapping("/validate")
	@Operation(summary ="URL to validate the jwt token")
	public ResponseEntity<Boolean> validate(@RequestHeader(name = "Authorization") String token) {

		log.info("Start");

		token = token.substring(7);
		
		try {

			UserDetails user = userDetailsService.loadUserByUsername(jwtUtil.extractUsername(token));
			
			if (jwtUtil.validateToken(token, user)) {

				log.debug("Valid token has been sent");
				log.info("End");

				return new ResponseEntity<>(true, HttpStatus.OK);

			} else {

				log.debug("Invalid token has been sent.");
				log.info("End");

				return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);

			}

		} catch (Exception e) {

			log.info("End");
			return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
		}

	}
	

	/**
	 * Method to check whether login credentials are correct or not
	 * 
	 * @param userCredentials user credentials contain user name and password
	 * @return This returns token on successful login else throws exception
	 */
	
	@PostMapping("/login")
	@Operation(summary ="URL to generate jwt token")
	public String login(@RequestBody @Valid UserCredentials userCredentials) {

		log.info("Start");

		try {

			UserDetails customer = userDetailsService.loadUserByUsername(userCredentials.getEmail());

			if (customer.getPassword().equals(userCredentials.getPassword())) {

				log.debug("Password matched generating jwt token.");

				String token = jwtUtil.generateToken(customer.getUsername());

				log.debug("Token generated. Login successful");
				log.info("End");

				return token;

			} else {

				log.debug("Invalid password. Login unsuccessful.");

				throw new InvalidPasswordException("You have entered wrong password.");

			}

		} catch (Exception exception) {

			log.info("End");
			
			throw exception;

		}

	}

}
