package com.slickcode.fdms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.slickcode.fdms.common.vo.LoginVO;
import com.slickcode.fdms.common.vo.PersonVO;
import com.slickcode.fdms.service.dao.ILoginDao;
import com.slickcode.fdms.service.dao.IPersonDao;
import com.slickcode.fdms.service.domain.LoginDO;
import com.slickcode.fdms.service.domain.PersonDO;
import com.slickcode.fdms.service.serviceobject.LoginResult;
import com.slickcode.fdms.utils.LoginConversionUtils;
import com.slickcode.fdms.utils.PersonConversionUtils;

@Component("loginServiceImp")
public class LoginServiceImpl implements ILoginService {

	@Autowired
	@Qualifier("loginDaoImpl")
	private ILoginDao loginDao;
	
	@Autowired
	@Qualifier("personDaoImpl")
	private IPersonDao personDao;

	@Override
	public LoginResult login(String userName, String password) {
		LoginResult result = new LoginResult();
		boolean success = false;
		List<String> errorList = null;

		LoginVO loginVO = new LoginVO();
		loginVO.setUserName(userName);
		List<LoginDO> loginDOList = loginDao.fetchByCriteria(
				LoginConversionUtils.convertToDO(loginVO), true);
		if ((null == loginDOList) || (loginDOList.size() == 0)) {
			success = false;
			errorList = new ArrayList<String>();
			errorList.add("Invalid username.");
		} else {
			loginVO.setPassword(password);
			loginDOList = loginDao.fetchByCriteria(
					LoginConversionUtils.convertToDO(loginVO), true);
			if ((null == loginDOList) || (loginDOList.size() == 0)) {
				success = false;
				errorList = new ArrayList<String>();
				errorList.add("Username and password don't match.");
			} else {
				success = true;
				result.setLoginVO(LoginConversionUtils.convertToVO(loginDOList
						.get(0)));
			}

		}
		result.setErrorList(errorList);
		result.setSuccess(success);
		return result;
	}

	@Override
	public LoginResult create(LoginVO loginVO) {
		LoginDO loginDO = new LoginDO();
		loginDO.setUserName(loginVO.getUserName());
		List<LoginDO> loginDOList = loginDao.fetchByCriteria(loginDO, true);

		LoginResult result = new LoginResult();
		boolean success = false;
		List<String> errorList = null;
		if ((null == loginDOList) || (loginDOList.size() != 1)) {
			loginDO = LoginConversionUtils.convertToDO(loginVO);
			Integer loginId = loginDao.create(loginDO);

			if ((null == loginId) || (loginId == 0)) {
				success = false;
				errorList = new ArrayList<String>();
				errorList.add("Error in creating user.");
			} else {
				success = true;
				loginDO.setLoginId(loginId);
				result.setLoginVO(loginVO);
			}
		} else {
			success = false;
			errorList = new ArrayList<String>();
			errorList
					.add("Username already exists. Please use another username.");
		}
		result.setSuccess(success);
		result.setErrorList(errorList);
		return result;
	}

	@Override
	public LoginResult checkPersonExistanceForNewUser(PersonVO personVO) {
		LoginResult result = new LoginResult();
		List<String> errorList = null;
		boolean success = false;
		List<PersonDO> personDOList = personDao.fetchByCriteria(
				PersonConversionUtils.convertToDO(personVO), true);

		if ((null == personDOList) || (personDOList.size() != 1)) {
			success = false;
			errorList = new ArrayList<String>();
			errorList.add("No such user exists. Please verify and try again.");
		} else {
			LoginDO loginDO = new LoginDO();
			loginDO.setPersonDO(personDOList.get(0));

			List<LoginDO> loginDOList = loginDao.fetchByCriteria(loginDO, true);

			if ((null == loginDOList) || (loginDOList.size() != 1)) {
				success = true;
				LoginVO loginVO = new LoginVO();
				loginVO.setPersonVO(PersonConversionUtils
						.convertToVO(personDOList.get(0)));
				result.setLoginVO(loginVO);
			} else {
				success = false;
				errorList = new ArrayList<String>();
				errorList
						.add("User already exists. Please contact adminnistrator to give your username and password.");
			}
		}
		result.setSuccess(success);
		result.setErrorList(errorList);
		return result;
	}

	@Override
	public LoginResult forgotPasswordStepOne(LoginVO loginVO) {
		LoginResult result = new LoginResult();
		List<String> errorList = null;
		boolean success = false;
		List<PersonDO> personDOList = personDao.fetchByCriteria(
				PersonConversionUtils.convertToDO(loginVO.getPersonVO()), true);

		if ((null == personDOList) || (personDOList.size() != 1)) {
			success = false;
			errorList = new ArrayList<String>();
			errorList.add("Invalid Person Id. Please verify and try again.");
		} else {
			LoginDO loginDO = LoginConversionUtils.convertToDO(loginVO);
			loginDO.setPersonDO(personDOList.get(0));

			List<LoginDO> loginDOList = loginDao.fetchByCriteria(loginDO, true);

			if ((null == loginDOList) || (loginDOList.size() != 1)) {
				success = false;
				errorList = new ArrayList<String>();
				errorList.add("Username and Person Id don't match");
			} else {
				success = true;
				loginVO = LoginConversionUtils.convertToVO(loginDOList.get(0));
				result.setLoginVO(loginVO);
			}
		}
		result.setSuccess(success);
		result.setErrorList(errorList);
		return result;
	}

	@Override
	public LoginResult forgotPasswordStepTwo(LoginVO loginVO) {
		LoginResult result = new LoginResult();
		List<String> errorList = null;
		boolean success = false;

		LoginDO loginDO = LoginConversionUtils.convertToDO(loginVO);

		List<LoginDO> loginDOList = loginDao.fetchByCriteria(loginDO, true);

		if ((null == loginDOList) || (loginDOList.size() != 1)) {
			success = false;
			errorList = new ArrayList<String>();
			errorList.add("Invalid Security Answer.");
		} else {
			success = true;
			loginVO = LoginConversionUtils.convertToVO(loginDOList.get(0));
			result.setLoginVO(loginVO);
		}
		result.setSuccess(success);
		result.setErrorList(errorList);
		return result;
	}

	@Override
	public LoginResult changePassword(LoginVO loginVO) {
		LoginResult result = new LoginResult();
		List<String> errorList = null;
		boolean success = false;

		LoginDO loginDO = LoginConversionUtils.convertToDO(loginVO);
		loginDO.setPassword(null);
		List<LoginDO> loginDOList = loginDao.fetchByCriteria(loginDO, true);

		if ((null == loginDOList) || (loginDOList.size() != 1)) {
			success = false;
			errorList = new ArrayList<String>();
			errorList.add("No such user exists.");
		} else {
			loginDO = loginDOList.get(0);
			loginDO.setPassword(loginVO.getPassword());
			boolean status = loginDao.update(loginDO);

			if (status) {
				success = true;
			} else {
				success = false;
				errorList = new ArrayList<String>();
				errorList
						.add("Issue in updating password. Kindly contact System Administrator.");
			}
		}
		result.setSuccess(success);
		result.setErrorList(errorList);
		return result;
	}

}
