package com.qaitdevlabs.ipl.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.qaitdevlabs.ipl.dao.UserDao;
import com.qaitdevlabs.ipl.domain.User;
import com.qaitdevlabs.ipl.exception.IplCustomException;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public User validateUser(User user) {
		Session session = null;
		User savedUser = null;
		try {
 			 session = getSessionFactory().openSession();
			 String queryString = "from User where username = :username and password =:password";
			 Query query = session.createQuery(queryString);
			 query.setString("username", user.getUserName());
			 query.setString("password", user.getPassword());
			 savedUser = (User) query.uniqueResult();			
		} catch (Exception e) {
			throw new IplCustomException();
		} finally {
			session.close();
		}
		return savedUser;		
	}

	@Override
	public void update(User user) {
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			session.update(user);
		} catch (Exception e) {
			throw new IplCustomException();
		} finally {
			session.flush();
			session.close();
		}

	}

	@Override
	public double getHighestUserScore() {
		Session session = null;
		double totalScore = 0.0;
		try {
 			 session = getSessionFactory().openSession();
			 String queryString = "Select max(totalScore) from User";
			 Query query = session.createQuery(queryString);
			 totalScore = ((Number)query.uniqueResult()).doubleValue();			
		} catch (Exception e) {
			throw new IplCustomException();
		} finally {
			session.close();
		}
		return totalScore;		
	}

	@Override
	public double getLowestUserScore() {
		Session session = null;
		double totalScore = 0.0;
		try {
 			 session = getSessionFactory().openSession();
			 String queryString = "Select min(totalScore) from User";
			 Query query = session.createQuery(queryString);
			 totalScore = ((Number)query.uniqueResult()).doubleValue();			
		} catch (Exception e) {
			throw new IplCustomException();
		} finally {
			session.close();
		}
		return totalScore;		
	}

	@Override
	public int getAllUsersCount() {
		Session session = null;
		int count = 0;
		try {
 			 session = getSessionFactory().openSession();
			 String queryString = "Select count(*) from User";
			 Query query = session.createQuery(queryString);
			 count = ((Number)query.uniqueResult()).intValue();			
		} catch (Exception e) {
			throw new IplCustomException();
		} finally {
			session.close();
		}
		return count;		
	}

	@Override
	public int getUserPosition(double userScore) {
		Session session = null;
		int pos = -1;
		try {
 			 session = getSessionFactory().openSession();
			 String queryString = "Select count(*) from User where totalScore > :score";
			 Query query = session.createQuery(queryString);
			 query.setParameter("score", userScore);
			 pos = ((Number)query.uniqueResult()).intValue();			
		} catch (Exception e) {
			throw new IplCustomException();
		} finally {
			session.close();
		}
		return pos;		
	}

}
