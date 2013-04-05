package com.qaitdevlabs.ipl.service.impl;

import java.util.List;

import com.qaitdevlabs.ipl.dao.MatchDetailDao;
import com.qaitdevlabs.ipl.domain.MatchDetails;
import com.qaitdevlabs.ipl.domain.Team;
import com.qaitdevlabs.ipl.service.MatchDetailsService;

public class MatchDetailServiceImpl implements MatchDetailsService {

	private MatchDetailDao matchDetailDao;
	
	public List<MatchDetails> getAllMatchDetails() {
		return matchDetailDao.getAllMatchDetails();
	}

	public MatchDetailDao getMatchDetailDao() {
		return matchDetailDao;
	}

	public void setMatchDetailDao(MatchDetailDao matchDetailDao) {
		this.matchDetailDao = matchDetailDao;
	}

	@Override
	public MatchDetails getMatchDetailById(long matchId) {
		return matchDetailDao.getMatchDetailById(matchId);
	}

	@Override
	public Team getTeamById(long teamid) {
		return matchDetailDao.getTeamById(teamid);
	}

	@Override
	public List<MatchDetails> getMatchDetailsForJob() {
		return matchDetailDao.getMatchDetailsForJob();
	}
}
