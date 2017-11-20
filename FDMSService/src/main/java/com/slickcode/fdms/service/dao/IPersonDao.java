package com.slickcode.fdms.service.dao;

import java.util.List;

import com.slickcode.fdms.service.domain.PersonDO;

public interface IPersonDao {

	public Integer create(PersonDO personDO);

	public boolean update(PersonDO personDO);

	List<PersonDO> fetchByCriteria(PersonDO personDO, boolean isExactSearch);

}
