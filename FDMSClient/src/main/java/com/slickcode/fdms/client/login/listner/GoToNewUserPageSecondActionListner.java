package com.slickcode.fdms.client.login.listner;

import java.awt.event.ActionEvent;

import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.login.page.NewUserSecondPage;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.common.bean.PersonPanelBean;
import com.slickcode.fdms.common.vo.LoginVO;
import com.slickcode.fdms.service.ILoginService;
import com.slickcode.fdms.service.LoginServiceImpl;
import com.slickcode.fdms.service.serviceobject.LoginResult;

public class GoToNewUserPageSecondActionListner extends FdmsActionListner {
	private BasePanel basePanel;
	private LoginVO loginVO;
	private ILoginService loginService;

	public GoToNewUserPageSecondActionListner(BasePanel basePanel) {
		this.basePanel = basePanel;
		loginService = (LoginServiceImpl) applicationContext
				.getBean("loginServiceImp");
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(new NewUserSecondPage(loginVO));
	}

	@Override
	public boolean performAction(ActionEvent e) {
		if (basePanel.validatePanelData()) {
			PersonPanelBean bean = (PersonPanelBean) basePanel
					.getPanelDataOnSubmit();
			LoginResult result = loginService
					.checkPersonExistanceForNewUser(bean.getPersonVO());

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
