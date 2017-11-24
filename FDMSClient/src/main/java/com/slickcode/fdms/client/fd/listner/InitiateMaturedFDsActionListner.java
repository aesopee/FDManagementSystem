package com.slickcode.fdms.client.fd.listner;

import java.awt.event.ActionEvent;
import java.util.List;

import com.slickcode.fdms.client.constants.HeaderConstants;
import com.slickcode.fdms.client.fd.page.ShowAllFdPage;
import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.common.vo.FdVO;
import com.slickcode.fdms.service.FdServiceImpl;
import com.slickcode.fdms.service.IFdService;
import com.slickcode.fdms.service.serviceobject.FdListResult;

public class InitiateMaturedFDsActionListner extends FdmsActionListner {

	private List<FdVO> maturedFdVOList;
	private IFdService fdService;
	
	public InitiateMaturedFDsActionListner() {
		this.fdService = (FdServiceImpl)applicationContext.getBean("fdServiceImpl");
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(new ShowAllFdPage(maturedFdVOList, HeaderConstants.MATURED_FD));
	}

	@Override
	public boolean performAction(ActionEvent e) {
		FdListResult result = fdService.fetchMaturedFds();

		if (result.isSuccess()) {
			maturedFdVOList = result.getFdVOList();
			return true;
		} else {
			setErrorList(result.getErrorList());
			return false;
		}
	}

}
