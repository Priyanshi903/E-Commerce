package com.cognizant.exception;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test - GlobalExceptionHandler class
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GlobalExceptionHandlerTest {

	@InjectMocks
	GlobalExceptionHandler globalExceptionHandler;

	@Mock
	CustomErrorResponse customErrorResponse;

	@Before
	public void setUp() {
		customErrorResponse = new CustomErrorResponse();
	}

	@Test
	public void handlesUsernameNotFoundException() {
		
		UsernameNotFoundException usernameNotFoundException = new UsernameNotFoundException("User not fonund");

		ResponseEntity<?> entity = globalExceptionHandler.handleUserNotFoundException(usernameNotFoundException);
		assertTrue(entity.getBody().toString().contains("User not fonund"));
		
	}
	
	@Test
	public void handlesInvalidPasswordExceptionTest() {
		
		InvalidPasswordException invalidPasswordException = new InvalidPasswordException("Invalid Password");
		
		ResponseEntity<?> entity = globalExceptionHandler.handlesInvalidCustomerException(invalidPasswordException);
		assertTrue(entity.getBody().toString().contains("Invalid Password"));
		
	}


}
