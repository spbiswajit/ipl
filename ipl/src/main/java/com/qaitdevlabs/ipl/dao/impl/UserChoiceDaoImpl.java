package com.qaitdevlabs.ipl.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.qaitdevlabs.ipl.dao.UserChoiceDao;
import com.qaitdevlabs.ipl.domain.MatchDetails;
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
			System.out.println(e.getMessage());
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

	@Override
	public List<UserChoice> getChoicesForAllUsers() {
		Session session = null;
		List<UserChoice> list = null;
		try {
 			 session = getSessionFactory().openSession();
			 String queryString = "from UserChoice";
			 Query query = session.createQuery(queryString);
			 list = query.list();		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new IplCustomException();
		} finally {
			session.close();
		}
		return list;		
	}

	@Override
	public List<UserChoice> getWinnerUserChoicesForMatch(long matchDetailId, long winnerId) {
		Session session = null;
		List<UserChoice> list = null;
		try {
 			 session = getSessionFactory().openSession();
			 String queryString = "from UserChoice uc where uc.userChoice.id = :wid and uc.match.id = :mid ";
			 Query query = session.createQuery(queryString);
			 query.setParameter("wid", winnerId);
			 query.setParameter("mid", matchDetailId);
			 list = query.list();		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new IplCustomException();
		} finally {
			session.close();
		}
		return list;		
	}

	@Override
	public List<UserChoice> getLooserUserChoicesForMatch(long matchDetailId, long winnerId) {
		Session session = null;
		List<UserChoice> list = null;
		try {
 			 session = getSessionFactory().openSession();
			 String queryString = "from UserChoice uc where uc.userChoice.id <> :wid and uc.match.id = :mid ";
			 Query query = session.createQuery(queryString);
			 query.setParameter("wid", winnerId);
			 query.setParameter("mid", matchDetailId);
			 list = query.list();		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new IplCustomException();
		} finally {
			session.close();
		}
		return list;		
	}

	@Override
	public int getLooserUsersBidTotalForMatch(long matchDetailId, long winnerId) {
		Session session = null;
		int total = 0;
		try {
 			 session = getSessionFactory().openSession();
			 String queryString = "Select sum(uc.userBid) from UserChoice uc where uc.userChoice.id <> :wid and uc.match.id = :mid ";
			 Query query = session.createQuery(queryString);
			 query.setParameter("wid", winnerId);
			 query.setParameter("mid", matchDetailId);
			 total = ((Number)query.uniqueResult()).intValue();			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new IplCustomException();
		} finally {
			session.close();
		}
		return total;		
	}

	@Override
	public int getWinnerUsersBidTotalForMatch(long matchDetailId, long winnerId) {
		Session session = null;
		int total = 0;
		try {
 			 session = getSessionFactory().openSession();
			 String queryString = "Select sum(uc.userBid) from UserChoice uc where uc.userChoice.id = :wid and uc.match.id = :mid ";
			 Query query = session.createQuery(queryString);
			 query.setParameter("wid", winnerId);
			 query.setParameter("mid", matchDetailId);
			 total = ((Number)query.uniqueResult()).intValue();			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new IplCustomException();
		} finally {
			session.close();
		}
		return total;		
	}

	@Override
	public void updateUserChoiceListForJob(List<UserChoice> userChoiceList,
			MatchDetails matchDetails) {
		Session session = null;
		Transaction tx = null;
		try {
 			 session = getSessionFactory().openSession();
 			 tx = session.beginTransaction();
 			 int counter = 1;
 			 for(UserChoice userChoice : userChoiceList) {
 				 session.update(userChoice);
 				 counter++;
 				 if(counter % 20 == 0) {
 					 session.flush();
 					 session.clear();
 				 }
 			 }
 			 session.update(matchDetails);
			 tx.commit();			
		} catch (Exception e) {
			if(tx != null) {
				tx.rollback();
				tx = null;
			}
		} finally {
			if(tx != null) {
				session.flush();
			}
			session.close();
		}		
	}
}
