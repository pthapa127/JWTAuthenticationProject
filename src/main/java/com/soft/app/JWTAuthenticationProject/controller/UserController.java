package com.soft.app.JWTAuthenticationProject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.soft.app.JWTAuthenticationProject.model.User;
import com.soft.app.JWTAuthenticationProject.repositories.UserRepository;
import com.soft.app.JWTAuthenticationProject.utility.CustomResponse;

@RestController
@RequestMapping(value="/api/users")
public class UserController {

	@Autowired private UserRepository userRepository;
	
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public CustomResponse<List<User>> allUsers(){
		CustomResponse<List<User>> customResponse = new CustomResponse<>();
		try {
			List<User> users = userRepository.findAll();
			customResponse.setStatus(200);
			customResponse.setMessage("Successfull");
			customResponse.setBody(users);
			return customResponse;
		}catch(Exception ex) {
			customResponse.setError("Server Error");
			customResponse.setStatus(500);
			customResponse.setMessage(ex.getMessage());
			return customResponse;
		}
	}
	
	@RequestMapping(value="/users/{id}",method=RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public CustomResponse<User> getUserById(@PathVariable("id") Long id){
		CustomResponse<User> customResponse = new CustomResponse<>();
		try {
			Optional<User> singleUser = userRepository.findById(id);
			User userInfo = singleUser.get();
			customResponse.setStatus(200);
			customResponse.setBody(userInfo);
			customResponse.setMessage("Successfull");
			return customResponse;
		}catch(Exception ex) {
			customResponse.setError("Server Error");
			customResponse.setStatus(500);
			customResponse.setMessage(ex.getMessage());
			return customResponse;
		}
	}
}
