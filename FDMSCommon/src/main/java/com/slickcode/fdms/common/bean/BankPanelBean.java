package com.slickcode.fdms.common.bean;

import com.slickcode.baseframework.domain.IPanelBean;
import com.slickcode.fdms.common.vo.BankVO;

public class BankPanelBean implements IPanelBean {
	private BankVO bankVO;

	/**
	 * @return the bankVO
	 */
	public BankVO getBankVO() {
		return bankVO;
	}

	/**
	 * @param bankVO the bankVO to set
	 */
	public void setBankVO(BankVO bankVO) {
		this.bankVO = bankVO;
	}
	
	

}
