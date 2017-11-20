package com.slickcode.fdms.client.page;

import javax.swing.JPanel;

import com.slickcode.baseframework.page.BaseHeaderPanel;
import com.slickcode.baseframework.page.BasePage;

public abstract class FdmsBasePage extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -755237125899008094L;

	public FdmsBasePage() {
	}

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
