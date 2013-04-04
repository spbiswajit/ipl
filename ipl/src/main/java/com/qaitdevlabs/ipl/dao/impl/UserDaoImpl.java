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

}
