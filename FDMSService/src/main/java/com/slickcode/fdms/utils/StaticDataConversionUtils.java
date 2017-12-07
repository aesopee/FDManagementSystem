package com.slickcode.fdms.utils;

import com.slickcode.fdms.common.vo.StaticDataVO;
import com.slickcode.fdms.service.domain.StaticDataDO;

public class StaticDataConversionUtils {
	private StaticDataConversionUtils() {

	}

	public static StaticDataVO convertToVO(StaticDataDO staticDataDO, StaticDataVO staticDataVO) {
		if (null == staticDataDO) {
			return null;
		}
		if (null == staticDataVO) {
			return null;
		}
		staticDataVO.setId(staticDataDO.getId());
		staticDataVO.setCode(staticDataDO.getCode());
		staticDataVO.setNarrative(staticDataDO.getNarrative());

		return staticDataVO;
	}

	public static StaticDataDO convertToDO(StaticDataVO staticDataVO, StaticDataDO staticDataDO) {
		if (null == staticDataVO) {
			return null;
		}
		if (null == staticDataDO) {
			return null;
		}
		staticDataDO.setId(staticDataVO.getId());
		staticDataDO.setCode(staticDataVO.getCode());
		staticDataDO.setNarrative(staticDataVO.getNarrative());

		return staticDataDO;
	}
}
