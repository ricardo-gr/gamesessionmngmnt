package com.vilia.gameattendance.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users")
	public List<UserDto> listUsers() {
		return userService.listUsers();
	}
	
	@GetMapping("/users/{id}")
	public UserDto findUser(@PathVariable long id) {
		return userService.findUser(id);
	}

}
