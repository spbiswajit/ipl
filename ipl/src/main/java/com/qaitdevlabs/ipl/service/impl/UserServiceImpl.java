package com.qaitdevlabs.ipl.service.impl;

import com.qaitdevlabs.ipl.dao.UserDao;
import com.qaitdevlabs.ipl.domain.User;
import com.qaitdevlabs.ipl.login.form.LoginForm;
import com.qaitdevlabs.ipl.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	@Override
	public User validateUser(LoginForm loginForm) throws Exception {
		User user =  new User();
		user.setUserName(loginForm.getUserName());
		user.setPassword(loginForm.getPassword());
		return userDao.validateUser(user);
	}

	public UserDao getUserDao() {
		return userDao;
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
