package com.slickcode.fdms.utils;

import com.slickcode.fdms.common.vo.SecurityQuestionVO;
import com.slickcode.fdms.service.domain.SecurityQuestionDO;

public class SecurityQuestionConversionUtils {
	private SecurityQuestionConversionUtils() {

	}

	public static SecurityQuestionVO convertToVO(SecurityQuestionDO questionDO) {
		return (SecurityQuestionVO) StaticDataConversionUtils.convertToVO(questionDO);
	}

	public static SecurityQuestionDO convertToDO(SecurityQuestionVO questionVO) {
		return (SecurityQuestionDO) StaticDataConversionUtils.convertToDO(questionVO);
	}
}
