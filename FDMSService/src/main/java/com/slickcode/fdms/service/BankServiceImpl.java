package com.slickcode.fdms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.slickcode.fdms.common.vo.BankVO;
import com.slickcode.fdms.service.dao.IBankDao;
import com.slickcode.fdms.service.domain.BankDO;
import com.slickcode.fdms.service.serviceobject.BankListResult;
import com.slickcode.fdms.service.serviceobject.BankResult;
import com.slickcode.fdms.utils.BankConversionUtils;

@Component("bankServiceImpl")
public class BankServiceImpl implements IBankService {
	@Autowired
	@Qualifier("bankDaoImpl")
	private IBankDao dao;

	@Override
	public BankResult create(BankVO bankVO) {
		BankResult result = new BankResult();
		List<String> errorList = null;
		boolean success = false;
		BankDO bankDO = BankConversionUtils.convertToDO(bankVO);

		List<BankDO> bankDOList = dao.fetchByCriteria(bankDO, true);

		if ((null == bankDOList) || (bankDOList.size() != 1)) {
			Integer bankId = dao.create(bankDO);
			if ((null == bankId) || (bankId == 0)) {
				success = false;
				errorList = new ArrayList<>();
				errorList.add("Cannot create Bank. Please contact System Administrator.");
			} else {
				success = true;
				bankVO.setBankId(bankId);
				result.setBankVO(bankVO);
			}
		} else {
			success = false;
			errorList = new ArrayList<>();
			errorList.add("This Bank already exists");
		}
		result.setErrorList(errorList);
		result.setSuccess(success);
		return result;
	}

	@Override
	public BankResult update(BankVO bankVO) {
		BankResult result = new BankResult();
		List<String> errorList = null;
		boolean success = false;
		BankDO bankDO = BankConversionUtils.convertToDO(bankVO);
		bankDO.setBankId(null);

		List<BankDO> bankDOList = dao.fetchByCriteria(bankDO, true);

		if ((null == bankDOList) || (bankDOList.isEmpty())) {
			bankDO.setBankId(bankVO.getBankId());

			boolean updateResult = dao.update(bankDO);
			if (updateResult) {
				success = true;
				result.setBankVO(bankVO);
			} else {
				success = false;
				errorList = new ArrayList<>();
				errorList.add("Error while updating details. Please contact System administrator.");
			}
		} else if ((bankDOList.size() == 1) && bankDOList.get(0).getBankId().equals(bankVO.getBankId())) {
			success = false;
			errorList = new ArrayList<>();
			errorList.add("You have not updated any of the details.");
		} else {
			success = false;
			errorList = new ArrayList<>();
			errorList.add("Bank with same deatils exists. Please check and try again.");
		}
		result.setErrorList(errorList);
		result.setSuccess(success);
		return result;
	}

	@Override
	public BankResult fetchById(BankVO bankVO) {
		BankResult result = new BankResult();
		boolean success = false;
		List<String> errorList = null;

		BankDO bankDO = new BankDO();
		bankDO.setBankId(bankVO.getBankId());

		List<BankDO> bankDOList = dao.fetchByCriteria(bankDO, true);
		if ((null == bankDOList) || (bankDOList.size() != 1)) {
			success = false;
			errorList = new ArrayList<>();
			errorList.add("Error while fetching details. Please contact System Administrator.");
		} else {
			success = true;
			result.setBankVO(BankConversionUtils.convertToVO(bankDOList.get(0)));
		}

		result.setSuccess(success);
		result.setErrorList(errorList);
		return result;
	}

	@Override
	public BankListResult fetchByCriteria(BankVO bankVO) {
		return fetchByCriteria(bankVO, true);
	}

	@Override
	public BankListResult searchByCriteria(BankVO bankVO) {
		return fetchByCriteria(bankVO, false);
	}

	private BankListResult fetchByCriteria(BankVO bankVO, boolean isExactSearch) {
		BankListResult result = new BankListResult();
		boolean success = false;
		List<String> errorList = null;

		List<BankDO> bankDOList = dao.fetchByCriteria(BankConversionUtils.convertToDO(bankVO), isExactSearch);

		if ((null == bankDOList) || (bankDOList.isEmpty())) {
			success = false;
			errorList = new ArrayList<>();
			errorList.add("No result available.");
		} else {
			success = true;
			List<BankVO> bankVOList = new ArrayList<>();

			for (BankDO bankDO : bankDOList) {
				bankVOList.add(BankConversionUtils.convertToVO(bankDO));
			}
			result.setBankVOList(bankVOList);
		}

		result.setSuccess(success);
		result.setErrorList(errorList);
		return result;
	}

}
