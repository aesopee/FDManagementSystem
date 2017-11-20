package com.slickcode.fdms.client.fd.listner;

import java.awt.event.ActionEvent;

import com.slickcode.fdms.client.fd.page.FdMainPage;
import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.client.utils.ScreenMode;
import com.slickcode.fdms.common.vo.FdVO;

public class InitiateSearchFdActionListner extends FdmsActionListner {

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(
				new FdMainPage(new FdVO(), ScreenMode.SEARCH));
	}

	@Override
	public boolean performAction(ActionEvent e) {
		return true;
	}

}
