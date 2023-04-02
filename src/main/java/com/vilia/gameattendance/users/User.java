package com.vilia.gameattendance.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private UserRole role;
	@Column
	private Boolean active;
	@Column(unique = true, nullable = false)
	private String username;
	@Column
	private String fullName;
	@Column(nullable = false)
	private String password;

	private static final Logger logger = LoggerFactory.getLogger(User.class);
	
	public User() {}
	
	public User(UserDto userDto, UserRole role, String hashedPassword) {
		super();
		this.id = userDto.getUserId();
		this.role = role;
		this.active = userDto.isActive();
		this.username = userDto.getUsername();
		this.fullName = userDto.getFullName();
		this.password = hashedPassword;
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

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
