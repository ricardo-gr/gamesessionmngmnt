package com.vilia.gameattendance.repositories;

import org.springframework.data.repository.CrudRepository;

import com.vilia.gameattendance.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
