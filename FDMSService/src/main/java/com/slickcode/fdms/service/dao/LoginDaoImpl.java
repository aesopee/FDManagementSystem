package com.slickcode.fdms.service.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.slickcode.fdms.service.domain.LoginDO;
import com.slickcode.fdms.utils.HibernateUtils;

@Component("loginDaoImpl")
public class LoginDaoImpl implements ILoginDao {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public Integer create(LoginDO loginDO) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer loginId = null;
		try {
			tx = session.beginTransaction();
			loginId = (Integer) session.save(loginDO);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return loginId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoginDO> fetchByCriteria(LoginDO loginDO, boolean isExactSearch) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(LoginDO.class);
		if(null != loginDO) {
			HibernateUtils.addRestrictionToCriteria(criteria, "loginId", loginDO.getLoginId(), isExactSearch);
			HibernateUtils.addRestrictionToCriteria(criteria, "password", loginDO.getPassword(), isExactSearch);
			HibernateUtils.addRestrictionToCriteria(criteria, "personDO", loginDO.getPersonDO(), isExactSearch);
			HibernateUtils.addRestrictionToCriteria(criteria, "userName", loginDO.getUserName(), isExactSearch);
			HibernateUtils.addRestrictionToCriteria(criteria, "securityQuestionDO", loginDO.getSecurityQuestionDO(), isExactSearch);
			HibernateUtils.addRestrictionToCriteria(criteria, "securityAnswer", loginDO.getSecurityAnswer(), isExactSearch);
		}
		return criteria.list();
	}

	@Override
	public boolean update(LoginDO loginDO) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		boolean status = false;
		try {
			tx = session.beginTransaction();
			session.update(loginDO);
			tx.commit();
			status = true;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return status;
	}
}
