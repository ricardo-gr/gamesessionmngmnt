package com.vilia.gameattendance.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> users = userService.listUsers();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> findUser(@PathVariable long userId) {
		UserDto user = userService.findUser(userId);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
		UserDto newUser = userService.createUser(user);
		return ResponseEntity.ok(newUser);
	}
	
	@PutMapping("")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto updatedUser) {
        UserDto user = userService.updateUser(updatedUser);
        return ResponseEntity.ok(user);	
    }
	
	@DeleteMapping("")
    public ResponseEntity<Void> deleteUser(@RequestBody UserDto userToDelete) {
        userService.deleteUser(userToDelete);
        return ResponseEntity.noContent().build();
    }

}
