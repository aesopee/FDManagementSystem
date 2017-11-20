package com.slickcode.fdms.service.serviceobject;

import com.slickcode.fdms.common.vo.PersonVO;

public class PersonResult extends BaseDomain {
	private PersonVO personVO;

	/**
	 * @return the personVO
	 */
	public PersonVO getPersonVO() {
		return personVO;
	}

	/**
	 * @param personVO the personVO to set
	 */
	public void setPersonVO(PersonVO personVO) {
		this.personVO = personVO;
	}
}
