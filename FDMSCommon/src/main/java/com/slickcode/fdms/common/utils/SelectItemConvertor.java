package com.slickcode.fdms.common.utils;

import java.util.ArrayList;
import java.util.List;

import com.slickcode.baseframework.domain.SelectItem;
import com.slickcode.fdms.common.vo.BankVO;
import com.slickcode.fdms.common.vo.PersonVO;
import com.slickcode.fdms.common.vo.SecurityQuestionVO;
import com.slickcode.fdms.common.vo.StatusVO;

public class SelectItemConvertor {

	private SelectItemConvertor() {

	}

	public static final SelectItem SELECT_ITEM_DEFAULT = new SelectItem("DEFAULT", "Please Select...");

	public static List<SelectItem> populateBankSelectItemList(List<BankVO> bankVOList) {
		List<SelectItem> selectItemList = new ArrayList<>();
		selectItemList.add(SELECT_ITEM_DEFAULT);
		if (null != bankVOList) {
			for (BankVO bankVO : bankVOList) {
				selectItemList.add(populateBankSelectItem(bankVO));
			}
		}
		return selectItemList;
	}

	public static SelectItem populateBankSelectItem(BankVO bankVO) {
		if (null == bankVO) {
			return null;
		}
		return new SelectItem(bankVO.getBankId(), bankVO.getName() + ", " + bankVO.getBranch());
	}

	public static List<SelectItem> populateSecurityQuestionSelectItemList(List<SecurityQuestionVO> voList) {
		List<SelectItem> selectItemList = new ArrayList<>();
		selectItemList.add(SELECT_ITEM_DEFAULT);
		if (null != voList) {
			for (SecurityQuestionVO vo : voList) {
				selectItemList.add(populateSecurityQuestionSelectItem(vo));
			}
		}
		return selectItemList;
	}

	public static SelectItem populateStatusSelectItem(StatusVO vo) {
		if (null == vo) {
			return null;
		}
		return new SelectItem(vo.getCode(), vo.getNarrative());
	}

	public static List<SelectItem> populateStatusSelectItemList(List<StatusVO> voList) {
		List<SelectItem> selectItemList = new ArrayList<>();
		selectItemList.add(SELECT_ITEM_DEFAULT);
		if (null != voList) {
			for (StatusVO vo : voList) {
				selectItemList.add(populateStatusSelectItem(vo));
			}
		}
		return selectItemList;
	}

	public static SelectItem populateSecurityQuestionSelectItem(SecurityQuestionVO vo) {
		if (null == vo) {
			return null;
		}
		return new SelectItem(vo.getCode(), vo.getNarrative());
	}

	public static List<SelectItem> populatePersonSelectItemList(List<PersonVO> personVOList) {
		List<SelectItem> selectItemList = new ArrayList<>();
		selectItemList.add(SELECT_ITEM_DEFAULT);
		if (null != personVOList) {
			for (PersonVO personVO : personVOList) {
				selectItemList.add(populatePersonSelectItem(personVO));
			}
		}
		return selectItemList;
	}

	public static SelectItem populatePersonSelectItem(PersonVO personVO) {
		if (null == personVO) {
			return null;
		}
		return new SelectItem(personVO.getPersonId(), FdmsUtils.getPersonName(personVO));
	}
}
