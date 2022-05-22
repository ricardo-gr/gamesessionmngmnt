package com.vilia.gameattendance.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vilia.gameattendance.model.CharacterGameSession;
import com.vilia.gameattendance.model.CharacterGameSessionId;

public interface CharacterGameSessionRepository extends CrudRepository<CharacterGameSession, CharacterGameSessionId> {
	
	List<CharacterGameSession> findByGameSessionId(Long id);

}
