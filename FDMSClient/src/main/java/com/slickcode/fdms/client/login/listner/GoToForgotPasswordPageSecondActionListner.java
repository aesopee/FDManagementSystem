package com.slickcode.fdms.client.login.listner;

import java.awt.event.ActionEvent;

import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.login.page.ForgotPasswordSecondPage;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.common.bean.LoginPanelBean;
import com.slickcode.fdms.common.vo.LoginVO;
import com.slickcode.fdms.service.ILoginService;
import com.slickcode.fdms.service.LoginServiceImpl;
import com.slickcode.fdms.service.serviceobject.LoginResult;

public class GoToForgotPasswordPageSecondActionListner extends
		FdmsActionListner {

	private BasePanel basePanel;
	private LoginVO loginVO;
	private ILoginService loginService;

	public GoToForgotPasswordPageSecondActionListner(BasePanel basePanel) {
		this.basePanel = basePanel;
		loginService = (LoginServiceImpl) applicationContext
				.getBean("loginServiceImp");
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(new ForgotPasswordSecondPage(loginVO));
	}

	@Override
	public boolean performAction(ActionEvent e) {
		if (basePanel.validatePanelData()) {
			LoginPanelBean bean = (LoginPanelBean) basePanel
					.getPanelDataOnSubmit();
			LoginResult result = loginService.forgotPasswordStepOne(bean
					.getLoginVO());

			if (result.isSuccess()) {
				loginVO = result.getLoginVO();
				return true;
			} else {
				setErrorList(result.getErrorList());
				return false;
			}
		}
		return false;
	}

}
