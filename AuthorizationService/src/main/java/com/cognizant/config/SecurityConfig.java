package com.cognizant.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Security Configuration class
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * To override the auto configurations provided by spring security
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, "/**").permitAll()
				.antMatchers(HttpMethod.GET, "/**").permitAll().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.anyRequest().permitAll();
		
		// To access the h2 console in browser
		http.headers().frameOptions().disable();
		
	}
}
