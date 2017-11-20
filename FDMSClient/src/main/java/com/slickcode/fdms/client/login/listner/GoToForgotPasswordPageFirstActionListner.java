package com.slickcode.fdms.client.login.listner;

import java.awt.event.ActionEvent;

import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.login.page.ForgotPasswordFirstPage;
import com.slickcode.fdms.client.page.MainPage;

public class GoToForgotPasswordPageFirstActionListner extends FdmsActionListner {

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(new ForgotPasswordFirstPage());
	}

	@Override
	public boolean performAction(ActionEvent e) {
		return true;
	}

}
