package com.slickcode.fdms.client.person.listner;

import java.awt.event.ActionEvent;

import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.client.person.page.PersonMainPage;
import com.slickcode.fdms.client.utils.ScreenMode;
import com.slickcode.fdms.common.vo.PersonVO;

public class InitiateAddPersonActionListner extends FdmsActionListner {

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(
				new PersonMainPage(new PersonVO(), ScreenMode.CREATE));
	}

	@Override
	public boolean performAction(ActionEvent e) {
		return true;
	}

}
