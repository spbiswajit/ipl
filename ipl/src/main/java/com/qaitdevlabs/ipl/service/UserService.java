package com.qaitdevlabs.ipl.service;

import com.qaitdevlabs.ipl.domain.User;
import com.qaitdevlabs.ipl.login.form.LoginForm;


public interface UserService {
	
	public User validateUser(LoginForm loginForm) throws Exception;
}
