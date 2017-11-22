package com.slickcode.fdms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.slickcode.fdms.common.vo.SecurityQuestionVO;
import com.slickcode.fdms.service.dao.ISecurityQuestionDao;
import com.slickcode.fdms.service.domain.SecurityQuestionDO;
import com.slickcode.fdms.utils.SecurityQuestionConversionUtils;

@Component("securityQuestionServiceImpl")
public class SecurityQuestionServiceImpl implements ISecurityQuestionService {
	@Autowired
	@Qualifier("securityQuestionDaoImpl")
	private ISecurityQuestionDao dao;

	@Override
	public List<SecurityQuestionVO> fetchAll() {
		List<SecurityQuestionDO> doList = dao.fetchAll();
		if (null == doList) {
			return new ArrayList<>();
		}
		List<SecurityQuestionVO> voList = new ArrayList<>();
		for (SecurityQuestionDO questionDO : doList) {
			voList.add(SecurityQuestionConversionUtils.convertToVO(questionDO));
		}
		return voList;
	}

}
