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

import com.slickcode.fdms.service.domain.BankDO;
import com.slickcode.fdms.utils.HibernateUtils;
@Component("bankDaoImpl")
public class BankDaoImpl implements IBankDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public Integer create(BankDO bankDO) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer bankId = null;
		try {
			tx = session.beginTransaction();
			bankId = (Integer) session.save(bankDO);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bankId;
	}

	@Override
	public boolean update(BankDO bankDO) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		boolean status = false;
		try {
			tx = session.beginTransaction();
			session.update(bankDO);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<BankDO> fetchByCriteria(BankDO bankDO, boolean isExactSearch) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(BankDO.class);
		if(null != bankDO) {
			HibernateUtils.addRestrictionToCriteria(criteria, "bankId", bankDO.getBankId(), true);
			HibernateUtils.addRestrictionToCriteria(criteria, "name", bankDO.getName(), isExactSearch);
			HibernateUtils.addRestrictionToCriteria(criteria, "branch", bankDO.getBranch(), isExactSearch);
		}
		return criteria.list();
	}

}
