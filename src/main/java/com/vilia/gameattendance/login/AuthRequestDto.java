package com.vilia.gameattendance.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class AuthRequestDto {
	
	@NotNull @Email
	private String email;
	
	@NotNull
	private String password;
	
	public AuthRequestDto() {}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
