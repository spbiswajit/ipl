package com.qaitdevlabs.ipl.service.impl;

import java.util.List;

import com.qaitdevlabs.ipl.dao.UserChoiceDao;
import com.qaitdevlabs.ipl.domain.MatchDetails;
import com.qaitdevlabs.ipl.domain.UserChoice;
import com.qaitdevlabs.ipl.service.UserChoiceService;

public class UserChoiceServiceImpl implements UserChoiceService{

	private UserChoiceDao userChoiceDao; 
	
	@Override
	public List<UserChoice> getAllUserChoices(long userId) {
		return userChoiceDao.getAllUserChoices(userId);
	}

	public UserChoiceDao getUserChoiceDao() {
		return userChoiceDao;
	}

	public void setUserChoiceDao(UserChoiceDao userChoiceDao) {
		this.userChoiceDao = userChoiceDao;
	}

	@Override
	public UserChoice getUserChoiceByMatch(long matchId, long userId) {
		return userChoiceDao.getUserChoiceByMatch(matchId, userId);
	}

	@Override
	public void save(UserChoice userChoice) {
		userChoiceDao.save(userChoice);
	}

	@Override
	public void update(UserChoice userChoice) {
		userChoiceDao.update(userChoice);
	}

	@Override
	public List<UserChoice> getChoicesForAllUsers() {
		return userChoiceDao.getChoicesForAllUsers();
	}

	@Override
	public List<UserChoice> getWinnerUserChoicesForMatch(long matchDetailId,
			long winnerId) {
		return userChoiceDao.getWinnerUserChoicesForMatch(matchDetailId, winnerId);
	}

	@Override
	public List<UserChoice> getLooserUserChoicesForMatch(long matchDetailId,
			long winnerId) {
		 return userChoiceDao.getLooserUserChoicesForMatch(matchDetailId, winnerId);
	}

	@Override
	public int getLooserUsersBidTotalForMatch(long matchDetailId, long winnerId) {
		return userChoiceDao.getLooserUsersBidTotalForMatch(matchDetailId, winnerId);
	}

	@Override
	public int getWinnerUsersBidTotalForMatch(long matchDetailId, long winnerId) {
		return userChoiceDao.getWinnerUsersBidTotalForMatch(matchDetailId, winnerId);
	}

	@Override
	public void updateUserChoiceListForJob(List<UserChoice> userChoiceList,
			MatchDetails matchDetails) {
		 userChoiceDao.updateUserChoiceListForJob(userChoiceList, matchDetails);
	}
}
