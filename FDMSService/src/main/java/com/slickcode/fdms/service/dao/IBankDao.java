package com.slickcode.fdms.service.dao;

import java.util.List;

import com.slickcode.fdms.service.domain.BankDO;

public interface IBankDao {

	public Integer create(BankDO bankDO);

	public boolean update(BankDO bankDO);

	List<BankDO> fetchByCriteria(BankDO bankDO, boolean isExactSearch);

}
