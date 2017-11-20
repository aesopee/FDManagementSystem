package com.slickcode.fdms.service.serviceobject;

import com.slickcode.fdms.common.vo.LoginVO;

public class LoginResult extends BaseDomain {
	private LoginVO loginVO;

	/**
	 * @return the loginVO
	 */
	public LoginVO getLoginVO() {
		return loginVO;
	}

	/**
	 * @param loginVO the loginVO to set
	 */
	public void setLoginVO(LoginVO loginVO) {
		this.loginVO = loginVO;
	}
	
	
}
