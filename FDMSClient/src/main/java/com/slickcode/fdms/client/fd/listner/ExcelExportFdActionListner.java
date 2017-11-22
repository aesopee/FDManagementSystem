package com.slickcode.fdms.client.fd.listner;

import java.util.ArrayList;
import java.util.List;

import com.slickcode.fdms.client.constants.CommonConstants;
import com.slickcode.fdms.client.listner.ExcelExportActionListner;
import com.slickcode.fdms.common.vo.FdVO;

import jxl.write.WritableSheet;
import jxl.write.WriteException;

public class ExcelExportFdActionListner extends ExcelExportActionListner {

	private List<FdVO> fDVOList;

	public ExcelExportFdActionListner(List<FdVO> fDVOList) {
		this.fDVOList = fDVOList;
	}

	public void createContent(WritableSheet sheet) throws WriteException {
		int row = 0;
		int column = 0;
		for (FdVO fdVO : fDVOList) {
			row++;
			column = 0;
			addLabel(sheet, column++, row, fdVO.getFdId().toString());
			addLabel(sheet, column++, row, fdVO.getFdNumber());
			addLabel(sheet, column++, row, fdVO.getBankVO().getName());
			addLabel(sheet, column++, row, fdVO.getBankVO().getBranch());
			addLabel(sheet, column++, row, fdVO.getMaturityDate().toString());
			addLabel(sheet, column, row, fdVO.getStatusVO().getNarrative());
			
		}
	}

	public List<String> populateHeaderList() {
		List<String> headerList = new ArrayList<>();
		headerList.add(CommonConstants.LABEL_FD_ID);
		headerList.add(CommonConstants.LABEL_FD_NUMBER);
		headerList.add(CommonConstants.LABEL_BANK_NAME);
		headerList.add(CommonConstants.LABEL_BRANCH);
		headerList.add(CommonConstants.LABEL_MATURITY_DATE);
		headerList.add(CommonConstants.LABEL_STATUS);
		return headerList;
	}

}
