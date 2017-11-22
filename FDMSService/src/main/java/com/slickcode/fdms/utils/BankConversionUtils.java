package com.slickcode.fdms.utils;

import com.slickcode.fdms.common.vo.BankVO;
import com.slickcode.fdms.service.domain.BankDO;

public class BankConversionUtils {
	private BankConversionUtils() {

	}

	public static BankVO convertToVO(BankDO bankDO) {
		if (null == bankDO) {
			return null;
		}
		BankVO bankVO = new BankVO();
		bankVO.setBankId(bankDO.getBankId());
		bankVO.setName(bankDO.getName());
		bankVO.setBranch(bankDO.getBranch());

		return bankVO;
	}

	public static BankDO convertToDO(BankVO bankVO) {
		if (null == bankVO) {
			return null;
		}
		BankDO bankDO = new BankDO();
		bankDO.setBankId(bankVO.getBankId());
		bankDO.setName(bankVO.getName());
		bankDO.setBranch(bankVO.getBranch());

		return bankDO;
	}

}
