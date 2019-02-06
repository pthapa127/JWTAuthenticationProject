package com.soft.app.JWTAuthenticationProject.utility;

public class CustomResponse<T> {

	public Integer status;
	private String message;
	private String error;
	private T body;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public T getBody() {
		return body;
	}
	public void setBody(T body) {
		this.body = body;
	}
	
	
}
