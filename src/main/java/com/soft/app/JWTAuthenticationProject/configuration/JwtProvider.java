package com.soft.app.JWTAuthenticationProject.configuration;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.soft.app.JWTAuthenticationProject.service.UserPrinciple;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {

	@Value("${pthapa.app.jwtSecret}")
	private String jwtSecret;

	@Value("${pthapa.app.jwtExpiration}")
	private int jwtExpiration;

	public String generateJwtToken(Authentication authentication) {
		UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
		//you can use authentication.getName to get the email
		//you can use authentication.getAuthorities to get the roles

		return Jwts.builder().setSubject(userPrinciple.getUsername())
				.claim("scopes", userPrinciple.getAuthorities())
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + jwtExpiration))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public String getUsernameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			System.out.println("SignatureException " + e.getMessage());
		} catch (MalformedJwtException e) {
			System.out.println("MalFormedException " + e.getMessage());
		} catch (ExpiredJwtException e) {
			System.out.println("ExpiredJwtException " + e.getMessage());
		} catch (UnsupportedJwtException e) {
			System.out.println("UnsupportedException " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println("IllegalArgumentException " + e.getMessage());
		}
		return false;
	}
}
