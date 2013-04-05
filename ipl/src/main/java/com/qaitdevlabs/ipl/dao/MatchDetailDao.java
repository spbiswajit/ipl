package com.qaitdevlabs.ipl.dao;

import java.util.List;

import com.qaitdevlabs.ipl.domain.MatchDetails;
import com.qaitdevlabs.ipl.domain.Team;

public interface MatchDetailDao {
	
	public List<MatchDetails> getAllMatchDetails();
	public MatchDetails getMatchDetailById(long matchId);
	public Team getTeamById(long teamid);
	public List<MatchDetails> getMatchDetailsForJob();
}
