package com.slickcode.fdms.service;

import com.slickcode.fdms.common.vo.PersonVO;
import com.slickcode.fdms.service.serviceobject.PersonListResult;
import com.slickcode.fdms.service.serviceobject.PersonResult;

public interface IPersonService {
	public PersonResult create(PersonVO personVO);

	public PersonResult update(PersonVO personVO);
	
	public PersonResult fetchById(PersonVO personVO);

	public PersonListResult fetchByCriteria(PersonVO personVO);
	
	public PersonListResult searchByCriteria(PersonVO personVO);
}
