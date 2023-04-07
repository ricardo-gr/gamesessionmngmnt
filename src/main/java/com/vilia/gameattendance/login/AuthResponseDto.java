package com.vilia.gameattendance.login;

public class AuthResponseDto {
	
	private String email;
	private String accessToken;
	
	public AuthResponseDto() {}
	
	public AuthResponseDto(String email, String accessToken) {
		this.email = email;
		this.accessToken = accessToken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	

}
