package com.vilia.gameattendance.login.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

	public boolean authorizeUserOverResourceUserId(Authentication authentication, Long userId) {
		User requestingUser = (User) authentication.getPrincipal();
		
		return requestingUser.getId() == userId;
	}

	public boolean authorizeAdminUserOrUserOverResourceUserId(Authentication authentication, Long id) {
		return authorizeAdminUser(authentication) || authorizeUserOverResourceUserId(authentication, id);
	}

	public boolean authorizeAdminUserOrUserOverResourceOwnerUsername(Authentication authentication,
			String resourceOwner) {
		return authorizeAdminUser(authentication) || authorizeUserOverResourceOwnerUsername(authentication, resourceOwner);
	}
	
	public boolean authorizeUserOverResourceOwnerUsername(Authentication authentication, String resourceOwner) {
		return authentication.getName().equals(resourceOwner);
	}
	
	public boolean authorizeAdminUser(Authentication authentication) {
		return authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
	}
}
