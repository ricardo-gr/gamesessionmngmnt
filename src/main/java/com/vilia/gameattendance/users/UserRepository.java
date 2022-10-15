package com.vilia.gameattendance.users;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

	public Optional<User> findByUsername(String userName);
	public boolean existsByUsername(String userName);
}
