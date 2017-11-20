package com.slickcode.fdms.service;

import com.slickcode.fdms.common.vo.LoginVO;
import com.slickcode.fdms.common.vo.PersonVO;
import com.slickcode.fdms.service.serviceobject.LoginResult;

public interface ILoginService {
	LoginResult login(String userName, String password);
	
	LoginResult create(LoginVO loginVO);
	
	LoginResult checkPersonExistanceForNewUser(PersonVO personVO);
	
	LoginResult forgotPasswordStepOne(LoginVO loginVO);
	
	LoginResult forgotPasswordStepTwo(LoginVO loginVO);
	
	LoginResult changePassword(LoginVO loginVO);
}
