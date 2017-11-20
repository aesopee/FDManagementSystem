package com.slickcode.fdms.client.login.listner;

import java.awt.event.ActionEvent;

import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.login.page.NewUserFirstPage;
import com.slickcode.fdms.client.page.MainPage;

public class GoToNewUserPageFirstActionListner extends FdmsActionListner {

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(new NewUserFirstPage());
	}

	@Override
	public boolean performAction(ActionEvent e) {
		return true;
	}
}
