package com.slickcode.fdms.service.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.slickcode.fdms.service.domain.SecurityQuestionDO;

@Component("securityQuestionDaoImpl")
public class SecurityQuestionDaoImpl implements ISecurityQuestionDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<SecurityQuestionDO> fetchAll() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<SecurityQuestionDO> list = null;
		try {
			tx = session.beginTransaction();
			list = session.createQuery("FROM SecurityQuestionDO").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

}
