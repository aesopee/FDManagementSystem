package com.slickcode.fdms.utils;

import com.slickcode.fdms.common.vo.StatusVO;
import com.slickcode.fdms.service.domain.StatusDO;

public class StatusConversionUtils {
	
	public static StatusVO convertToVO(StatusDO statusDO) {
		if(null == statusDO) {
			return null;
		}
		StatusVO statusVO = new StatusVO();
		statusVO.setId(statusDO.getId());
		statusVO.setCode(statusDO.getCode());
		statusVO.setNarrative(statusDO.getNarrative());
		
		return statusVO;
	}
	
	public static StatusDO convertToDO(StatusVO statusVO) {
		if(null == statusVO) {
			return null;
		}
		StatusDO statusDO = new StatusDO();
		statusDO.setId(statusVO.getId());
		statusDO.setCode(statusVO.getCode());
		statusDO.setNarrative(statusVO.getNarrative());
		
		return statusDO;
	}
}
