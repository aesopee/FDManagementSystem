package com.slickcode.fdms.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.slickcode.fdms.common.vo.BankVO;
import com.slickcode.fdms.common.vo.PersonVO;

public class Utils {

	private Utils() {

	}

	public static java.sql.Date dateConverter(String date) {
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			java.util.Date utilDate = simpleDateformat.parse(date);
			return new java.sql.Date(utilDate.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String convertDateToString(java.sql.Date sqlDate) {
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		return df.format(sqlDate);
	}

	public static String getPersonName(PersonVO person) {
		return person.getFirstName() + " " + person.getLastName();
	}

	public static String getBankName(BankVO bank) {
		return bank.getName() + ", " + bank.getBranch();
	}

	public static java.sql.Date getTodaysDate() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}

	public static List<PersonVO> removePleaseSelectFromList(List<PersonVO> personList) {
		for (PersonVO person : personList) {
			if (person.getPersonId() == 1) {
				personList.remove(person);
				break;
			}
		}
		return personList;
	}

}
