package com.cognizant.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.cognizant.model.UserCredentials;
import com.cognizant.repository.CustomerRepository;
import com.cognizant.service.UserDetailsServiceImpl;
import com.cognizant.util.JwtUtil;
import com.google.gson.Gson;

/**
 * Test - AuthController class
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AuthControllerTest {

//	@InjectMocks
//	AuthController authController;

	UserDetails userdetails;
	private static Gson gson = new Gson();

	@MockBean
	JwtUtil jwtutil;

	@MockBean
	UserDetailsServiceImpl custdetailservice;

	@MockBean
	CustomerRepository userservice;
	
	@Autowired
	private MockMvc mvc;

	@Test
	public void checkHealthOfService() throws Exception {
		mvc.perform(get("/health-check")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void validTokenTest() throws Exception {
		
		UserDetails value = new User("pck@gmail.com", "123", new ArrayList<>());

		when(jwtutil.extractUsername("token")).thenReturn(value.getUsername());
		
		when(custdetailservice.loadUserByUsername(value.getUsername())).thenReturn(value);
		
		when(jwtutil.validateToken("token", value)).thenReturn(true);
		
		mvc.perform(get("/validate").header("Authorization", "Bearer token")).andDo(print()).andExpect(status().isOk());
	
		
	}
	
	@Test
	public void inValidTokenTest() throws Exception {
		
		UserDetails value = new User("pck@gmail.com", "123", new ArrayList<>());
		
		when(jwtutil.extractUsername("token")).thenReturn(value.getUsername());
		
		when(custdetailservice.loadUserByUsername(value.getUsername())).thenReturn(value);
		
		when(jwtutil.validateToken("token", value)).thenReturn(true);
		
		mvc.perform(get("/validate").header("Authorization", "Bearer invalidToken")).andDo(print()).andExpect(status().isForbidden());
		
		
	}
	
	@Test
	public void validLoginTest() throws Exception {

		
		UserCredentials user = new UserCredentials("pck@gmail.com", "1234");
		
		UserDetails value = new User(user.getEmail(), user.getPassword(), new ArrayList<>());
		
		when(custdetailservice.loadUserByUsername("pck@gmail.com")).thenReturn(value);
		
		when(jwtutil.generateToken(user.getEmail())).thenReturn("token");
		
		MvcResult mvcResult = mvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(user))).andReturn();
		
		assertEquals(mvcResult.getResponse().getContentAsString(),"token");
		
	}
	
	
	
	@Test
	public void wrongPasswordLoginTest() throws Exception {
		
		UserCredentials user = new UserCredentials("pck@gmail.com", "123");
		
		UserDetails value = new User(user.getEmail(), "12345", new ArrayList<>());
		
		when(custdetailservice.loadUserByUsername("pck@gmail.com")).thenReturn(value);
		
		mvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(user)).accept(MediaType.APPLICATION_JSON))
						.andDo(print())
						.andExpect(jsonPath("$.message", is("You have entered wrong password.")));
		
	}
	
	@Test
	public void usernameNameNotFoundExceptionLoginTest() throws Exception {
		
		UserCredentials user = new UserCredentials("pck@gmail.com", "123");
		
		when(custdetailservice.loadUserByUsername(user.getEmail())).thenThrow(new UsernameNotFoundException("User doesnt exits."));
		
		mvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(user)).accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(jsonPath("$.message", is("User doesnt exits.")));


	}
	
}
