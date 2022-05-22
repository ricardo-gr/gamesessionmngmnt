package com.vilia.gameattendance.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vilia.gameattendance.model.GameSession;

public interface GameSessionRepository extends CrudRepository<GameSession, Long> {
	
	//List<GameSession> findByDateGreaterThan(LocalDate date); //TODO: Check whether this works with time sorting
	//List<GameSession> findByDateLesserThan(LocalDate date);

}
