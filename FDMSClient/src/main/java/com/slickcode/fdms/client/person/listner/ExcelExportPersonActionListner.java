package com.slickcode.fdms.client.person.listner;

import java.util.ArrayList;
import java.util.List;

import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.slickcode.fdms.client.constants.CommonConstants;
import com.slickcode.fdms.client.listner.ExcelExportActionListner;
import com.slickcode.fdms.common.vo.PersonVO;

public class ExcelExportPersonActionListner extends ExcelExportActionListner {

	private List<PersonVO> personVOList;

	public ExcelExportPersonActionListner(List<PersonVO> personVOList) {
		this.personVOList = personVOList;
	}

	public void createContent(WritableSheet sheet) throws WriteException,
			RowsExceededException {
		int row = 0;
		int column = 0;
		for (PersonVO personVO : personVOList) {
			row++;
			column = 0;
			addLabel(sheet, column++, row, personVO.getPersonId().toString());
			addLabel(sheet, column++, row, personVO.getFirstName());
			addLabel(sheet, column++, row, personVO.getMiddleName());
			addLabel(sheet, column++, row, personVO.getLastName());
		}
	}

	public List<String> populateHeaderList() {
		List<String> headerList = new ArrayList<String>();
		headerList.add(CommonConstants.LABEL_PERSON_ID);
		headerList.add(CommonConstants.LABEL_FIRST_NAME);
		headerList.add(CommonConstants.LABEL_MIDDLE_NAME);
		headerList.add(CommonConstants.LABEL_LAST_NAME);
		return headerList;
	}

}
