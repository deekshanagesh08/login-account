package com.ibm.loginaccount.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.loginaccount.model.UserModel;
import com.ibm.loginaccount.model.UserResponse;
import com.ibm.loginaccount.service.MyUserDetailsService;
import com.ibm.loginaccount.service.util.JwtUtil;

@Component
@RestController
public class loginAccountController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	@RequestMapping(value="/hello")
	public String hello() {
		return "Hello world";
	}
	
@RequestMapping(value="/authenticate", method=RequestMethod.POST)
public ResponseEntity<?> createAuthenticateToken(@RequestBody UserModel authenticateRequest ) throws Exception{
	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateRequest.getUsername(),authenticateRequest.getPassword()));
			
final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticateRequest.getUsername());
final String jwt = jwtUtil.generateToken(userDetails);
return ResponseEntity.ok(new UserResponse(jwt));
}	
}







