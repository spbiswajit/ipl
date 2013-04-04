package com.qaitdevlabs.ipl.userchoice.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.qaitdevlabs.ipl.domain.Team;
import com.qaitdevlabs.ipl.domain.User;
import com.qaitdevlabs.ipl.domain.UserChoice;
import com.qaitdevlabs.ipl.service.MatchDetailsService;
import com.qaitdevlabs.ipl.service.UserChoiceService;

public class UserChoiceController extends AbstractController {

	private UserChoiceService userChoiceService;
	private MatchDetailsService matchDetailsService;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		long matchId = Long.valueOf(request.getParameter("matchId"));
		long teamId = Long.valueOf(request.getParameter("teamId"));
		
		HttpSession httpSession = request.getSession(true);
		if(httpSession != null) {
			User user = (User)httpSession.getAttribute("user");
			UserChoice userChoice = userChoiceService.getUserChoiceByMatch(matchId, user.getId());
			Team userChoiceTeam = matchDetailsService.getTeamById(teamId);
			if(userChoice!=null) {
				userChoice.setUserChoice(userChoiceTeam);
				userChoice.setLastUpdated(new Date());
				userChoiceService.update(userChoice);
			} else {
				UserChoice newUserChoice = new UserChoice();
				newUserChoice.setUser(user);
				newUserChoice.setMatch(matchDetailsService.getMatchDetailById(matchId));
				newUserChoice.setUserChoice(userChoiceTeam);
				newUserChoice.setLastUpdated(new Date());
				userChoiceService.save(newUserChoice);
			}
		} else {
			response.setStatus(500);
		}
		return null;
	}

	public UserChoiceService getUserChoiceService() {
		return userChoiceService;
	}

	public void setUserChoiceService(UserChoiceService userChoiceService) {
		this.userChoiceService = userChoiceService;
	}

	public MatchDetailsService getMatchDetailsService() {
		return matchDetailsService;
	}

	public void setMatchDetailsService(MatchDetailsService matchDetailsService) {
		this.matchDetailsService = matchDetailsService;
	}

}
