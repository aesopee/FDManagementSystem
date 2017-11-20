package com.slickcode.fdms.service.serviceobject;

import java.util.List;

import com.slickcode.fdms.common.vo.FdVO;

public class FdListResult extends BaseDomain {
	private List<FdVO> fdVOList;

	/**
	 * @return the fdVOList
	 */
	public List<FdVO> getFdVOList() {
		return fdVOList;
	}

	/**
	 * @param fdVOList the fdVOList to set
	 */
	public void setFdVOList(List<FdVO> fdVOList) {
		this.fdVOList = fdVOList;
	}
}
