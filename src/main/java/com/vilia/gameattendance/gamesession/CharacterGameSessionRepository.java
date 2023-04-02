package com.vilia.gameattendance.gamesession;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterGameSessionRepository extends JpaRepository<CharacterGameSession, CharacterGameSessionId> {
	
	List<CharacterGameSession> findByGameSessionId(Long id);

}
