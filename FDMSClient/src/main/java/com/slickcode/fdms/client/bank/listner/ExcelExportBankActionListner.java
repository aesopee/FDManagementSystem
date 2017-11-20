package com.slickcode.fdms.client.bank.listner;

import java.util.ArrayList;
import java.util.List;

import jxl.write.WritableSheet;
import jxl.write.WriteException;

import com.slickcode.fdms.client.constants.CommonConstants;
import com.slickcode.fdms.client.listner.ExcelExportActionListner;
import com.slickcode.fdms.common.vo.BankVO;

public class ExcelExportBankActionListner extends ExcelExportActionListner {

	private List<BankVO> bankVOList;

	public ExcelExportBankActionListner(List<BankVO> bankVOList) {
		this.bankVOList = bankVOList;
	}

	public void createContent(WritableSheet sheet) throws WriteException {
		int row = 0;
		int column = 0;
		for (BankVO bankVO : bankVOList) {
			row++;
			column = 0;
			addLabel(sheet, column++, row, bankVO.getBankId().toString());
			addLabel(sheet, column++, row, bankVO.getName());
			addLabel(sheet, column, row, bankVO.getBranch());
		}
	}

	public List<String> populateHeaderList() {
		List<String> headerList = new ArrayList<>();
		headerList.add(CommonConstants.LABEL_BANK_ID);
		headerList.add(CommonConstants.LABEL_BANK_NAME);
		headerList.add(CommonConstants.LABEL_BRANCH);
		return headerList;
	}

}
