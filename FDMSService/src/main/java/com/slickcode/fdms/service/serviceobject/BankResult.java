package com.slickcode.fdms.service.serviceobject;

import com.slickcode.fdms.common.vo.BankVO;

public class BankResult extends BaseDomain {
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
