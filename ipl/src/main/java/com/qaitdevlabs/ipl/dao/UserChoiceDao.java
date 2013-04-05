package com.qaitdevlabs.ipl.dao;

import java.util.List;

import com.qaitdevlabs.ipl.domain.MatchDetails;
import com.qaitdevlabs.ipl.domain.UserChoice;

public interface UserChoiceDao {

	public List<UserChoice> getAllUserChoices(long userId);
	public void save(UserChoice userChoice);
	public UserChoice getUserChoiceByMatch(long matchId, long userId);
	public void update(UserChoice userChoice);
	public List<UserChoice> getChoicesForAllUsers();
	public List<UserChoice> getWinnerUserChoicesForMatch(long matchDetailId, long winnerId);
	public List<UserChoice> getLooserUserChoicesForMatch(long matchDetailId, long winnerId);
	public int getLooserUsersBidTotalForMatch(long matchDetailId, long winnerId);
	public int getWinnerUsersBidTotalForMatch(long matchDetailId, long winnerId);
	public void updateUserChoiceListForJob(List<UserChoice> userChoiceList, MatchDetails matchDetails);
}
