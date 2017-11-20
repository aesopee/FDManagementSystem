package com.slickcode.fdms.service.dao;

import java.util.List;

import com.slickcode.fdms.service.domain.SecurityQuestionDO;

public interface ISecurityQuestionDao {
	public List<SecurityQuestionDO> fetchAll();
}
