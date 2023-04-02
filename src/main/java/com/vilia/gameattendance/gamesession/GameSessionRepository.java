package com.vilia.gameattendance.gamesession;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameSessionRepository extends JpaRepository<GameSession, Long> {
	
	//List<GameSession> findByDateGreaterThan(LocalDate date); //TODO: Check whether this works with time sorting
	//List<GameSession> findByDateLesserThan(LocalDate date);

}
