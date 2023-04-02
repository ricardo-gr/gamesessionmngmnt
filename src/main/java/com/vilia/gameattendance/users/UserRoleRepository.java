package com.vilia.gameattendance.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

	public Optional<UserRole> findByRoleName(String roleName);
}
