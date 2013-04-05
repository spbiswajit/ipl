package com.qaitdevlabs.ipl.dao;

import com.qaitdevlabs.ipl.domain.User;

public interface UserDao {

	public User validateUser(User user);
	public void update(User user);
	public double getHighestUserScore();
	public double getLowestUserScore();
	public int getAllUsersCount();
	public int getUserPosition(double userScore);
}
