package com.qaitdevlabs.ipl.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.qaitdevlabs.ipl.dao.UserChoiceDao;
import com.qaitdevlabs.ipl.domain.UserChoice;
import com.qaitdevlabs.ipl.exception.IplCustomException;

public class UserChoiceDaoImpl extends HibernateDaoSupport implements UserChoiceDao {

	@Override
	public List<UserChoice> getAllUserChoices(long userId) {
		Session session = null;
		List<UserChoice> list = null;
		try {
 			 session = getSessionFactory().openSession();
			 String queryString = "from UserChoice uc where uc.user.id = :id ";
			 Query query = session.createQuery(queryString);
			 query.setParameter("id", userId);
			 list = query.list();		
		} catch (Exception e) {
			throw new IplCustomException();
		} finally {
			session.close();
		}
		return list;		
	}

	@Override
	public void save(UserChoice userChoice) {
		Session session = null;
		try {
 			 session = getSessionFactory().openSession();
			 session.save(userChoice);		
		} catch (Exception e) {
			throw new IplCustomException();
		} finally {
			session.flush();
			session.close();
		}
	}

	@Override
	public void update(UserChoice userChoice) {
		Session session = null;
		try {
 			 session = getSessionFactory().openSession();
			 session.update(userChoice);		
		} catch (Exception e) {
			throw new IplCustomException();
		} finally {
			session.flush();
			session.close();
		}
	}
	
	@Override
	public UserChoice getUserChoiceByMatch(long matchId, long userId) {
		Session session = null;
		UserChoice userChoice = null;
		try {
 			 session = getSessionFactory().openSession();
			 String queryString = "from UserChoice uc where uc.user.id = :id and uc.match.id = :matchId";
			 Query query = session.createQuery(queryString);
			 query.setParameter("id", userId);
			 query.setParameter("matchId", matchId);
			 userChoice = (UserChoice)query.uniqueResult();		
		} catch (Exception e) {
			throw new IplCustomException();
		} finally {
			session.close();
		}
		return userChoice;		
	}
}
