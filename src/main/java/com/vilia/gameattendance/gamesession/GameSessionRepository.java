package com.vilia.gameattendance.gamesession;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface GameSessionRepository extends CrudRepository<GameSession, Long> {
	
	//List<GameSession> findByDateGreaterThan(LocalDate date); //TODO: Check whether this works with time sorting
	//List<GameSession> findByDateLesserThan(LocalDate date);

}
