package com.qaitdevlabs.ipl.jobs.scoreupdatejob;

import java.util.ArrayList;
import java.util.List;

import com.qaitdevlabs.ipl.domain.MatchDetails;
import com.qaitdevlabs.ipl.domain.User;
import com.qaitdevlabs.ipl.domain.UserChoice;
import com.qaitdevlabs.ipl.service.MatchDetailsService;
import com.qaitdevlabs.ipl.service.UserChoiceService;

public class ScoreUpdateJob {

	private MatchDetailsService matchDetailsService;
	private UserChoiceService userChoiceService;
	
	public void startProcessing() {
		List<MatchDetails> matchList = matchDetailsService.getMatchDetailsForJob();
		if(matchList.size() > 0) {
			for(MatchDetails matchDetails : matchList) {
				long matchDetailId = matchDetails.getId();
				long winnerId = matchDetails.getWinnerTeam().getId();
				
				int totalPointsToDistribute = userChoiceService.getLooserUsersBidTotalForMatch(matchDetailId, winnerId);
				int ratioTotalForUsers = userChoiceService.getWinnerUsersBidTotalForMatch(matchDetailId, winnerId);
				
				List<UserChoice> winnerUserChoiceList = userChoiceService.getWinnerUserChoicesForMatch(matchDetailId, winnerId);
				List<UserChoice> looserUserChoiceList = userChoiceService.getLooserUserChoicesForMatch(matchDetailId, winnerId);
				List<UserChoice> userChoiceListToUpdate = new ArrayList<UserChoice>();
				
				for(UserChoice winnerUserChoice : winnerUserChoiceList) {
					User user = winnerUserChoice.getUser();
					double userEarnedBidPoints = ((double)winnerUserChoice.getUserBid()/ratioTotalForUsers)*totalPointsToDistribute;
					user.setTotalScore(user.getTotalScore()+userEarnedBidPoints);
					winnerUserChoice.setUserScore(userEarnedBidPoints);
					winnerUserChoice.setUser(user);
					userChoiceListToUpdate.add(winnerUserChoice);
					user=null;
				}
				
				for(UserChoice looserUserChoice : looserUserChoiceList) {
					User user = looserUserChoice.getUser();
					double userEarnedBidPoints = -looserUserChoice.getUserBid();
					user.setTotalScore(user.getTotalScore()+userEarnedBidPoints);
					looserUserChoice.setUserScore(userEarnedBidPoints);
					looserUserChoice.setUser(user);
					userChoiceListToUpdate.add(looserUserChoice);
					user=null;
				}
				
				matchDetails.setScoreUpdated(true);
				
				userChoiceService.updateUserChoiceListForJob(userChoiceListToUpdate, matchDetails);
				
				winnerUserChoiceList = null;
				looserUserChoiceList = null;
			}
			
		}
		matchList=null;
	}

	public MatchDetailsService getMatchDetailsService() {
		return matchDetailsService;
	}

	public void setMatchDetailsService(MatchDetailsService matchDetailsService) {
		this.matchDetailsService = matchDetailsService;
	}

	public UserChoiceService getUserChoiceService() {
		return userChoiceService;
	}

	public void setUserChoiceService(UserChoiceService userChoiceService) {
		this.userChoiceService = userChoiceService;
	}
}
