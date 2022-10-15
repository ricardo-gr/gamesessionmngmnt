package com.vilia.gameattendance.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.vilia.gameattendance.users.exception.UserServiceException;

@Component
public class UserService {
	
	private final UserRepository userRepository;
	private final UserRoleRepository userRoleRepository;
	
	@Autowired
	public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
		super();
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepository;
	}

	public void createUser(UserDto newUserDto) throws UserServiceException {
		Long userId = newUserDto.getUserId();
		if (userId != null || 
				userRepository.existsByUsername(newUserDto.getUsername()))
			throw new UserServiceException(HttpStatus.BAD_REQUEST, "UserService - user already exists", newUserDto);
		
		String roleName = newUserDto.getRoleName();
		if (roleName == null || roleName.isEmpty())
			throw new UserServiceException(HttpStatus.BAD_REQUEST, "UserService - role does not exist", newUserDto);
		
		UserRole role = userRoleRepository.findByRoleName(roleName).orElse(null);
		
		if (role == null)
			throw new UserServiceException(HttpStatus.BAD_REQUEST, "UserService - role does not exist", newUserDto);
		
		User newUser = new User(newUserDto, role);
		
		userRepository.save(newUser);
	}
	
	public List<UserDto> listUsers() {
		List<User> userList = new ArrayList<>();
		userRepository.findAll().forEach(userList::add);
		
		return userList.stream().map(UserDto::new).toList();
	}
	
	public UserDto findUser(Long userId) throws UserServiceException{
		User user = userRepository.findById(userId).orElse(null);
		if (user == null)
			throw new UserServiceException(HttpStatus.NOT_FOUND, "UserService - user does not exist", userId);
		
		return new UserDto(user);
	}
	
	public void updateUser(UserDto newUserDto) throws UserServiceException {
		Long userId = newUserDto.getUserId();
		if (userId == null) 
			throw new UserServiceException(HttpStatus.BAD_REQUEST, "UserService - user id is not included in request", newUserDto);
		
		if (!userRepository.existsById(userId))
			throw new UserServiceException(HttpStatus.NOT_FOUND, "UserService - user does not exist", newUserDto);
		
		String roleName = newUserDto.getRoleName();
		if (roleName == null || roleName.isEmpty())
			throw new UserServiceException(HttpStatus.BAD_REQUEST, "UserService - role does not exist", newUserDto);
		
		UserRole role = userRoleRepository.findByRoleName(roleName).orElse(null);
		if (role == null)
			throw new UserServiceException(HttpStatus.BAD_REQUEST, "UserService - new role does not exist", newUserDto);
		
		User newUser = new User(newUserDto, role);
		
		userRepository.save(newUser);
	}
	
	public void deleteUser(UserDto userToDelete) throws UserServiceException {
		Long userId = userToDelete.getUserId();
		if (userId == null) 
			throw new UserServiceException(HttpStatus.BAD_REQUEST, "UserService - user id is not included in request", userToDelete);
		
		if (!userRepository.existsById(userId))
			throw new UserServiceException(HttpStatus.NOT_FOUND, "UserService - user does not exist", userToDelete);
		
		userRepository.deleteById(userId);
	}
}
