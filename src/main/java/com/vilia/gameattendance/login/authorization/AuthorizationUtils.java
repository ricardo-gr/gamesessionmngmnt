package com.vilia.gameattendance.login.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vilia.gameattendance.login.JwtTokenUtil;
import com.vilia.gameattendance.users.User;

@Component
public class AuthorizationUtils {

	@Autowired
	private JwtTokenUtil jwtUtil;

	public boolean authorizeUserOverResourceUserId(String authHeader, long userId) {
		User requestingUser = (User) jwtUtil.getUserDetails(authHeader.split(" ")[1].trim());

		return requestingUser.getId() == userId;
	}
}
