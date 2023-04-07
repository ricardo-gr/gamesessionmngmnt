package com.vilia.gameattendance.character;

import java.util.Arrays;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CharacterDto {
	
	@Min(1)
	private Long characterId;
	@NotNull
	private String characterName;
	private String characterOwner;
	private byte[] image;
	@NotNull
	private boolean active;
	
	public CharacterDto() {}
	
	public CharacterDto(Character character) {
		super();
		this.characterId = character.getId();
		this.characterName = character.getName();
		this.characterOwner = character.getUser().getUsername();
		this.image = character.getImage();
		this.active = character.isActive();
	}

	public String getCharacterOwner() {
		return characterOwner;
	}

	public void setCharacterOwner(String characterOwner) {
		this.characterOwner = characterOwner;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
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
				+ characterOwner + ", image=" + Arrays.toString(image) + ", active=" + active + "]";
	}

	

	
}
