package com.vilia.gameattendance.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne(fetch = FetchType.EAGER)
	private UserRole role;
	@Column
	private boolean active;
	@Column(unique = true)
	private String username;
	@Column
	private String fullName;
	@Column
	private String password;

	public User() {}
	
	public User(UserDto userDto, UserRole role) {
		super();
		this.id = userDto.getUserId();
		this.role = role;
		this.active = userDto.isActive();
		this.username = userDto.getUsername();
		this.fullName = userDto.getFullName();
		this.password = userDto.getPassword().equals("") ? null : userDto.getPassword();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", role=" + role + ", active=" + active + ", username=" + username + ", fullName="
				+ fullName + ", password=" + password + "]";
	}

}
