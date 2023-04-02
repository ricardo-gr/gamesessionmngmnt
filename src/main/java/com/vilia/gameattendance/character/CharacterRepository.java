package com.vilia.gameattendance.character;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<com.vilia.gameattendance.character.Character, Long> {
	
	List<com.vilia.gameattendance.character.Character> findByUserId(Long id);

}
