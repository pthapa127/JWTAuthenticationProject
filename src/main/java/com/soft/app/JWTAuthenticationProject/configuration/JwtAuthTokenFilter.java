package com.soft.app.JWTAuthenticationProject.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.soft.app.JWTAuthenticationProject.service.UserDetailsServiceImpl;

public class JwtAuthTokenFilter extends OncePerRequestFilter{

	@Autowired private JwtProvider jwtProvider;
	
	@Autowired private UserDetailsServiceImpl userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String jwt = getJwt(request);
			if(jwt!=null && jwtProvider.validateJwtToken(jwt)) {
				String username = jwtProvider.getUsernameFromJwtToken(jwt);
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			}
		}catch(Exception ex) {
			System.out.println("Cannot set user authentication "+ex.getMessage());
		}
		filterChain.doFilter(request, response);
	}

	public String getJwt(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		 if(authHeader!=null && authHeader.startsWith("Bearer")) {
			 return authHeader.replace("Bearer", "");
		 }
		 return null;
	}
}
