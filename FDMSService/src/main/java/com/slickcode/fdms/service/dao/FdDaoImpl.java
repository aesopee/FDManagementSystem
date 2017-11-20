package com.slickcode.fdms.service.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.slickcode.fdms.service.domain.BankDO;
import com.slickcode.fdms.service.domain.FdDO;
import com.slickcode.fdms.service.domain.StatusDO;
import com.slickcode.fdms.utils.HibernateUtils;

@Component("fdDaoImpl")
public class FdDaoImpl implements IFdDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public Integer create(FdDO fddo) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer fdId = null;
		try {
			tx = session.beginTransaction();
			fdId = (Integer) session.save(fddo);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return fdId;
	}

	@Override
	public boolean update(FdDO fddo) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		boolean status = false;
		try {
			tx = session.beginTransaction();
			session.update(fddo);
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
	public List<FdDO> fetchByCriteria(FdDO fddo, boolean isExactSearch) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(FdDO.class);
		if (null != fddo) {
			HibernateUtils.addRestrictionToCriteria(criteria, "fdId",
					fddo.getFdId(), true);
			HibernateUtils.addRestrictionToCriteria(criteria, "fdNumber",
					fddo.getFdNumber(), isExactSearch);
			HibernateUtils.addRestrictionToCriteria(criteria, "renewedFrom",
					fddo.getRenewedFrom(), true);
			HibernateUtils.addRestrictionToCriteria(criteria, "renewedTo",
					fddo.getRenewedTo(), true);
			HibernateUtils.addRestrictionToCriteria(criteria,
					"fdBankReferenceNumber", fddo.getFdBankReferenceNumber(),
					true);
			Criterion primaryPersonDOCriterion = null;
			if (null != fddo.getPrimaryPersonDO()) {
				primaryPersonDOCriterion = Restrictions.eq("primaryPersonDO",
						fddo.getPrimaryPersonDO());
			}

			Criterion secondaryPersonDOCriterion = null;
			if (null != fddo.getSecondaryPersonDO()) {
				secondaryPersonDOCriterion = Restrictions.eq(
						"secondaryPersonDO", fddo.getSecondaryPersonDO());
			}

			Criterion nomineeDOCriterion = null;
			if (null != fddo.getNomineeDO()) {
				nomineeDOCriterion = Restrictions.eq("nomineeDO",
						fddo.getNomineeDO());
			}

			if (!((null == primaryPersonDOCriterion)
					|| (null == secondaryPersonDOCriterion) || (null == nomineeDOCriterion))) {
				criteria.add(Restrictions.or(Restrictions.or(
						primaryPersonDOCriterion, secondaryPersonDOCriterion),
						nomineeDOCriterion));
			}

			HibernateUtils.addRestrictionToCriteria(criteria, "bankDO",
					fddo.getBankDO(), true);
			HibernateUtils.addRestrictionToCriteria(criteria, "statusDO",
					fddo.getStatusDO(), true);
			HibernateUtils.addRestrictionToCriteria(criteria,
					"originalFdNumber", fddo.getOriginalFdNumber(),
					isExactSearch);
		}
		return criteria.list();
	}

	@Override
	public Integer fetchMaxBankFdId(BankDO bankDO) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(FdDO.class)
				.setProjection(Projections.max("fdBankReferenceNumber"))
				.add(Restrictions.eq("bankDO", bankDO));
		return (Integer) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FdDO> fetchByMaturityDateRangeAndStatus(Date startDate,
			Date endDate, StatusDO statusDO) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(FdDO.class);
		if (null != statusDO) {
			HibernateUtils.addRestrictionToCriteria(criteria, "statusDO",
					statusDO, true);
		}
		if (null != startDate) {
			criteria.add(Restrictions.ge("maturityDate", startDate));
		}
		if (null != endDate) {
			criteria.add(Restrictions.le("maturityDate", endDate));
		}
		return criteria.list();
	}
}
