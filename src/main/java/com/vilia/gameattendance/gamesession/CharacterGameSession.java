package com.vilia.gameattendance.gamesession;

import java.time.OffsetDateTime;

import com.vilia.gameattendance.character.Character;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@IdClass(CharacterGameSessionId.class)
public class CharacterGameSession {
	@Id
	@ManyToOne
	@JoinColumn(name = "character_id", referencedColumnName = "id")
	private Character character;

	@Id
	@ManyToOne
	@JoinColumn(name = "game_session_id", referencedColumnName = "id")
	private GameSession gameSession;

	@Column
	private Boolean presential;

	@Column
	private Boolean remote;

	@Column
	private OffsetDateTime timestamp;

	public OffsetDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(OffsetDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public GameSession getGameSession() {
		return gameSession;
	}

	public void setGameSession(GameSession gameSession) {
		this.gameSession = gameSession;
	}

	public Boolean getPresential() {
		return presential;
	}

	public void setPresential(Boolean presential) {
		this.presential = presential;
	}

	public Boolean getRemote() {
		return remote;
	}

	public void setRemote(Boolean remote) {
		this.remote = remote;
	}

}
