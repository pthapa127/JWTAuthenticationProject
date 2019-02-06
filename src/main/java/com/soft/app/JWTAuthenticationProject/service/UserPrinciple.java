package com.soft.app.JWTAuthenticationProject.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.soft.app.JWTAuthenticationProject.model.User;

public class UserPrinciple implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String firstname;
	
	private String lastname;
	
	private String username;
	
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserPrinciple(Long id,String firstname,String lastname,String username,String password,Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.firstname = firstname;
		this.lastname= lastname;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}
	
	public static UserPrinciple build(User user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getRoleName());
		authorities.add(authority);
		
		return new UserPrinciple(user.getId(), user.getFirstname(), user.getLastname(), user.getUsername(), user.getPassword(), authorities);
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
