package com.qaitdevlabs.ipl.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.qaitdevlabs.ipl.dao.MatchDetailDao;
import com.qaitdevlabs.ipl.domain.MatchDetails;
import com.qaitdevlabs.ipl.domain.Team;
import com.qaitdevlabs.ipl.exception.IplCustomException;

public class MatchDetailDaoImpl extends HibernateDaoSupport implements MatchDetailDao {

	@Override
	public List<MatchDetails> getAllMatchDetails() {
		Session session = null;
		List<MatchDetails> list = null;
		try {
 			 session = getSessionFactory().openSession();
			 String queryString = "from MatchDetails";
			 list = session.createQuery(queryString).list();
		} catch (Exception e) {
			throw new IplCustomException();
		} finally {
			session.close();
		}
		return list;		
	}

	@Override
	public MatchDetails getMatchDetailById(long matchId) {
		Session session = null;
		MatchDetails matchDetail = null;
		try {
 			 session = getSessionFactory().openSession();
			 String queryString = "from MatchDetails where id = :matchid";
			 matchDetail = (MatchDetails)session.createQuery(queryString).setParameter("matchid", matchId).uniqueResult();
		} catch (Exception e) {
			throw new IplCustomException();
		} finally {
			session.close();
		}
		return matchDetail;		
	
		
	}

	@Override
	public Team getTeamById(long teamid) {
		Session session = null;
		Team team = null;
		try {
 			 session = getSessionFactory().openSession();
			 String queryString = "from Team where id = :teamid";
			 team = (Team)session.createQuery(queryString).setParameter("teamid", teamid).uniqueResult();
		} catch (Exception e) {
			throw new IplCustomException();
		} finally {
			session.close();
		}
		return team;	
	}

}
