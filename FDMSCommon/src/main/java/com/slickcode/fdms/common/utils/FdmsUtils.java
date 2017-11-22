package com.slickcode.fdms.common.utils;

import com.slickcode.fdms.common.vo.PersonVO;

public class FdmsUtils {

	private FdmsUtils() {

	}

	public static String getPersonName(PersonVO person) {
		return person.getFirstName() + " " + person.getLastName();
	}
}
