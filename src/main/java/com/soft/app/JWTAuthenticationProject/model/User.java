package com.soft.app.JWTAuthenticationProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * Author : Pawan Thapa
 * User class that contains user attributes
 * 
 * */

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@NotNull
	@Column(name="firstname")
	private String firstname;
	
	@NotNull
	@Column(name="lastname")
	private String lastname;
	
	@NotNull
	@Column(name="address")
	private String address;
	
	@NotNull
	@Column(name="username")
	private String username;
	
	@NotNull
	@JsonIgnore
	@Column(name="password")
	private String password;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="role_id",nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Role role;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
