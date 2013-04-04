package com.qaitdevlabs.ipl.matchesView.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.qaitdevlabs.ipl.domain.MatchDetails;
import com.qaitdevlabs.ipl.domain.User;
import com.qaitdevlabs.ipl.domain.UserChoice;
import com.qaitdevlabs.ipl.dto.MatchDetailsDto;
import com.qaitdevlabs.ipl.service.MatchDetailsService;
import com.qaitdevlabs.ipl.service.UserChoiceService;
import com.qaitdevlabs.ipl.util.IplUtil;

@SuppressWarnings("deprecation")
public class MatchesViewController extends SimpleFormController {
	
	private MatchDetailsService matchDetailService;
	private UserChoiceService userChoiceService;
	
	public MatchesViewController() {
		setCommandName("matchesViewForm");
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException, Exception {

		HttpSession session = request.getSession(false);
		if (session == null || (session != null && session.getAttribute("user") == null)) {
			return new ModelAndView("loginPageRedirect");
		}else {
	        return super.handleRequest(request,response);
	    }
	}
	
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map referenceData = new HashMap();
		HttpSession session  = request.getSession(false);
		boolean flag = false;
		if(session != null) {
			User user = (User)session.getAttribute("user");
			List<UserChoice> userChoiceList = userChoiceService.getAllUserChoices(user.getId());
			
			List<MatchDetails> list = matchDetailService.getAllMatchDetails();
			if(list != null) {
				List<MatchDetailsDto> matchDetailList = new ArrayList<MatchDetailsDto>();
				for(MatchDetails matchDetails : list) {
					MatchDetailsDto matchDetailsDto = new MatchDetailsDto();
					matchDetailsDto.setTeam1(matchDetails.getTeam1().getTeamName());
					matchDetailsDto.setTeam2(matchDetails.getTeam2().getTeamName());
					matchDetailsDto.setVenue(matchDetails.getVenue());
					matchDetailsDto.setMatchTime(new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(IplUtil.covnvertDateToIST(matchDetails.getMatchDate())));
				//	matchDetailsDto.setMatchTime(matchDetails.getMatchDate().toString()); 
					matchDetailsDto.setMatchId(matchDetails.getId());
					matchDetailsDto.setTeam1Id(matchDetails.getTeam1().getId());
					matchDetailsDto.setTeam2Id(matchDetails.getTeam2().getId());
					for(UserChoice choice : userChoiceList) {
						if(choice.getMatch().getId().longValue() == matchDetails.getId().longValue()) {
							matchDetailsDto.setUserChoice(choice.getUserChoice().getId());
							matchDetailsDto.setUserBid(choice.getUserBid());
							flag = true;
							break;
						}
					}
					if(!flag) {
						matchDetailsDto.setUserChoice(0);
					}
					matchDetailList.add(matchDetailsDto);
				}
				referenceData.put("matchDetails", matchDetailList);
			}
		}
		
		return referenceData;
	}
	
	public MatchDetailsService getMatchDetailService() {
		return matchDetailService;
	}

	public void setMatchDetailService(MatchDetailsService matchDetailService) {
		this.matchDetailService = matchDetailService;
	}

	public UserChoiceService getUserChoiceService() {
		return userChoiceService;
	}

	public void setUserChoiceService(UserChoiceService userChoiceService) {
		this.userChoiceService = userChoiceService;
	}
}
