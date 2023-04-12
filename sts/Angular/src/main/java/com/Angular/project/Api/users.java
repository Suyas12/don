package com.Angular.project.Api;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class users {
	
	//Fields
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id; 
	
	@Column(name="username")
	private String username;
	
	@Column(name="role")
	private String role;
	
	@Column(name="password")
	private String password;
	
	
	public users() {
		
	}
	
	public users(int id, String username, String role, String password) {
		this.id = id;
		this.username = username;
		this.role = role;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "users [id=" + id + ", username=" + username + ", role=" + role + ", password=" + password + "]";
	}

	
}
