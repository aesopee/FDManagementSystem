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

public class InitiateNextWeekMaturingFDsActionListner extends FdmsActionListner {

	private List<FdVO> nextWeekFdVOList;
	private IFdService fdService;
	
	public InitiateNextWeekMaturingFDsActionListner() {
		this.fdService = (FdServiceImpl)applicationContext.getBean("fdServiceImpl");
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(new ShowAllFdPage(nextWeekFdVOList,HeaderConstants.MATURED_IN_NEXT_WEEK_FD));
	}

	@Override
	public boolean performAction(ActionEvent e) {
		FdListResult result = fdService.fetchFdsMaturingInNextWeek();

		if (result.isSuccess()) {
			nextWeekFdVOList = result.getFdVOList();
			return true;
		} else {
			setErrorList(result.getErrorList());
			return false;
		}
	}

}
