package com.qaitdevlabs.ipl.login.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.qaitdevlabs.ipl.login.form.LoginForm;


public class LoginFailedController extends AbstractController {

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("loginForm", new LoginForm());
		modelAndView.addObject("error", "true");
		return modelAndView;
	}

}

