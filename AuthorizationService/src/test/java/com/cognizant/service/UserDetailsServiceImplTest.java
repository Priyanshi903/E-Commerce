package com.cognizant.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.model.Customer;
import com.cognizant.repository.CustomerRepository;

/**
 * Test - UserDetailsServiceImpl
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDetailsServiceImplTest {

	@Mock
	CustomerRepository repo;

	@InjectMocks
	UserDetailsServiceImpl userDetailsService;

	@Test
	public void loadUserByUsernameTest() {

		Customer user = new Customer("chandu","pck@gmail.com","1234",1l);
		
		when(repo.findByEmail("pck@gmail.com")).thenReturn(user);
		
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername("pck@gmail.com");
		
		assertEquals(user.getEmail(), loadUserByUsername.getUsername());
		
	}
	@Test(expected = UsernameNotFoundException.class)
	public void userNotFoundExceptionTest() {
		
		when(repo.findByEmail("pck@gmail.com")).thenReturn(null);
		
		userDetailsService.loadUserByUsername("pck@gmail.com");
		
		
	}

}
