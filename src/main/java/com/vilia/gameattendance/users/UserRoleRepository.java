package com.vilia.gameattendance.users;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

	public Optional<UserRole> findByRoleName(String roleName);
}
