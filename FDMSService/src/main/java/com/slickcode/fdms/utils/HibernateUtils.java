package com.slickcode.fdms.utils;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class HibernateUtils {
	private HibernateUtils() {

	}

	public static void addRestrictionToCriteria(Criteria criteria, String domainProperty, Object value,
			boolean isExactSearch) {
		if (null != value) {
			if (isExactSearch) {
				criteria.add(Restrictions.eq(domainProperty, value));
			} else {
				if (value instanceof String) {
					criteria.add(Restrictions.like(domainProperty, "%" + value + "%"));
				} else {
					criteria.add(Restrictions.sqlRestriction(" " + domainProperty + " LIKE '%" + value + "%' "));
				}
			}
		}
	}
}
