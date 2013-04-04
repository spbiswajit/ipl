package com.qaitdevlabs.ipl.userchoice.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.qaitdevlabs.ipl.domain.User;
import com.qaitdevlabs.ipl.service.UserService;

public class ChangePasswordController extends AbstractController {

	private UserService userService;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");

		HttpSession httpSession = request.getSession(true);

		if (httpSession != null) {
			User user = (User) httpSession.getAttribute("user");
			if (user.getPassword().equals(oldPassword)) {
				if (newPassword.equals(confirmPassword)) {
					user.setPassword(newPassword);
					userService.update(user);
					response.setStatus(200);
				} else {
					response.setStatus(500);
				}

			} else {
				response.setStatus(500);
			}
		} else {
			response.setStatus(500);
		}

		return null;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
