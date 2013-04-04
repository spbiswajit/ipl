package com.qaitdevlabs.ipl.domain;

import java.util.Date;

public class MatchDetails {

	private Long id;
	private Team team1;
	private Team team2;
	private String venue;
	private Date matchDate;
	private Team winnerTeam;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Team getTeam1() {
		return team1;
	}
	public void setTeam1(Team team1) {
		this.team1 = team1;
	}
	public Team getTeam2() {
		return team2;
	}
	public void setTeam2(Team team2) {
		this.team2 = team2;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public Date getMatchDate() {
		return matchDate;
	}
	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}
	public Team getWinnerTeam() {
		return winnerTeam;
	}
	public void setWinnerTeam(Team winnerTeam) {
		this.winnerTeam = winnerTeam;
	}
}
