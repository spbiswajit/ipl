package com.qaitdevlabs.ipl.matchesView.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.qaitdevlabs.ipl.domain.MatchDetails;
import com.qaitdevlabs.ipl.domain.Team;
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
		Map<Long, List<UserChoice>> userChoiceMap = new LinkedHashMap<Long, List<UserChoice>>();
		boolean flag = false;
		
		if(session != null) {
			User user = (User)session.getAttribute("user");
			List<UserChoice> userChoiceList = userChoiceService.getAllUserChoices(user.getId());
			
			prepareUserChocieMap(userChoiceService.getChoicesForAllUsers(), userChoiceMap);
			
			List<MatchDetails> list = matchDetailService.getAllMatchDetails();
			if(list != null) {
				List<MatchDetailsDto> matchDetailList = new ArrayList<MatchDetailsDto>();
				for(MatchDetails matchDetails : list) {
					Team team1 = matchDetails.getTeam1();
					Team team2 = matchDetails.getTeam2();
					MatchDetailsDto matchDetailsDto = new MatchDetailsDto();
					matchDetailsDto.setTeam1(team1.getTeamName());
					matchDetailsDto.setTeam2(team2.getTeamName());
					matchDetailsDto.setVenue(matchDetails.getVenue());
					matchDetailsDto.setMatchTime(new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(IplUtil.covnvertDateToIST(matchDetails.getMatchDate())));
				//	matchDetailsDto.setMatchTime(matchDetails.getMatchDate().toString()); 
					matchDetailsDto.setMatchId(matchDetails.getId());
					matchDetailsDto.setTeam1Id(team1.getId());
					matchDetailsDto.setTeam2Id(team2.getId());
					Team winnerTeam = matchDetails.getWinnerTeam();
					matchDetailsDto.setWinnerTeam(winnerTeam == null ? "" : winnerTeam.getTeamName());
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
					
					List<UserChoice> userChoiceListForMatch = userChoiceMap.get(matchDetails.getId());
					if(userChoiceListForMatch != null && userChoiceListForMatch.size() > 0) {
						matchDetailsDto.setTeamOdds(setOdds(team1.getId(), team2.getId(),userChoiceListForMatch));
					} else {
						matchDetailsDto.setTeamOdds("0/0");
					}
					
					matchDetailList.add(matchDetailsDto);
					userChoiceListForMatch = null;
					winnerTeam = null;
					team1 = null;
					team2 = null;
				}
				referenceData.put("matchDetails", matchDetailList);
			}
		}
		
		return referenceData;
	}
	
	private String setOdds(long team1Id, long team2Id, List<UserChoice> userChoiceListForMatch) {
		int team1Total = 0;
		int team2Total = 0;
		for(UserChoice userChoice : userChoiceListForMatch) {
			Team team = userChoice.getUserChoice();
			if(team.getId() == team1Id) {
				team1Total+=userChoice.getUserBid();
			} else {
				team2Total+=userChoice.getUserBid();
			}
			team=null;
		}
		return new String(team1Total+"/"+team2Total);
	}
	
	private void prepareUserChocieMap(List<UserChoice> userChoiceList, Map<Long, List<UserChoice>> userChoiceMap) {
		for(UserChoice userChoice : userChoiceList) {
			Long matchId = userChoice.getMatch().getId();
			if(userChoice.getUserChoice() != null) {
				if(userChoiceMap.containsKey(matchId)) {
					userChoiceMap.get(matchId).add(userChoice);
				} else {
					List<UserChoice> userChoiceList1 = new ArrayList<UserChoice>();
					userChoiceList1.add(userChoice);
					userChoiceMap.put(matchId, userChoiceList1);
					userChoiceList1 = null;
				}
				matchId= null;
			}
		}
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
