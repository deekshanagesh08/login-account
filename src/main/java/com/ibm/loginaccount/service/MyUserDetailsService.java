package com.ibm.loginaccount.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	public UserDetails loadUserByUsername(String userName) {
		return new User("foo", "foo", new ArrayList<>());
	}

}
