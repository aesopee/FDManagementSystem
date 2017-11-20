package com.slickcode.fdms.client.fd.listner;

import java.awt.event.ActionEvent;

import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.fdms.client.fd.page.FdMainPage;
import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.client.utils.ScreenMode;
import com.slickcode.fdms.common.bean.FdPanelBean;
import com.slickcode.fdms.common.vo.FdVO;
import com.slickcode.fdms.service.FdServiceImpl;
import com.slickcode.fdms.service.IFdService;
import com.slickcode.fdms.service.serviceobject.FdResult;

public class WithdrawFdActionListner extends FdmsActionListner {

	private BasePanel basePanel;
	private FdVO fdVO;
	private IFdService fdService;
	
	
	public WithdrawFdActionListner(BasePanel basePanel) {
		this.basePanel = basePanel;
		this.fdService = (FdServiceImpl)applicationContext.getBean("fdServiceImpl");
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(new FdMainPage(fdVO, ScreenMode.VIEW));
	}

	@Override
	public boolean performAction(ActionEvent e) {
		FdPanelBean fdPanelBean = (FdPanelBean) basePanel
				.getPanelDataOnSubmit();
		fdVO = fdPanelBean.getFdVO();
		int popupResult = showYesNoPopUp("Do you want to withdraw FD?",
				"FD withdraw confirmation");
		if (popupResult == 0) {
			FdResult result = fdService.withdraw(fdPanelBean.getFdVO());
			if (result.isSuccess()) {
				showPopUp("FD Withdrawn successfully.", "Message");
				fdVO = result.getFdVO();
				return true;
			} else {
				setErrorList(result.getErrorList());
				return false;
			}
		} else {
			return true;
		}
	}
}
