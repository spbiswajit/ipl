package com.qaitdevlabs.ipl.login.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.qaitdevlabs.ipl.domain.User;
import com.qaitdevlabs.ipl.login.form.LoginForm;
import com.qaitdevlabs.ipl.service.UserService;


@SuppressWarnings("deprecation")
public class LoginController extends SimpleFormController {
	
	private UserService userService;

	public LoginController() {
		setCommandName("loginForm");
	}
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		LoginForm loginForm = (LoginForm)command;
		User savedUser = userService.validateUser(loginForm);
		HttpSession session = request.getSession();
		if (savedUser != null && session != null) {
			session.setAttribute("user", savedUser);
			return new ModelAndView(getSuccessView());
		}
		 return new ModelAndView(getFormView());	
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
