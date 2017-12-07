package com.slickcode.fdms.utils;

import com.slickcode.fdms.common.vo.SecurityQuestionVO;
import com.slickcode.fdms.service.domain.SecurityQuestionDO;

public class SecurityQuestionConversionUtils {
	private SecurityQuestionConversionUtils() {

	}

	public static SecurityQuestionVO convertToVO(SecurityQuestionDO securityQuestionDO) {
		SecurityQuestionVO securityQuestionVO = new SecurityQuestionVO();
		return (SecurityQuestionVO) StaticDataConversionUtils.convertToVO(securityQuestionDO, securityQuestionVO);
	}

	public static SecurityQuestionDO convertToDO(SecurityQuestionVO securityQuestionVO) {
		SecurityQuestionDO securityQuestionDO = new SecurityQuestionDO();
		return (SecurityQuestionDO) StaticDataConversionUtils.convertToDO(securityQuestionVO, securityQuestionDO);
	}
}
