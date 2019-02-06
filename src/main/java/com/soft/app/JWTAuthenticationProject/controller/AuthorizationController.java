package com.soft.app.JWTAuthenticationProject.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.soft.app.JWTAuthenticationProject.configuration.JwtProvider;
import com.soft.app.JWTAuthenticationProject.model.LoginForm;
import com.soft.app.JWTAuthenticationProject.response.JwtResponse;
import com.soft.app.JWTAuthenticationProject.utility.CustomResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthorizationController {

	@Autowired private AuthenticationManager authenticationManager;
	
	
	@Autowired private JwtProvider provider;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public CustomResponse<JwtResponse> authenticateUser(@Valid @RequestBody LoginForm loginForm){
		CustomResponse<JwtResponse> response = new CustomResponse<>();
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword())
				);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = provider.generateJwtToken(authentication);
		JwtResponse jwtResponse = new JwtResponse(jwt);
		response.setStatus(200);
		response.setMessage("Successfull");
		response.setBody(jwtResponse);
		return response;
		//return new ResponseEntity<JwtResponse>(new JwtResponse(jwt),HttpStatus.OK);
		}catch(Exception ex) {
			response.setError("UnAuthorized");
			response.setStatus(401);
			response.setMessage(ex.getMessage());
			return response;
		}
		
		
	}
}
