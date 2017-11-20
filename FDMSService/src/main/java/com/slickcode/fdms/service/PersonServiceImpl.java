package com.slickcode.fdms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.slickcode.fdms.common.vo.PersonVO;
import com.slickcode.fdms.service.dao.IPersonDao;
import com.slickcode.fdms.service.domain.PersonDO;
import com.slickcode.fdms.service.serviceobject.PersonListResult;
import com.slickcode.fdms.service.serviceobject.PersonResult;
import com.slickcode.fdms.utils.PersonConversionUtils;

@Component("personServiceImpl")
public class PersonServiceImpl implements IPersonService {
	
	@Autowired
	@Qualifier("personDaoImpl")
	private IPersonDao dao;
	
	@Override
	public PersonResult create(PersonVO personVO) {
		PersonResult result = new PersonResult();
		List<String> errorList = null;
		boolean success = false;
		PersonDO personDO = PersonConversionUtils.convertToDO(personVO);

		List<PersonDO> personDOList = dao.fetchByCriteria(personDO, true);

		if ((null == personDOList) || (personDOList.size() != 1)) {
			Integer personId = dao.create(personDO);
			if ((null == personId) || (personId == 0)) {
				success = false;
				errorList = new ArrayList<String>();
				errorList
						.add("Cannot create Person. Please contact System Administrator.");
			} else {
				success = true;
				personVO.setPersonId(personId);
				result.setPersonVO(personVO);
			}
		} else {
			success = false;
			errorList = new ArrayList<String>();
			errorList.add("This person already exists");
		}
		result.setErrorList(errorList);
		result.setSuccess(success);
		return result;
	}

	@Override
	public PersonResult update(PersonVO personVO) {
		PersonResult result = new PersonResult();
		List<String> errorList = null;
		boolean success = false;
		PersonDO personDO = PersonConversionUtils.convertToDO(personVO);
		personDO.setPersonId(null);

		List<PersonDO> personDOList = dao.fetchByCriteria(personDO, true);

		if ((null == personDOList) || (personDOList.size() == 0)) {
			personDO.setPersonId(personVO.getPersonId());
			personDOList = dao.fetchByCriteria(personDO, true);

			boolean updateResult = dao.update(personDO);
			if (updateResult) {
				success = true;
				result.setPersonVO(personVO);
			} else {
				success = false;
				errorList = new ArrayList<String>();
				errorList
						.add("Error while updating details. Please contact System administrator.");
			}
		} else if ((personDOList.size() == 1)
				&& personDOList.get(0).getPersonId()
						.equals(personVO.getPersonId())) {
			success = false;
			errorList = new ArrayList<String>();
			errorList.add("You have not updated any of the details.");
		} else {
			success = false;
			errorList = new ArrayList<String>();
			errorList
					.add("Person with same deatils exists. Please check and try again.");
		}
		result.setErrorList(errorList);
		result.setSuccess(success);
		return result;
	}

	@Override
	public PersonResult fetchById(PersonVO personVO) {
		PersonResult result = new PersonResult();
		boolean success = false;
		List<String> errorList = null;

		PersonDO personDO = new PersonDO();
		personDO.setPersonId(personVO.getPersonId());

		List<PersonDO> personDOList = dao.fetchByCriteria(personDO, true);
		if ((null == personDOList) || (personDOList.size() != 1)) {
			success = false;
			errorList = new ArrayList<String>();
			errorList
					.add("Error while fetching details. Please contact System Administrator.");
		} else {
			success = true;
			result.setPersonVO(PersonConversionUtils.convertToVO(personDOList
					.get(0)));
		}

		result.setSuccess(success);
		result.setErrorList(errorList);
		return result;
	}

	@Override
	public PersonListResult fetchByCriteria(PersonVO personVO) {
		return fetchByCriteria(personVO, true);
	}

	@Override
	public PersonListResult searchByCriteria(PersonVO personVO) {
		return fetchByCriteria(personVO, false);
	}

	private PersonListResult fetchByCriteria(PersonVO personVO,
			boolean isExactSearch) {
		PersonListResult result = new PersonListResult();
		boolean success = false;
		List<String> errorList = null;

		List<PersonDO> personDOList = dao.fetchByCriteria(
				PersonConversionUtils.convertToDO(personVO), isExactSearch);

		if ((null == personDOList) || (personDOList.size() == 0)) {
			success = false;
			errorList = new ArrayList<String>();
			errorList.add("No result available.");
		} else {
			success = true;
			List<PersonVO> personVOList = new ArrayList<PersonVO>();

			for (PersonDO personDO : personDOList) {
				personVOList.add(PersonConversionUtils.convertToVO(personDO));
			}
			result.setPersonVOList(personVOList);
		}

		result.setSuccess(success);
		result.setErrorList(errorList);
		return result;
	}
}
