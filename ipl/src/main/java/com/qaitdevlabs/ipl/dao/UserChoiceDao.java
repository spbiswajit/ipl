package com.qaitdevlabs.ipl.dao;

import java.util.List;

import com.qaitdevlabs.ipl.domain.UserChoice;

public interface UserChoiceDao {

	public List<UserChoice> getAllUserChoices(long userId);
	public void save(UserChoice userChoice);
	public UserChoice getUserChoiceByMatch(long matchId, long userId);
	public void update(UserChoice userChoice);
	public List<UserChoice> getChoicesForAllUsers();
}
