package com.vilia.gameattendance.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vilia.gameattendance.login.authorization.AuthorizationUtils;

import jakarta.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;
	private AuthorizationUtils authUtils;
	
	@Autowired
	public UserController(UserService userService, AuthorizationUtils jwtUtil) {
		this.userService = userService;
		this.authUtils = jwtUtil;
	}
	
	@GetMapping("")
	@RolesAllowed("ROLE_ADMIN")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> users = userService.listUsers();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{userId}")
	@RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
	public ResponseEntity<UserDto> findUser(@PathVariable long userId, @RequestHeader("Authorization") String authHeader) throws AuthenticationException {
		if (!authUtils.authorizeUserOverResourceUserId(authHeader, userId))
			throw new BadCredentialsException("Not Authorized to request resource with ID " + userId);
		
		UserDto user = userService.findUser(userId);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("")
	@RolesAllowed("ROLE_ADMIN")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
		UserDto newUser = userService.createUser(user);
		return ResponseEntity.ok(newUser);
	}
	
	@PutMapping("")
	@RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto updatedUser, @RequestHeader("Authorization") String authHeader) {
		if (!authUtils.authorizeUserOverResourceUserId(authHeader, updatedUser.getUserId()))
			throw new BadCredentialsException("Not Authorized to request resource with ID " + updatedUser.getUserId());
		
        UserDto user = userService.updateUser(updatedUser);
        return ResponseEntity.ok(user);	
    }
	
	@DeleteMapping("")
	@RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Void> deleteUser(@RequestBody UserDto userToDelete) {
        userService.deleteUser(userToDelete);
        return ResponseEntity.noContent().build();
    }
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<?> handleAuthenticationException(AuthenticationException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

}
