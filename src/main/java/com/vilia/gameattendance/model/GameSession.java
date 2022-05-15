package com.vilia.gameattendance.model;

import java.time.LocalDate;
import java.time.OffsetTime;
import java.util.ArrayList;
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
public class GameSession {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column
	private LocalDate date;
	@Column
	private OffsetTime time;
	@Column
	private Integer max_characterse;
	@ManyToOne(fetch = FetchType.LAZY)
	private User creatingUser;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<CharacterGameSession> joinedCharacters = new ArrayList<CharacterGameSession>();

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
		return max_characterse;
	}

	public void setMax_characterse(Integer max_characterse) {
		this.max_characterse = max_characterse;
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
