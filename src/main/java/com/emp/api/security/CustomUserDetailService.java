
package com.emp.api.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.emp.api.exception.ResourceNotFoundException;
import com.emp.api.service.AuthService;


@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private AuthService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws ResourceNotFoundException {
		System.out.println("username ... " + username);
		// loading user from db
		CustomUserDetail user = service.loadUserByUserId(username);
		if (user != null) {
			System.out.println("user value "+user);
			return user;
		} else {
			System.out.println("user not found");
			throw new ResourceNotFoundException("User not found with username: " + username);
		}

	}

}
