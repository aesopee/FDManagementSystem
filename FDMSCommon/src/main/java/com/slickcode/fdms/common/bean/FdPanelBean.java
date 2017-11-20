package com.slickcode.fdms.common.bean;

import com.slickcode.baseframework.domain.IPanelBean;
import com.slickcode.fdms.common.vo.FdVO;

public class FdPanelBean implements IPanelBean {
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
