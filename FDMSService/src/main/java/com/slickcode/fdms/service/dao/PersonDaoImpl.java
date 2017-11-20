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

import com.slickcode.fdms.service.domain.PersonDO;
import com.slickcode.fdms.utils.HibernateUtils;

@Component("personDaoImpl")
public class PersonDaoImpl implements IPersonDao {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public Integer create(PersonDO personDO) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer personId = null;
		try {
			tx = session.beginTransaction();
			personId = (Integer) session.save(personDO);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return personId;
	}

	@Override
	public boolean update(PersonDO personDO) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		boolean status = false;
		try {
			tx = session.beginTransaction();
			session.update(personDO);
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
	public List<PersonDO> fetchByCriteria(PersonDO personDO, boolean isExactSearch){
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(PersonDO.class);
		if(null != personDO) {
			HibernateUtils.addRestrictionToCriteria(criteria, "personId", personDO.getPersonId(), isExactSearch);
			HibernateUtils.addRestrictionToCriteria(criteria, "firstName", personDO.getFirstName(), isExactSearch);
			HibernateUtils.addRestrictionToCriteria(criteria, "middleName", personDO.getMiddleName(), isExactSearch);
			HibernateUtils.addRestrictionToCriteria(criteria, "lastName", personDO.getLastName(), isExactSearch);
		}
		return criteria.list();
	}
}
