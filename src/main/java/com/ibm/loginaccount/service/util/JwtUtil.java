package com.ibm.loginaccount.service.util;

import java.util.Date;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.stereotype.Service;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {
private String SECRET_KEY="secret";

public String extractUserName(String token) {
	return extractClaim(token, Claims::getSubject);
}

public Date extractUserExpiration(String token) {
	return extractClaim(token, Claims::getExpiration);
}

public <T> T extractClaim(String token, Function <Claims, T> claimsResolver){
	final Claims claim = extractAllClaims(token);
	return claimsResolver.apply(claim);
}

private Claims extractAllClaims(String token) {
	return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
}

private boolean isTokenExpired(String token) {
	return extractUserExpiration(token).before(new Date());
}

public String generateToken(UserDetails userDetails) {
	Map<String, Object> claims = new HashMap<>();
	return createToken(claims, userDetails.getUsername());
}

private String createToken(Map<String, Object> claims, String subject) {
	return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)).signWith(SignatureAlgorithm.ES256, SECRET_KEY).compact();
}

public boolean validateToken(String token, UserDetails userDetails) {
	final String username = extractUserName(token);
	return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	
}
}
