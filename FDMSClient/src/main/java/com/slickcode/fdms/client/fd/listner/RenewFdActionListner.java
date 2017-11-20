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

public class RenewFdActionListner extends FdmsActionListner {

	private BasePanel basePanel;
	private FdVO fdVO;
	private IFdService fdService;
	
	public RenewFdActionListner(BasePanel basePanel) {
		this.basePanel = basePanel;
		this.fdService = (FdServiceImpl)applicationContext.getBean("fdServiceImpl");
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(new FdMainPage(fdVO, ScreenMode.VIEW));
	}

	@Override
	public boolean performAction(ActionEvent e) {
		if (basePanel.validatePanelData()) {
			FdPanelBean fdPanelBean = (FdPanelBean) basePanel
					.getPanelDataOnSubmit();
			FdResult result = fdService.renew(fdPanelBean.getFdVO());
			if (result.isSuccess()) {
				showPopUp("New FD added.", "Message");
				fdVO = result.getFdVO();
				return true;
			} else {
				setErrorList(result.getErrorList());
				return false;
			}
		}
		return false;
	}
}
