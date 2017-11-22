package com.slickcode.fdms.utils;

import com.slickcode.fdms.common.vo.StaticDataVO;
import com.slickcode.fdms.service.domain.StaticDataDO;

public class StaticDataConversionUtils {
	private StaticDataConversionUtils() {

	}

	public static StaticDataVO convertToVO(StaticDataDO staticDataDO) {
		if (null == staticDataDO) {
			return null;
		}
		StaticDataVO staticDataVO = new StaticDataVO();
		staticDataVO.setId(staticDataDO.getId());
		staticDataVO.setCode(staticDataDO.getCode());
		staticDataVO.setNarrative(staticDataDO.getNarrative());

		return staticDataVO;
	}

	public static StaticDataDO convertToDO(StaticDataVO staticDataVO) {
		if (null == staticDataVO) {
			return null;
		}
		StaticDataDO staticDataDO = new StaticDataDO();
		staticDataDO.setId(staticDataVO.getId());
		staticDataDO.setCode(staticDataVO.getCode());
		staticDataDO.setNarrative(staticDataVO.getNarrative());

		return staticDataDO;
	}
}
