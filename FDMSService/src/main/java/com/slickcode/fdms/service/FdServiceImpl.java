package com.slickcode.fdms.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.slickcode.fdms.common.constant.FdStatusEnum;
import com.slickcode.fdms.common.vo.FdVO;
import com.slickcode.fdms.service.dao.IFdDao;
import com.slickcode.fdms.service.domain.FdDO;
import com.slickcode.fdms.service.serviceobject.FdListResult;
import com.slickcode.fdms.service.serviceobject.FdResult;
import com.slickcode.fdms.utils.FDConversionUtils;
import com.slickcode.fdms.utils.StatusConversionUtils;

@Component("fdServiceImpl")
public class FdServiceImpl implements IFdService {

	@Autowired
	@Qualifier("fdDaoImpl")
	private IFdDao dao;

	@Autowired
	@Qualifier("statusServiceImpl")
	private IStatusService statusService;

	@Override
	public FdResult create(FdVO fdVO) {
		FdResult result = new FdResult();

		List<String> errorList = null;
		boolean success = false;
		FdDO fdDO = FDConversionUtils.convertToDO(fdVO);
		Integer i = dao.fetchMaxBankFdId(fdDO.getBankDO());
		if (null == i) {
			i = 0;
		}
		fdDO.setFdBankReferenceNumber(i + 1);
		Integer fdId = dao.create(fdDO);
		if ((null == fdId) || (fdId == 0)) {
			success = false;
			errorList = new ArrayList<>();
			errorList.add("Cannot create FD. Please contact System Administrator.");
		} else {
			fdVO.setFdId(fdId);
			fdVO = fetchById(fdVO).getFdVO();
			if (null == fdVO.getOriginalFdNumber() || fdVO.getOriginalFdNumber() == 0) {
				fdVO.setOriginalFdNumber(fdId);
				fdVO = update(fdVO).getFdVO();
			}
			success = true;
			result.setFdVO(fdVO);
		}
		result.setSuccess(success);
		return result;
	}

	@Override
	public FdResult update(FdVO fdVO) {
		FdResult result = new FdResult();
		List<String> errorList = null;
		boolean success = false;
		FdDO fdDO = FDConversionUtils.convertToDO(fdVO);

		boolean updateResult = dao.update(fdDO);
		if (updateResult) {
			success = true;
			result.setFdVO(fdVO);
		} else {
			success = false;
			errorList = new ArrayList<>();
			errorList.add("Error while updating details. Please contact System administrator.");
		}

		result.setErrorList(errorList);
		result.setSuccess(success);
		return result;
	}

	@Override
	public FdResult fetchById(FdVO fdVO) {
		FdResult result = new FdResult();
		boolean success = false;
		List<String> errorList = null;

		FdDO fdDO = new FdDO();
		fdDO.setFdId(fdVO.getFdId());

		List<FdDO> bankDOList = dao.fetchByCriteria(fdDO, true);
		if ((null == bankDOList) || (bankDOList.size() != 1)) {
			success = false;
			errorList = new ArrayList<>();
			errorList.add("Error while fetching details. Please contact System Administrator.");
		} else {
			success = true;
			result.setFdVO(FDConversionUtils.convertToVO(bankDOList.get(0)));
		}

		result.setSuccess(success);
		result.setErrorList(errorList);
		return result;
	}

	@Override
	public FdListResult fetchByCriteria(FdVO fdVO) {
		return fetchByCriteria(fdVO, true);
	}

	@Override
	public FdListResult searchByCriteria(FdVO fdVO) {
		return fetchByCriteria(fdVO, false);
	}

	private FdListResult fetchByCriteria(FdVO fdVO, boolean isExactSearch) {
		FdListResult result = new FdListResult();
		boolean success = false;
		List<String> errorList = null;

		List<FdDO> fdDOList = dao.fetchByCriteria(FDConversionUtils.convertToDO(fdVO), isExactSearch);

		if ((null == fdDOList) || (fdDOList.isEmpty())) {
			success = false;
			errorList = new ArrayList<>();
			errorList.add("No result available.");
		} else {
			success = true;
			List<FdVO> fdVOList = new ArrayList<>();

			for (FdDO fdDO : fdDOList) {
				fdVOList.add(FDConversionUtils.convertToVO(fdDO));
			}
			result.setFdVOList(fdVOList);
		}

		result.setSuccess(success);
		result.setErrorList(errorList);
		return result;
	}

	@Override
	public FdResult withdraw(FdVO fdVO) {
		FdResult fdResult = fetchById(fdVO);
		if (fdResult.isSuccess()) {
			FdVO fetchedFdVO = fdResult.getFdVO();
			fetchedFdVO.setStatusVO(statusService.fetchByCode(FdStatusEnum.WITHDRAWN.getCode()));
			fdResult = update(fetchedFdVO);
		}
		return fdResult;
	}

	@Override
	public FdResult renew(FdVO fdVO) {
		FdResult fdResult = create(fdVO);
		if (fdResult.isSuccess()) {
			FdVO fdVoOld = new FdVO();
			fdVoOld.setFdBankReferenceNumber(fdVO.getRenewedFrom());
			fdVoOld.setBankVO(fdVO.getBankVO());
			FdListResult fdListResult = fetchByCriteria(fdVoOld);
			if (fdListResult.isSuccess()) {
				if (fdListResult.getFdVOList().size() == 1) {
					fdVoOld = fdListResult.getFdVOList().get(0);
					fdVoOld.setStatusVO(statusService.fetchByCode(FdStatusEnum.RENEWED.getCode()));
					fdVoOld.setRenewedTo(fdResult.getFdVO().getFdBankReferenceNumber());

					FdResult fdResultOld = update(fdVoOld);

					if (!fdResult.isSuccess()) {
						return fdResultOld;
					}

				} else if (fdListResult.getFdVOList().size() > 1) {
					List<String> errorList = new ArrayList<>();
					errorList.add("Multiple records found to renew FD Number : " + fdVoOld.getFdBankReferenceNumber());
				} else {
					List<String> errorList = new ArrayList<>();
					errorList.add("No record found to revew FD Number : " + fdVoOld.getFdBankReferenceNumber());
				}
			} else {
				fdResult.setErrorList(fdListResult.getErrorList());
			}
		}

		return fdResult;
	}

	@Override
	public FdListResult track(FdVO fdVO) {
		FdVO refFdVO = new FdVO();
		refFdVO.setOriginalFdNumber(fdVO.getOriginalFdNumber());
		refFdVO.setBankVO(fdVO.getBankVO());
		FdListResult result = fetchByCriteria(refFdVO);
		if (result.isSuccess()) {
			List<FdVO> fdVOList = new ArrayList<>();
			fdVOList.addAll(result.getFdVOList());
			result.setFdVOList(fdVOList);
		}
		return result;
	}

	@Override
	public FdListResult fetchMaturedFds() {
		Date startDate = null;
		Date endDate = Calendar.getInstance().getTime();
		List<FdDO> fdDOList = dao.fetchByMaturityDateRangeAndStatus(startDate, endDate,
				StatusConversionUtils.convertToDO(statusService.fetchByCode(FdStatusEnum.CURRENT.getCode())));

		FdListResult result = new FdListResult();
		List<FdVO> fdVOList = new ArrayList<>();
		for (FdDO fdDO : fdDOList) {
			fdVOList.add(FDConversionUtils.convertToVO(fdDO));
		}
		result.setFdVOList(fdVOList);
		result.setSuccess(true);
		return result;
	}

	@Override
	public FdListResult fetchFdsMaturingInNextWeek() {
		Date startDate = Calendar.getInstance().getTime();
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.add(Calendar.DATE, 7);
		Date endDate = endCalendar.getTime();
		List<FdDO> fdDOList = dao.fetchByMaturityDateRangeAndStatus(startDate, endDate,
				StatusConversionUtils.convertToDO(statusService.fetchByCode(FdStatusEnum.CURRENT.getCode())));

		FdListResult result = new FdListResult();
		List<FdVO> fdVOList = new ArrayList<>();
		for (FdDO fdDO : fdDOList) {
			fdVOList.add(FDConversionUtils.convertToVO(fdDO));
		}
		result.setFdVOList(fdVOList);
		result.setSuccess(true);
		return result;
	}
}
