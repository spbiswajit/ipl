package com.qaitdevlabs.ipl.service;

import java.util.List;

import com.qaitdevlabs.ipl.domain.MatchDetails;
import com.qaitdevlabs.ipl.domain.Team;

public interface MatchDetailsService {

	public List<MatchDetails> getAllMatchDetails() throws Exception;
	public MatchDetails getMatchDetailById(long matchId);
	public Team getTeamById(long teamid);
	public List<MatchDetails> getMatchDetailsForJob();
}
