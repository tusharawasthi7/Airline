package com.airline.Airline.security;


import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final String SECRET_KEY="RandomKeyjsdaidagsadgyuasdgyuasgdydssqwtyutg6554564qw56e456456ew4q564d56as4";
	private final Key key=Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
	
	
	public String generateToken(String username)
	{
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))  // 10 hours validity
				.signWith(key,SignatureAlgorithm.HS256)
				.compact();
	}
	
	
	private Claims extractAllClaims(String token)
	{
		return Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}
	
	public String extractUserName(String token) {		
		return extractAllClaims(token).getSubject();
	}
	
	public boolean isTokenExpired(String token) {
		return extractAllClaims(token).getExpiration().before(new Date());
	}
	
	public boolean validateToken(String token,String username)
	{
		return (username.equals(extractUserName(token)) && !isTokenExpired(token));
	}
}




