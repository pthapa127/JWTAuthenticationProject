package com.soft.app.JWTAuthenticationProject.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soft.app.JWTAuthenticationProject.model.User;
import com.soft.app.JWTAuthenticationProject.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired private UserRepository userRepo;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//get the user info by username 
		User user =userRepo.findByUsername(username);
		//pass that user obj to the static method build of UserPrinciple class
		//user obj contains all the user info like username,role and so on....
		return UserPrinciple.build(user);
		
		//you can use below code instead of using UserPrincipe.build(user)
		
		//List<GrantedAuthority> authorities = new ArrayList<>();
		//GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getRoleName());
		//authorities.add(authority);
		//return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),  authorities);
	
	}

	
}
