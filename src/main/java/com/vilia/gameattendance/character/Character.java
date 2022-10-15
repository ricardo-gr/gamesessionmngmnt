package com.vilia.gameattendance.character;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "playercharacter")
public class Character {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	@Column
	private String name;
	@Column
	private byte[] image;
	@Column
	private boolean active;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<CharacterGameSession> gameSessions;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

}
