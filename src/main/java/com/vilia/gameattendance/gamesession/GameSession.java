package com.vilia.gameattendance.gamesession;

import java.time.LocalDate;
import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.List;

import com.vilia.gameattendance.users.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class GameSession {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private LocalDate date;
	@Column
	private OffsetTime time;
	@Column
	private Integer max_characters;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creating_user_id")
	private User creatingUser;
	
	@OneToMany(mappedBy = "gameSession", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CharacterGameSession> joinedCharacters = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public OffsetTime getTime() {
		return time;
	}

	public void setTime(OffsetTime time) {
		this.time = time;
	}

	public Integer getMax_characterse() {
		return max_characters;
	}

	public void setMax_characterse(Integer max_characters) {
		this.max_characters = max_characters;
	}

	public User getCreatingUser() {
		return creatingUser;
	}

	public void setCreatingUser(User creatingUser) {
		this.creatingUser = creatingUser;
	}

	public List<CharacterGameSession> getJoinedCharacters() {
		return joinedCharacters;
	}

	public void setJoinedCharacters(List<CharacterGameSession> joinedCharacters) {
		this.joinedCharacters = joinedCharacters;
	}

}
