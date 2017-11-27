package com.slickcode.fdms.utils;

import com.slickcode.fdms.common.vo.StatusVO;
import com.slickcode.fdms.service.domain.StatusDO;

public class StatusConversionUtils {
	private StatusConversionUtils() {

	}

	public static StatusVO convertToVO(StatusDO statusDO) {
		return (StatusVO) StaticDataConversionUtils.convertToVO(statusDO);
	}

	public static StatusDO convertToDO(StatusVO statusVO) {
		return (StatusDO) StaticDataConversionUtils.convertToDO(statusVO);
	}
}
