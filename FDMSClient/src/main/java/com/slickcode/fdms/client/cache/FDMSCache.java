package com.slickcode.fdms.client.cache;

import java.util.List;

import com.slickcode.fdms.client.utils.SpringContextLoader;
import com.slickcode.fdms.common.vo.BankVO;
import com.slickcode.fdms.common.vo.PersonVO;
import com.slickcode.fdms.common.vo.SecurityQuestionVO;
import com.slickcode.fdms.common.vo.StatusVO;
import com.slickcode.fdms.service.BankServiceImpl;
import com.slickcode.fdms.service.IBankService;
import com.slickcode.fdms.service.IPersonService;
import com.slickcode.fdms.service.ISecurityQuestionService;
import com.slickcode.fdms.service.IStatusService;
import com.slickcode.fdms.service.PersonServiceImpl;
import com.slickcode.fdms.service.SecurityQuestionServiceImpl;
import com.slickcode.fdms.service.StatusServiceImpl;
import com.slickcode.fdms.service.serviceobject.BankListResult;
import com.slickcode.fdms.service.serviceobject.PersonListResult;

public class FDMSCache {
	private static final FDMSCache FDMS_CACHE = new FDMSCache();
	private List<SecurityQuestionVO> securityQuestionVOList;
	private List<StatusVO> statusVOList;
	private List<PersonVO> personVOList;
	private List<BankVO> bankVOList;

	private FDMSCache() {
		getSecurityQuestionVOList();
		getStatusVOList();
		getBankVOList();
		getPersonVOList();
	}

	public static FDMSCache getInstance() {
		return FDMS_CACHE;
	}

	public List<SecurityQuestionVO> getSecurityQuestionVOList() {
		if ((null == securityQuestionVOList) || (securityQuestionVOList.isEmpty())) {
			ISecurityQuestionService service = (SecurityQuestionServiceImpl) SpringContextLoader.getInstance()
					.loadContext().getBean("securityQuestionServiceImpl");
			securityQuestionVOList = service.fetchAll();
		}
		return securityQuestionVOList;
	}

	public SecurityQuestionVO getSecurityQuestionVOByCode(String code) {
		if (null != securityQuestionVOList) {
			for (SecurityQuestionVO securityQuestionVO : securityQuestionVOList) {
				if (code.equalsIgnoreCase(securityQuestionVO.getCode())) {
					return securityQuestionVO;
				}
			}
		}
		return null;
	}

	public List<StatusVO> getStatusVOList() {
		if ((null == statusVOList) || (statusVOList.isEmpty())) {
			IStatusService service = (StatusServiceImpl) SpringContextLoader.getInstance().loadContext()
					.getBean("statusServiceImpl");
			statusVOList = service.fetchAll();
		}
		return statusVOList;
	}

	public StatusVO getStatusVOByCode(String code) {
		if (null != statusVOList) {
			for (StatusVO statusVO : statusVOList) {
				if (code.equalsIgnoreCase(statusVO.getCode())) {
					return statusVO;
				}
			}
		}
		return null;
	}

	public List<PersonVO> getPersonVOList() {
		if (null == personVOList) {
			IPersonService personService = (PersonServiceImpl) SpringContextLoader.getInstance().loadContext()
					.getBean("personServiceImpl");
			PersonListResult result = personService.fetchByCriteria(null);
			if (result.isSuccess()) {
				personVOList = result.getPersonVOList();
			} else {
				System.out.println(result.getErrorList().toString());
			}
		}
		return personVOList;
	}

	public PersonVO getPersonVOByCode(Integer code) {
		if (null != personVOList) {
			for (PersonVO personVO : personVOList) {
				if (code.equals(personVO.getPersonId())) {
					return personVO;
				}
			}
		}
		return null;
	}

	public List<BankVO> getBankVOList() {
		if (null == bankVOList) {

			IBankService service = (BankServiceImpl) SpringContextLoader.getInstance().loadContext()
					.getBean("bankServiceImpl");
			BankListResult result = service.fetchByCriteria(null);
			if (result.isSuccess()) {
				bankVOList = result.getBankVOList();
			} else {
				System.out.println(result.getErrorList().toString());
			}
		}
		return bankVOList;
	}

	public BankVO getBankVOByCode(Integer code) {
		if (null != bankVOList) {
			for (BankVO bankVO : bankVOList) {
				if (code.equals(bankVO.getBankId())) {
					return bankVO;
				}
			}
		}
		return null;
	}

}
