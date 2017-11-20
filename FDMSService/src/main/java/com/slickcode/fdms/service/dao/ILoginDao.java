package com.slickcode.fdms.service.dao;

import java.util.List;

import com.slickcode.fdms.service.domain.LoginDO;

public interface ILoginDao {
	Integer create(LoginDO loginDO);

	List<LoginDO> fetchByCriteria(LoginDO loginDO, boolean isExactSearch);
	
	boolean update(LoginDO loginDO);
}
