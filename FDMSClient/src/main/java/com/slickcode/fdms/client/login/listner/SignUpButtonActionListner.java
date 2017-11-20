package com.slickcode.fdms.client.login.listner;

import java.awt.event.ActionEvent;

import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.login.page.LoginPage;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.common.bean.LoginPanelBean;
import com.slickcode.fdms.service.ILoginService;
import com.slickcode.fdms.service.LoginServiceImpl;
import com.slickcode.fdms.service.serviceobject.LoginResult;

public class SignUpButtonActionListner extends FdmsActionListner {

	private BasePanel basePanel;
	private ILoginService loginService;

	public SignUpButtonActionListner(BasePanel basePanel) {
		this.basePanel = basePanel;
		loginService = (LoginServiceImpl) applicationContext
				.getBean("loginServiceImp");
	}

	public SignUpButtonActionListner() {
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
			LoginResult result = loginService.create(bean.getLoginVO());

			if (result.isSuccess()) {
				return true;
			} else {
				setErrorList(result.getErrorList());
				return false;
			}
		}
		return false;
	}

}
