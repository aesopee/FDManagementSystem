package com.slickcode.fdms.utils;

import java.util.Calendar;

import com.slickcode.fdms.common.vo.BaseVO;
import com.slickcode.fdms.service.domain.BaseDO;

public class BaseConversionUtils {

	public static BaseVO convertToVO(BaseDO baseDO, BaseVO baseVO) {
		if (null == baseDO) {
			return null;
		}
		if (null == baseVO) {
			baseVO = new BaseVO();
		}
		baseVO.setCreationDate(baseDO.getCreationDate());
		baseVO.setModificationDate(baseDO.getModificationDate());
		baseVO.setVersion(baseDO.getVersion());

		return baseVO;
	}

	public static BaseDO convertToDO(BaseVO baseVO, BaseDO baseDO) {
		if (null == baseVO) {
			return null;
		}
		if (null == baseDO) {
			baseDO = new BaseDO();
		}
		baseDO.setCreationDate(baseVO.getCreationDate());
		baseDO.setModificationDate(Calendar.getInstance().getTime());
		baseDO.setVersion(baseVO.getVersion());

		return baseDO;
	}

}
