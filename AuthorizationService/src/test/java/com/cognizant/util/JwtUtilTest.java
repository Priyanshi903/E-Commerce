package com.cognizant.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * test - JwtUtil class
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class JwtUtilTest {

	UserDetails userDetails;

	@InjectMocks
	JwtUtil jwtUtil;

	@Test
	public void isNumericTrueTest() {
		String num = "123";
		assertEquals(true, jwtUtil.isNumeric(num));
	}

	@Test
	public void isNumericNullTest() {
		String num = null;
		assertEquals(false, jwtUtil.isNumeric(num));
	}

	@Test
	public void isNumericFalseTest() {
		String num = "abc";
		assertEquals(false, jwtUtil.isNumeric(num));
	}

	@Test
	public void generateTokenTest() {
		userDetails = new User("admin", "admin", new ArrayList<>());
		String generateToken = jwtUtil.generateToken(userDetails.getUsername());
		assertNotNull(generateToken);
	}

	@Test
	public void validateTokenTest() {
		userDetails = new User("admin", "pass", new ArrayList<>());
		String generateToken = jwtUtil.generateToken(userDetails.getUsername());
		boolean validateToken = jwtUtil.validateToken(generateToken, userDetails);
		assertEquals(true, validateToken);
	}

	@Test
	public void validateTokenWithNameTest() {
		userDetails = new User("vishal", "password", new ArrayList<>());
		String generateToken = jwtUtil.generateToken(userDetails.getUsername());
		Boolean validateToken = jwtUtil.validateToken(generateToken, userDetails);
		assertEquals(true, validateToken);
	}

	@Test
	public void validateTokenWithNameFalseTest() {
		userDetails = new User("pass", "admin", new ArrayList<>());
		String generateToken = jwtUtil.generateToken(userDetails.getUsername());
		Boolean validateToken = jwtUtil.validateToken(generateToken, userDetails);
		assertEquals(true, validateToken);
	}

}
