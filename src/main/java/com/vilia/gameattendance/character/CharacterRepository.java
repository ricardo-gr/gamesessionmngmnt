package com.vilia.gameattendance.character;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CharacterRepository extends CrudRepository<com.vilia.gameattendance.character.Character, Long> {
	
	List<com.vilia.gameattendance.character.Character> findByUserId(Long id);

}
