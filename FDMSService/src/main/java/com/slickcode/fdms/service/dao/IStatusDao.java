package com.slickcode.fdms.service.dao;

import java.util.List;

import com.slickcode.fdms.service.domain.StatusDO;

public interface IStatusDao {
	public List<StatusDO> fetchAll();

	public StatusDO fetchByCode(String code);
}
