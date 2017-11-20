package com.slickcode.fdms.service.serviceobject;

import java.util.List;

import com.slickcode.fdms.common.vo.BankVO;

public class BankListResult extends BaseDomain {
	private List<BankVO> bankVOList;

	/**
	 * @return the bankVOList
	 */
	public List<BankVO> getBankVOList() {
		return bankVOList;
	}

	/**
	 * @param bankVOList the bankVOList to set
	 */
	public void setBankVOList(List<BankVO> bankVOList) {
		this.bankVOList = bankVOList;
	}
	
	
}
