package com.blog.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.blog.domain.User;

@Repository
public class RegisterDao {
	@Resource
	public SessionFactory sessionFactory;

	public void register(User user) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(user);
		transaction.commit();
	}
}
