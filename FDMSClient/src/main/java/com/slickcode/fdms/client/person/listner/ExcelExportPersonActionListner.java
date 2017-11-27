package com.slickcode.fdms.client.person.listner;

import java.util.ArrayList;
import java.util.List;

import com.slickcode.fdms.client.constants.LabelConstants;
import com.slickcode.fdms.client.listner.ExcelExportActionListner;
import com.slickcode.fdms.common.vo.PersonVO;

import jxl.write.WritableSheet;
import jxl.write.WriteException;

public class ExcelExportPersonActionListner extends ExcelExportActionListner {

	private List<PersonVO> personVOList;

	public ExcelExportPersonActionListner(List<PersonVO> personVOList) {
		this.personVOList = personVOList;
	}

	public void createContent(WritableSheet sheet) throws WriteException {
		int row = 0;
		int column = 0;
		for (PersonVO personVO : personVOList) {
			row++;
			column = 0;
			addLabel(sheet, column++, row, personVO.getPersonId().toString());
			addLabel(sheet, column++, row, personVO.getFirstName());
			addLabel(sheet, column++, row, personVO.getMiddleName());
			addLabel(sheet, column, row, personVO.getLastName());
		}
	}

	public List<String> populateHeaderList() {
		List<String> headerList = new ArrayList<>();
		headerList.add(LabelConstants.PERSON_ID);
		headerList.add(LabelConstants.FIRST_NAME);
		headerList.add(LabelConstants.MIDDLE_NAME);
		headerList.add(LabelConstants.LAST_NAME);
		return headerList;
	}

}
