package com.vilia.gameattendance.character;

import java.util.ArrayList;
import java.util.List;

import com.vilia.gameattendance.gamesession.CharacterGameSession;
import com.vilia.gameattendance.users.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "playercharacter")
public class Character {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	@Column(nullable = false)
	private String name;
	@Lob
	private byte[] image; //I might want to change this to a String reference to the location of an image in the future
	@Column(nullable = false)
	private Boolean active;

	@OneToMany(mappedBy = "character", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CharacterGameSession> gameSessions = new ArrayList<>();
	
	public Character() {}

	public Character(CharacterDto character, User owningUser) {
		super();
		this.id = character.getCharacterId();
		this.user = owningUser;
		this.name = character.getCharacterName();
		this.image = character.getImage();
		this.active = character.isActive();
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public List<CharacterGameSession> getGameSessions() {
		return gameSessions;
	}

	public void setGameSessions(List<CharacterGameSession> gameSessions) {
		this.gameSessions = gameSessions;
	}

	public void update(CharacterDto character, User newOwner) {
		this.name = character.getCharacterName();
		this.image = character.getImage();
		this.user = newOwner;
		this.active = character.isActive();
	}

}
