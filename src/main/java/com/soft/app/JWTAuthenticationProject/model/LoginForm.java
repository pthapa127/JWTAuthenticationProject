package com.soft.app.JWTAuthenticationProject.model;

import javax.validation.constraints.NotNull;

/*
 * Author:Pawan Thapa
 * LoginForm that contains two attributes for user login and to generate token
 * */

public class LoginForm {

	@NotNull
	public String username;
	
	@NotNull
	public String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
}
