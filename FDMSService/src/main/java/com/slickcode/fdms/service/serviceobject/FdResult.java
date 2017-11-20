package com.slickcode.fdms.service.serviceobject;

import com.slickcode.fdms.common.vo.FdVO;

public class FdResult extends BaseDomain {
	private FdVO fdVO;

	/**
	 * @return the fdVO
	 */
	public FdVO getFdVO() {
		return fdVO;
	}

	/**
	 * @param fdVO the fdVO to set
	 */
	public void setFdVO(FdVO fdVO) {
		this.fdVO = fdVO;
	}
	
	
}
