package com.slickcode.fdms.client.login.page;

import javax.swing.JPanel;

import com.slickcode.baseframework.page.BaseHeaderPanel;
import com.slickcode.baseframework.page.BasePage;
import com.slickcode.fdms.client.page.HeaderPanel;

public abstract class LoginBasePage extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public BaseHeaderPanel getHeaderPanel() {
		HeaderPanel headerPanel = new HeaderPanel();
		headerPanel.createPanel();
		headerPanel.loadPanelData();
		return headerPanel;
	}

	@Override
	public JPanel populateSideMenu() {
		return null;
	}
}
