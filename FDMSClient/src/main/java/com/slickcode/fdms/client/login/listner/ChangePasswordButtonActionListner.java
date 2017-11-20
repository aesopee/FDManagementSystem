package com.slickcode.fdms.client.login.listner;

import java.awt.event.ActionEvent;

import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.login.page.LoginPage;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.common.bean.LoginPanelBean;
import com.slickcode.fdms.service.ILoginService;
import com.slickcode.fdms.service.LoginServiceImpl;

public class ChangePasswordButtonActionListner extends FdmsActionListner {

	private BasePanel basePanel;
	private ILoginService loginService;

	public ChangePasswordButtonActionListner(BasePanel basePanel) {
		this.basePanel = basePanel;
		loginService = (LoginServiceImpl) applicationContext
				.getBean("loginServiceImp");
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(new LoginPage());
	}

	@Override
	public boolean performAction(ActionEvent e) {
		if (basePanel.validatePanelData()) {
			LoginPanelBean bean = (LoginPanelBean) basePanel
					.getPanelDataOnSubmit();

			loginService.changePassword(bean.getLoginVO());

			showPopUp(
					"Password changed successfully. Please login to continue.",
					"Message");
			return true;
		}
		return false;
	}

}
