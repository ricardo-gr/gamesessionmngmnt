package com.vilia.gameattendance.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CharacterRepository extends CrudRepository<com.vilia.gameattendance.model.Character, Long> {
	
	List<com.vilia.gameattendance.model.Character> findByUserId(Long id);

}
