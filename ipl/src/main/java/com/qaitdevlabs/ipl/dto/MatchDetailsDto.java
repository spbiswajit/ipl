package com.qaitdevlabs.ipl.dto;

public class MatchDetailsDto {

	private String team1;
	private String team2;
	private String venue;
	private String matchTime;
	private long team1Id;
	private long team2Id;
	private long matchId;
	private long userChoice;
	private int userBid;
	private String winnerTeam;
	private String teamOdds;
	
	public String getTeam1() {
		return team1;
	}
	public void setTeam1(String team1) {
		this.team1 = team1;
	}
	public String getTeam2() {
		return team2;
	}
	public void setTeam2(String team2) {
		this.team2 = team2;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getMatchTime() {
		return matchTime;
	}
	public void setMatchTime(String matchTime) {
		this.matchTime = matchTime;
	}
	public long getTeam1Id() {
		return team1Id;
	}
	public void setTeam1Id(long team1Id) {
		this.team1Id = team1Id;
	}
	public long getTeam2Id() {
		return team2Id;
	}
	public void setTeam2Id(long team2Id) {
		this.team2Id = team2Id;
	}
	public long getMatchId() {
		return matchId;
	}
	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}
	public long getUserChoice() {
		return userChoice;
	}
	public void setUserChoice(long userChoice) {
		this.userChoice = userChoice;
	}
	public int getUserBid() {
		return userBid;
	}
	public void setUserBid(int userBid) {
		this.userBid = userBid;
	}
	public String getWinnerTeam() {
		return winnerTeam;
	}
	public void setWinnerTeam(String winnerTeam) {
		this.winnerTeam = winnerTeam;
	}
	public String getTeamOdds() {
		return teamOdds;
	}
	public void setTeamOdds(String teamOdds) {
		this.teamOdds = teamOdds;
	}
}
