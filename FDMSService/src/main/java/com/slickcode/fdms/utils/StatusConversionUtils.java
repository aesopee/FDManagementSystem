package com.slickcode.fdms.utils;

import com.slickcode.fdms.common.vo.StatusVO;
import com.slickcode.fdms.service.domain.StatusDO;

public class StatusConversionUtils {
	private StatusConversionUtils() {
	}

	public static StatusVO convertToVO(StatusDO statusDO) {
		StatusVO statusVO = new StatusVO();
		return (StatusVO) StaticDataConversionUtils.convertToVO(statusDO, statusVO);
	}

	public static StatusDO convertToDO(StatusVO statusVO) {
		StatusDO statusDO = new StatusDO();
		return (StatusDO) StaticDataConversionUtils.convertToDO(statusVO, statusDO);
	}
}
