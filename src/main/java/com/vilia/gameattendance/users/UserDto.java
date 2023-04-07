package com.vilia.gameattendance.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class UserDto {
	@Min(1)
	private Long userId;
	private String roleName;
	private Boolean active;
	@NotNull
	private String username; 
	private String fullName;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	private static final Logger logger = LoggerFactory.getLogger(UserDto.class);
	
	public UserDto() {}
	
	public UserDto(User baseUser) {
		super();
		this.userId = baseUser.getId();
		this.roleName = baseUser.getRole().getRoleName();
		this.active = baseUser.isActive();
		this.username = baseUser.getUsername();
		this.fullName = baseUser.getFullName();
		this.password = baseUser.getPassword();
	}
	
	public UserDto(Long userId, String roleName, Boolean active, String username, String fullName, String password) {
		super();
		this.userId = userId;
		this.roleName = roleName;
		this.active = active;
		this.username = username;
		this.fullName = fullName;
		this.password = password;
	}
	
	public UserDto(String roleName, Boolean active, String username, String fullName, String password) {
		super();
		this.userId = null;
		this.roleName = roleName;
		this.active = active;
		this.username = username;
		this.fullName = fullName;
		this.password = password;
	}
	
	public Long getUserId() {
		return userId;
	}
	public String getRoleName() {
		return roleName;
	}
	public Boolean isActive() {
		return active;
	}
	public String getUsername() {
		return username;
	}
	public String getFullName() {
		return fullName;
	}
	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", roleName=" + roleName + ", active=" + active + ", username=" + username
				+ ", fullName=" + fullName + ", password=********]";
	}
	
	public void updateUser(User user) {
		user.setActive(this.active);
		user.setFullName(this.fullName);
	}
	
	public void updateUser(User user, UserRole role, String hashedPassword) {
		this.updateUser(user);
		user.setRole(role);
		user.setPassword(hashedPassword);
	}
	
	

}
