package com.joshwithee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;

/*
 * Notes: This authentication is done using annotations. 
 * The only 3 contraints are:
 * -annotate the method with @Autowired
 * -the method is in a class with one of the following: @EnableWebSecurity, @EnableWebMvcSecurity, @EnableGlobalMethodSecurity, or @EnableGlobalAuthentication
 * -the method must have an argument of type AuthenticationManagerBuilder
 * see http://stackoverflow.com/questions/35218354/difference-between-registerglobal-configure-configureglobal-configureglo
 */
@Configuration
@EnableGlobalAuthentication
public class MySecurityConfig {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER").and().withUser("admin")
				.password("password").roles("USER", "ADMIN");
	}
}
