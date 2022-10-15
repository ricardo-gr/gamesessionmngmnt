package com.vilia.gameattendance.users;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
	private String password;
	
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
	
	

}
