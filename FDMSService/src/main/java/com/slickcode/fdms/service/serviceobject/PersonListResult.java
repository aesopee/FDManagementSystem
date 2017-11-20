package com.slickcode.fdms.service.serviceobject;

import java.util.List;

import com.slickcode.fdms.common.vo.PersonVO;

public class PersonListResult extends BaseDomain {
	private List<PersonVO> personVOList;

	/**
	 * @return the personVOList
	 */
	public List<PersonVO> getPersonVOList() {
		return personVOList;
	}

	/**
	 * @param personVOList the personVOList to set
	 */
	public void setPersonVOList(List<PersonVO> personVOList) {
		this.personVOList = personVOList;
	}
	
	
}
