package com.slickcode.fdms.service;

import java.util.List;

import com.slickcode.fdms.common.vo.SecurityQuestionVO;

public interface ISecurityQuestionService {
	public List<SecurityQuestionVO> fetchAll();
}
