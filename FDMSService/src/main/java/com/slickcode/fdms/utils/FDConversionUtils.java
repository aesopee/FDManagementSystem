package com.slickcode.fdms.utils;

import com.slickcode.fdms.common.vo.FdVO;
import com.slickcode.fdms.service.domain.FdDO;

public class FDConversionUtils {
	private FDConversionUtils() {

	}

	public static FdVO convertToVO(FdDO fddo) {
		if (null == fddo) {
			return null;
		}
		FdVO fdVO = new FdVO();
		fdVO.setBankVO(BankConversionUtils.convertToVO(fddo.getBankDO()));
		fdVO.setFdBankReferenceNumber(fddo.getFdBankReferenceNumber());
		fdVO.setFdId(fddo.getFdId());
		fdVO.setFdNumber(fddo.getFdNumber());
		fdVO.setFirstOwnerVO(PersonConversionUtils.convertToVO(fddo.getPrimaryPersonDO()));
		fdVO.setInvestedAmount(fddo.getInvestedAmount());
		fdVO.setInvestmentDate(fddo.getInvestmentDate());
		fdVO.setMaturityAmount(fddo.getMaturityAmount());
		fdVO.setMaturityDate(fddo.getMaturityDate());
		fdVO.setNomineeVO(PersonConversionUtils.convertToVO(fddo.getNomineeDO()));
		fdVO.setRemark(fddo.getRemark());
		fdVO.setRenewedFrom(fddo.getRenewedFrom());
		fdVO.setRenewedTo(fddo.getRenewedTo());
		fdVO.setSecondOwnerVO(PersonConversionUtils.convertToVO(fddo.getSecondaryPersonDO()));
		fdVO.setOriginalFdNumber(fddo.getOriginalFdNumber());
		fdVO.setStatusVO(StatusConversionUtils.convertToVO(fddo.getStatusDO()));
		BaseConversionUtils.convertToVO(fddo, fdVO);
		return fdVO;
	}

	public static FdDO convertToDO(FdVO fdVo) {
		if (null == fdVo) {
			return null;
		}
		FdDO fdDO = new FdDO();
		fdDO.setBankDO(BankConversionUtils.convertToDO(fdVo.getBankVO()));
		fdDO.setFdBankReferenceNumber(fdVo.getFdBankReferenceNumber());
		fdDO.setFdId(fdVo.getFdId());
		fdDO.setFdNumber(fdVo.getFdNumber());
		fdDO.setPrimaryPersonDO(PersonConversionUtils.convertToDO(fdVo.getFirstOwnerVO()));
		fdDO.setInvestedAmount(fdVo.getInvestedAmount());
		fdDO.setInvestmentDate(fdVo.getInvestmentDate());
		fdDO.setMaturityAmount(fdVo.getMaturityAmount());
		fdDO.setMaturityDate(fdVo.getMaturityDate());
		fdDO.setNomineeDO(PersonConversionUtils.convertToDO(fdVo.getNomineeVO()));
		fdDO.setRemark(fdVo.getRemark());
		fdDO.setRenewedFrom(fdVo.getRenewedFrom());
		fdDO.setRenewedTo(fdVo.getRenewedTo());
		fdDO.setSecondaryPersonDO(PersonConversionUtils.convertToDO(fdVo.getSecondOwnerVO()));
		fdDO.setOriginalFdNumber(fdVo.getOriginalFdNumber());
		fdDO.setStatusDO(StatusConversionUtils.convertToDO(fdVo.getStatusVO()));
		BaseConversionUtils.convertToDO(fdVo, fdDO);
		return fdDO;
	}

}
