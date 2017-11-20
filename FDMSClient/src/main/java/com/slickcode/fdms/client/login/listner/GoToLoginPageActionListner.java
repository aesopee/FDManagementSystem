package com.slickcode.fdms.client.login.listner;

import java.awt.event.ActionEvent;

import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.login.page.LoginPage;
import com.slickcode.fdms.client.page.MainPage;

public class GoToLoginPageActionListner extends FdmsActionListner {

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(new LoginPage());
	}

	@Override
	public boolean performAction(ActionEvent e) {
		return true;
	}

}
