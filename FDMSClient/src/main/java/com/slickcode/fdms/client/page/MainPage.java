package com.slickcode.fdms.client.page;

import javax.swing.JMenuBar;

import com.slickcode.baseframework.page.BaseMainPage;
import com.slickcode.fdms.client.login.page.LoginPage;
import com.slickcode.fdms.common.vo.PersonVO;

public class MainPage extends BaseMainPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static MainPage mainPage;

	private MainPage() {
		super(new LoginPage());
	}

	public static MainPage getInstance() {
		if (null == mainPage) {
			mainPage = new MainPage();
		}
		return mainPage;
	}

	@Override
	public JMenuBar populateMenuBar() {
		FdmsMenuBar fdmsMenuBar = FdmsMenuBar.getInstance();
		fdmsMenuBar.createMenu(false, null);
		return fdmsMenuBar;
	}

	public void changeMenuBar(boolean isLoggedIn, PersonVO personVO) {
		FdmsMenuBar.getInstance().createMenu(isLoggedIn, personVO);
	}
}
