package com.slickcode.fdms.client.fd.listner;

import java.awt.event.ActionEvent;

import com.slickcode.fdms.client.cache.FDMSCache;
import com.slickcode.fdms.client.fd.page.FdMainPage;
import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.client.utils.ScreenMode;
import com.slickcode.fdms.common.constant.FdStatusEnum;
import com.slickcode.fdms.common.vo.FdVO;

public class InitiateAddFdActionListner extends FdmsActionListner {
	private FdVO fdVO;

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(
				new FdMainPage(fdVO, ScreenMode.CREATE));
	}

	@Override
	public boolean performAction(ActionEvent e) {
		fdVO = new FdVO();
		fdVO.setStatusVO(FDMSCache.getInstance().getStatusVOByCode(FdStatusEnum.CURRENT.getCode()));
		fdVO.setRenewedFrom(0);
		fdVO.setRenewedTo(0);
		fdVO.setOriginalFdNumber(0);
		return true;
	}

}
