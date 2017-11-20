package com.slickcode.fdms.service;

import com.slickcode.fdms.common.vo.FdVO;
import com.slickcode.fdms.service.serviceobject.FdListResult;
import com.slickcode.fdms.service.serviceobject.FdResult;

public interface IFdService {
	public FdResult create(FdVO fdVO);

	public FdResult update(FdVO fdVO);
	
	public FdResult renew(FdVO fdVO);

	public FdResult withdraw(FdVO fdVO);
	
	public FdResult fetchById(FdVO fdVO);

	public FdListResult fetchByCriteria(FdVO fdVO);

	public FdListResult searchByCriteria(FdVO fdVO);
	
	public FdListResult track(FdVO fdVO);
	
	public FdListResult fetchMaturedFds();
	
	public FdListResult fetchFdsMaturingInNextWeek();
}
