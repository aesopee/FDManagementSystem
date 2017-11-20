package com.slickcode.fdms.common.bean;

import com.slickcode.baseframework.domain.IPanelBean;
import com.slickcode.fdms.common.vo.LoginVO;

public class LoginPanelBean implements IPanelBean {
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
