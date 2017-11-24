package com.slickcode.fdms.client.login.listner;

import java.awt.event.ActionEvent;

import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.fdms.client.constants.HeaderConstants;
import com.slickcode.fdms.client.home.page.HomePage;
import com.slickcode.fdms.client.page.MainPage;

public class GoToHomePageListner extends LoginButtonActionListner {

	public GoToHomePageListner(BasePanel basePanel) {
		super(basePanel);
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(
				new HomePage(currentFdVOList, maturedFdVOList,
						nextWeekFdVOList, HeaderConstants.ACTIVE_FDS,
						HeaderConstants.MATURED_FD,
						HeaderConstants.MATURED_IN_NEXT_WEEK_FD));
	}

	@Override
	public boolean performAction(ActionEvent e) {
		fetchCurrentFdList();
		fetchMaturedFdList();
		fetchNextWeekFdList();
		return true;
	}

}
