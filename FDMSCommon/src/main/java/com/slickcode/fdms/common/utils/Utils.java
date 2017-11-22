package com.slickcode.fdms.common.utils;

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

	@SuppressWarnings({ "deprecation" })
	public static String convertDateToString(java.sql.Date sqlDate) {

		Integer date = sqlDate.getDate();
		Integer month = sqlDate.getMonth() + 1;
		Integer year = sqlDate.getYear() + 1900;

		String stringDate = date.toString();
		String stringMonth = month.toString();
		String stringYear = year.toString();
		int size = stringDate.length();

		if (size == 1) {
			stringDate = "0" + stringDate;
		}
		size = stringMonth.length();
		if (size == 1) {
			stringMonth = "0" + stringMonth;
		}
		size = stringYear.length();
		if (size == 1) {
			stringYear = "000" + stringYear;
		} else if (size == 2) {
			stringYear = "00" + stringYear;
		} else if (size == 3) {
			stringYear = "0" + stringYear;
		}
		return stringDate + "-" + stringMonth + "-" + stringYear;
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

	@SuppressWarnings("deprecation")
	public static java.sql.Date getWeekLaterDate() {

		java.util.Date today = new java.util.Date();
		java.sql.Date sqlToday = new java.sql.Date(today.getTime());
		sqlToday.setDate(sqlToday.getDate() + 7);

		return sqlToday;
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
