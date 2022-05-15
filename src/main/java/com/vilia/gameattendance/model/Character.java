package com.vilia.gameattendance.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
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
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<CharacterGameSession> gameSessions;

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
