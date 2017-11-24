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

public class InitiateShowAllFdActionListner extends FdmsActionListner {

	private List<FdVO> fdVOList;
	private IFdService fdService;
	
	public InitiateShowAllFdActionListner() {
		this.fdService = (FdServiceImpl)applicationContext.getBean("fdServiceImpl");
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(new ShowAllFdPage(fdVOList, HeaderConstants.SHOW_ALL_FDS));

	}

	@Override
	public boolean performAction(ActionEvent e) {
		FdListResult result = fdService.fetchByCriteria(null);

		if (result.isSuccess()) {
			fdVOList = result.getFdVOList();
			return true;
		} else {
			setErrorList(result.getErrorList());
			return false;
		}
	}

}
