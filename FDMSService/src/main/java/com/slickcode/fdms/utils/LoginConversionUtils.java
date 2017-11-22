package com.slickcode.fdms.utils;

import com.slickcode.fdms.common.vo.LoginVO;
import com.slickcode.fdms.service.domain.LoginDO;

public class LoginConversionUtils {
	private LoginConversionUtils() {

	}

	public static LoginVO convertToVO(LoginDO loginDO) {
		LoginVO loginVO = new LoginVO();
		loginVO.setLoginId(loginDO.getLoginId());
		loginVO.setPassword(loginDO.getPassword());
		loginVO.setPersonVO(PersonConversionUtils.convertToVO(loginDO.getPersonDO()));
		loginVO.setSecurityAnswer(loginDO.getSecurityAnswer());
		loginVO.setSecurityQuestion(SecurityQuestionConversionUtils.convertToVO(loginDO.getSecurityQuestionDO()));
		loginVO.setUserName(loginDO.getUserName());

		return loginVO;
	}

	public static LoginDO convertToDO(LoginVO loginVO) {
		LoginDO loginDO = new LoginDO();
		loginDO.setLoginId(loginVO.getLoginId());
		loginDO.setPassword(loginVO.getPassword());
		loginDO.setPersonDO(PersonConversionUtils.convertToDO(loginVO.getPersonVO()));
		loginDO.setSecurityAnswer(loginVO.getSecurityAnswer());
		loginDO.setSecurityQuestionDO(SecurityQuestionConversionUtils.convertToDO(loginVO.getSecurityQuestion()));
		loginDO.setUserName(loginVO.getUserName());

		return loginDO;
	}

}
