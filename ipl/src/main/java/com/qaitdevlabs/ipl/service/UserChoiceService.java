package com.qaitdevlabs.ipl.service;

import java.util.List;

import com.qaitdevlabs.ipl.domain.UserChoice;

public interface UserChoiceService {
  
	 public List<UserChoice> getAllUserChoices(long userId);
	 public UserChoice getUserChoiceByMatch(long matchId, long userId);
	 public void save(UserChoice userChoice);
	 public void update(UserChoice userChoice);
	 public List<UserChoice> getChoicesForAllUsers();
}
