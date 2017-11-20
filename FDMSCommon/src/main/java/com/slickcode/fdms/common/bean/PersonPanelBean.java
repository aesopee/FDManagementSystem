package com.slickcode.fdms.common.bean;

import com.slickcode.baseframework.domain.IPanelBean;
import com.slickcode.fdms.common.vo.PersonVO;

public class PersonPanelBean implements IPanelBean {
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
