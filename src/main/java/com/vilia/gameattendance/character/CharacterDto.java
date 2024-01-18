package com.vilia.gameattendance.character;

import java.util.Arrays;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CharacterDto {
	
	@Min(1)
	private Long characterId;
	@NotNull
	private String characterName;
	private String characterOwnerUsername;
	private byte[] portrait;
	@NotNull
	private boolean active;
	
	public CharacterDto() {}
	
	public CharacterDto(Character character) {
		super();
		this.characterId = character.getId();
		this.characterName = character.getName();
		this.characterOwnerUsername = character.getUser().getUsername();
		this.portrait = character.getPortrait();
		this.active = character.isActive();
	}

	public String getCharacterOwner() {
		return characterOwnerUsername;
	}

	public void setCharacterOwner(String characterOwner) {
		this.characterOwnerUsername = characterOwner;
	}

	public Long getCharacterId() {
		return characterId;
	}

	public void setCharacterId(Long characterId) {
		this.characterId = characterId;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public byte[] getPortrait() {
		return portrait;
	}

	public void setPortrait(byte[] image) {
		this.portrait = image;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "CharacterDto [characterId=" + characterId + ", characterName=" + characterName + ", characterOwner="
				+ characterOwnerUsername + ", image=" + Arrays.toString(portrait) + ", active=" + active + "]";
	}

	

	
}
