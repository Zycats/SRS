package com.zycats.srs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import waffle.spring.NegotiateSecurityFilter;
import waffle.spring.NegotiateSecurityFilterEntryPoint;
import waffle.spring.WindowsAuthenticationProvider;

@Configuration
@ComponentScan(basePackages = { "com.zycats.srs.controller", "com.zycats.srs.service", "com.zycats.srs.event", "com.zycats.srs.scheduler", "com.zycats.srs.config"})
@EntityScan(basePackages = "com.zycats.srs.entity")
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = false)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private NegotiateSecurityFilter negotiateSecurityFilter;

	@Autowired
	private NegotiateSecurityFilterEntryPoint entryPoint;
	
	@Autowired
	private WindowsAuthenticationProvider windowsAuthenticationProvider;


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		
		http.authorizeRequests().antMatchers("/rest/employee/**").access("hasAnyRole('ROLE_USER')");
		
		http.authorizeRequests().antMatchers("/rest/executive/**").access("hasAnyRole('ROLE_USER')");
		
		
		/*http
		.authorizeRequests()
        .anyRequest().authenticated()
    .and()
         // Set authentication provider here
        .formLogin()
			.loginPage("/login")
			.permitAll()
			.and()
		
			.addFilterBefore(negotiateSecurityFilter, BasicAuthenticationFilter.class)
	        .httpBasic()
	        .authenticationEntryPoint(entryPoint)
	        .and()
	        .authenticationProvider(windowsAuthenticationProvider)*/
	        
	
	
	http
			
	.authorizeRequests()
	.anyRequest()
	.authenticated()
	.and()
	.httpBasic()
	.authenticationEntryPoint(entryPoint)
	.and()
	.addFilterBefore(negotiateSecurityFilter, BasicAuthenticationFilter.class)
	.csrf()
	.disable();
	
		
	
	}

	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication();
	}
	


}
