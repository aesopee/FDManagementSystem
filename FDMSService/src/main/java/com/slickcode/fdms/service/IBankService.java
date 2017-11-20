package com.slickcode.fdms.service;

import com.slickcode.fdms.common.vo.BankVO;
import com.slickcode.fdms.service.serviceobject.BankListResult;
import com.slickcode.fdms.service.serviceobject.BankResult;

public interface IBankService {
	public BankResult create(BankVO bankVO);

	public BankResult update(BankVO bankVO);
	
	public BankResult fetchById(BankVO bankVO);

	public BankListResult fetchByCriteria(BankVO bankVO);
	
	public BankListResult searchByCriteria(BankVO bankVO);
}
