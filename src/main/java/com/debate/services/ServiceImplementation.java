package com.debate.services;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.debate.entities.Students;

@Service
public class ServiceImplementation implements Services {
	private SessionFactory sessionFactory;

	private Session session;

	@Autowired
	public ServiceImplementation(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException ex) {
			session = sessionFactory.openSession();
		}
	}

	@Override
	public List<Students> findAll() {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		List<Students> students = session.createQuery("from Students").list();
		tx.commit();
		return students;
	}

	@Override
	@Transactional
	public Students findById(int id) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		Students student = session.get(Students.class, id);
		tx.commit();
		return student;
	}

	@Override
	public void save(Students student) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(student);
		tx.commit();

	}

	@Override
	@Transactional
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		Students student = session.get(Students.class, id);
		session.delete(student);
		tx.commit();

	}


}
