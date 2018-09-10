package com.zycats.srs.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;



public class Auth {

	public static void setAuthority(String authorityRole){
		
		
		@SuppressWarnings("unchecked")
		Collection<SimpleGrantedAuthority> oldAuthorities = (Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		
		List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<>();
		
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(authorityRole);
//		for(SimpleGrantedAuthority authorityObj : )
		if(!oldAuthorities.contains(authority))
			updatedAuthorities.add(authority);
		updatedAuthorities.addAll(oldAuthorities);
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Object credentials = SecurityContextHolder.getContext().getAuthentication().getCredentials();
		
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(principal, credentials,updatedAuthorities));
		
	}
}
