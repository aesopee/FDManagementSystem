package com.slickcode.fdms.utils;

import com.slickcode.fdms.common.vo.PersonVO;
import com.slickcode.fdms.service.domain.PersonDO;

public class PersonConversionUtils {
	private PersonConversionUtils() {

	}

	public static PersonVO convertToVO(PersonDO personDO) {
		if (null == personDO) {
			return null;
		}
		PersonVO personVO = new PersonVO();
		personVO.setPersonId(personDO.getPersonId());
		personVO.setFirstName(personDO.getFirstName());
		personVO.setMiddleName(personDO.getMiddleName());
		personVO.setLastName(personDO.getLastName());

		return personVO;
	}

	public static PersonDO convertToDO(PersonVO personVO) {
		if (null == personVO) {
			return null;
		}
		PersonDO personDO = new PersonDO();
		personDO.setPersonId(personVO.getPersonId());
		personDO.setFirstName(personVO.getFirstName());
		personDO.setMiddleName(personVO.getMiddleName());
		personDO.setLastName(personVO.getLastName());

		return personDO;
	}

}
