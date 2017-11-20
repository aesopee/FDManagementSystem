package com.slickcode.fdms.service;

import java.util.List;

import com.slickcode.fdms.common.vo.StatusVO;

public interface IStatusService {
	public List<StatusVO> fetchAll();

	public StatusVO fetchByCode(String code);
}
