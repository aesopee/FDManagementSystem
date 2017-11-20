package com.slickcode.fdms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.slickcode.fdms.common.vo.StatusVO;
import com.slickcode.fdms.service.dao.IStatusDao;
import com.slickcode.fdms.service.domain.StatusDO;
import com.slickcode.fdms.utils.StatusConversionUtils;

@Component("statusServiceImpl")
public class StatusServiceImpl implements IStatusService {
	@Autowired
	@Qualifier("statusDaoImpl")
	private IStatusDao dao;

	@Override
	public List<StatusVO> fetchAll() {
		List<StatusDO> doList = dao.fetchAll();
		if (null == doList) {
			return null;
		}
		List<StatusVO> voList = new ArrayList<StatusVO>();
		for (StatusDO statusDO : doList) {
			voList.add(StatusConversionUtils.convertToVO(statusDO));
		}
		return voList;
	}

	@Override
	public StatusVO fetchByCode(String code) {
		StatusDO statusDO = dao.fetchByCode(code);
		if (null == statusDO) {
			return null;
		}
		return StatusConversionUtils.convertToVO(statusDO);
	}

}
