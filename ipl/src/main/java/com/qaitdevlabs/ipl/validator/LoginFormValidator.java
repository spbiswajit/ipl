package com.qaitdevlabs.ipl.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.qaitdevlabs.ipl.domain.User;
import com.qaitdevlabs.ipl.login.form.LoginForm;
import com.qaitdevlabs.ipl.service.UserService;

public class LoginFormValidator implements Validator {

	private UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return LoginForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (!errors.hasErrors()) {
			User user;
			try {
				user = userService.validateUser((LoginForm) target);
				if (user == null) {
					errors.rejectValue("userName","userName.password.incorrect");
				}  
			} catch (Exception e) {
				errors.rejectValue("userName","userName.password.incorrect");
			}
		}
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
