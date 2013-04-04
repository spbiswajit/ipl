package com.qaitdevlabs.ipl.service.impl;

import java.util.List;

import com.qaitdevlabs.ipl.dao.UserChoiceDao;
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
}
