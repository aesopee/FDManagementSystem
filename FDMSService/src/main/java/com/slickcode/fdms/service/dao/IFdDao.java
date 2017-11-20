package com.slickcode.fdms.service.dao;

import java.util.Date;
import java.util.List;

import com.slickcode.fdms.service.domain.BankDO;
import com.slickcode.fdms.service.domain.FdDO;
import com.slickcode.fdms.service.domain.StatusDO;

public interface IFdDao {

	public Integer create(FdDO fddo);

	public boolean update(FdDO fddo);

	public List<FdDO> fetchByCriteria(FdDO fddo, boolean isExactSearch);
	
	public Integer fetchMaxBankFdId(BankDO bankDO);
	
	public List<FdDO> fetchByMaturityDateRangeAndStatus(Date startDate, Date endDate, StatusDO statusDO);
}
