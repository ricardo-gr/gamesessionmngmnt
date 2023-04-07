package com.vilia.gameattendance.login;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vilia.gameattendance.users.User;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping("")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequestDto authRequest)
			throws AuthenticationException {
		// Authenticate the user
		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

		// Set the authenticated user in the SecurityContextHolder
		//SecurityContextHolder.getContext().setAuthentication(authentication);

		// Generate the JWT token
		final User userDetails = (User) authentication.getPrincipal();
		final String token = jwtTokenUtil.generateAccessToken(userDetails);
		final AuthResponseDto response = new AuthResponseDto(userDetails.getUsername(), token);

		// Return the token in the response
		return ResponseEntity.ok().body(response);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<?> handleAuthenticationException(AuthenticationException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
}
