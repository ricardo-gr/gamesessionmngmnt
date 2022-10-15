package com.vilia.gameattendance.gamesession;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CharacterGameSessionRepository extends CrudRepository<CharacterGameSession, CharacterGameSessionId> {
	
	List<CharacterGameSession> findByGameSessionId(Long id);

}
