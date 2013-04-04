package com.qaitdevlabs.ipl.domain;

import java.util.Date;

public class UserChoice {

	private Long id;
	private User user;
	private MatchDetails match;
	private Team userChoice;
	private Date lastUpdated;
	
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
	public MatchDetails getMatch() {
		return match;
	}
	public void setMatch(MatchDetails match) {
		this.match = match;
	}
	public Team getUserChoice() {
		return userChoice;
	}
	public void setUserChoice(Team userChoice) {
		this.userChoice = userChoice;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
}
