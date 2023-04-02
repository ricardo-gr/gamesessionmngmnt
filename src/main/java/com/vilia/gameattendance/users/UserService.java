package com.vilia.gameattendance.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.vilia.gameattendance.users.exception.UserServiceException;

@Component
public class UserService {

	private final UserRepository userRepository;
	private final UserRoleRepository userRoleRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository,
			PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public List<UserDto> listUsers() {
		List<User> userList = userRepository.findAll();

		return userList.stream().map(UserDto::new).toList();
	}

	public UserDto createUser(UserDto newUserDto) throws UserServiceException {
		Long userId = newUserDto.getUserId();
		if (userId != null || userRepository.existsByUsername(newUserDto.getUsername()))
			throw new UserServiceException(HttpStatus.BAD_REQUEST, "UserService - user already exists", newUserDto);

		String roleName = newUserDto.getRoleName();
		if (roleName == null || roleName.isEmpty())
			throw new UserServiceException(HttpStatus.BAD_REQUEST, "UserService - role does not exist", newUserDto);

		UserRole role = userRoleRepository.findByRoleName(roleName).orElse(null);

		if (role == null)
			throw new UserServiceException(HttpStatus.BAD_REQUEST, "UserService - role does not exist", newUserDto);

		String hashedPassword = checkAndEncodePassword(newUserDto);
		
		User newUser = new User(newUserDto, role, hashedPassword);

		User createdUser = userRepository.save(newUser);

		return new UserDto(createdUser);
	}

	public UserDto findUser(Long userId) throws UserServiceException {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null)
			throw new UserServiceException(HttpStatus.NOT_FOUND, "UserService - user does not exist", userId);

		return new UserDto(user);
	}

	public UserDto updateUser(UserDto newUserDto) throws UserServiceException {
		Long userId = newUserDto.getUserId();
		if (userId == null)
			throw new UserServiceException(HttpStatus.BAD_REQUEST, "UserService - user id is not included in request",
					newUserDto);

		User user = userRepository.findById(userId).orElse(null);
		if (user == null || user.getUsername().equals(newUserDto.getUsername()))
			throw new UserServiceException(HttpStatus.NOT_FOUND, "UserService - user does not exist", newUserDto);

		String roleName = newUserDto.getRoleName();
		if (roleName == null || roleName.isEmpty())
			throw new UserServiceException(HttpStatus.BAD_REQUEST, "UserService - role does not exist", newUserDto);

		UserRole role = userRoleRepository.findByRoleName(roleName).orElse(null);
		if (role == null)
			throw new UserServiceException(HttpStatus.BAD_REQUEST, "UserService - new role does not exist", newUserDto);

		String hashedPassword = checkAndEncodePassword(newUserDto);
		
		newUserDto.updateUser(user, role, hashedPassword);

		User updatedUser = userRepository.save(user);

		return new UserDto(updatedUser);
	}

	public void deleteUser(UserDto userToDelete) throws UserServiceException {
		Long userId = userToDelete.getUserId();
		if (userId == null)
			throw new UserServiceException(HttpStatus.BAD_REQUEST, "UserService - user id is not included in request",
					userToDelete);

		if (!userRepository.existsById(userId))
			throw new UserServiceException(HttpStatus.NOT_FOUND, "UserService - user does not exist", userToDelete);

		userRepository.deleteById(userId);
	}

	//Consider password security at some point in the future
	protected String checkAndEncodePassword(UserDto user) throws UserServiceException {
		String password = user.getPassword();
		
		if (password == null || password.equals(""))
			throw new UserServiceException(HttpStatus.BAD_REQUEST, "UserService - user password is blank or null");
		
		String hashedPassword = passwordEncoder.encode(password);
		
		return hashedPassword;
	}
}
