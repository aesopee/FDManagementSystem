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

import com.slickcode.fdms.service.domain.StatusDO;
import com.slickcode.fdms.utils.HibernateUtils;

@Component("statusDaoImpl")
public class StatusDaoImpl implements IStatusDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<StatusDO> fetchAll() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<StatusDO> list = null;
		try {
			tx = session.beginTransaction();
			list = session.createQuery("FROM StatusDO").list();
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

	@SuppressWarnings("unchecked")
	@Override
	public StatusDO fetchByCode(String code) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(StatusDO.class);
		if (null != code) {
			HibernateUtils.addRestrictionToCriteria(criteria, "code", code,
					true);
			List<StatusDO> statusDOList = criteria.list();
			if ((null == statusDOList) || (statusDOList.isEmpty())) {
				return null;
			} else {
				return statusDOList.get(0);
			}
		} else {
			return null;
		}

	}

}
