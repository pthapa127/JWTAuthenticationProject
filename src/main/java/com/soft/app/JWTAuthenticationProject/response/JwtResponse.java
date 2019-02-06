package com.soft.app.JWTAuthenticationProject.response;

public class JwtResponse {

	private String token;
	private String type="Bearer";
	
	public JwtResponse(String accesstoken) {
		this.token = accesstoken;
	}
	
	public String getAccessToken() {
		return this.token;
	}
	
	public void setAccessToken(String accesstoken) {
		this.token = accesstoken;
	}
	
	public String getTokenType() {
		return this.type;
	}
	
	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}
}
