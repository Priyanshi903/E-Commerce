package com.cognizant.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

/**
 * Test - MyUser class
 */
public class CustomerTest {

	@Mock
	Customer myUser;

	@Before
	public void setUp() {
		myUser = new Customer("chandu","pck@gmail.com","1234",1l);
	}

	@Test
	public void userCredentialsAllConstructorTest() {
		Customer user = new Customer("pck@gmail.com","chandu","1234",1l);
		assertEquals("pck@gmail.com", user.getUsername());
	}

	@Test
	public void userIdTest() {
		assertEquals("pck@gmail.com", myUser.getEmail());
	}

	@Test
	public void userNameTest() {
		assertEquals("chandu", myUser.getUsername());
	}

	@Test
	public void passwordTest() {
		assertEquals("1234", myUser.getPassword());
	}

	@Test
	public void toStringTest() {
		String string = myUser.toString();
		assertEquals(string, myUser.toString());
	}

	@Test
	public void testEqualsMethod() {
		boolean equals = myUser.equals(myUser);
		assertTrue(equals);
	}

	@Test
	public void testHashCodeMethod() {
		int hashCode = myUser.hashCode();
		assertEquals(hashCode, myUser.hashCode());
	}

}
