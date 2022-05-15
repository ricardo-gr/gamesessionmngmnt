package com.vilia.gameattendance.model;

import java.io.Serializable;
import java.util.Objects;

public class CharacterGameSessionId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8150583499295104832L;
	
	private long character;
	private long gameSession;
	public long getCharacter() {
		return character;
	}
	public void setCharacter(long character) {
		this.character = character;
	}
	public long getGameSession() {
		return gameSession;
	}
	public void setGameSession(long gameSession) {
		this.gameSession = gameSession;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null || getClass() != obj.getClass())
			return false;
		
		CharacterGameSessionId cgsid = (CharacterGameSessionId) obj;
		return cgsid.getCharacter() == this.character && cgsid.getGameSession() == this.gameSession;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.character, this.gameSession);
	}
	

}
