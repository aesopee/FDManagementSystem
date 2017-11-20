package com.slickcode.fdms.utils;

import com.slickcode.fdms.common.vo.SecurityQuestionVO;
import com.slickcode.fdms.service.domain.SecurityQuestionDO;

public class SecurityQuestionConversionUtils {
	
	public static SecurityQuestionVO convertToVO(SecurityQuestionDO questionDO) {
		if(null == questionDO) {
			return null;
		}
		SecurityQuestionVO questionVO = new SecurityQuestionVO();
		questionVO.setId(questionDO.getId());
		questionVO.setCode(questionDO.getCode());
		questionVO.setNarrative(questionDO.getNarrative());
		
		return questionVO;
	}
	
	public static SecurityQuestionDO convertToDO(SecurityQuestionVO questionVO) {
		if(null == questionVO) {
			return null;
		}
		SecurityQuestionDO questionDO = new SecurityQuestionDO();
		questionDO.setId(questionVO.getId());
		questionDO.setCode(questionVO.getCode());
		questionDO.setNarrative(questionVO.getNarrative());
		
		return questionDO;
	}
}
