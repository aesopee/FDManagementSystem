package com.slickcode.fdms.client.login.page;

import javax.swing.JPanel;

import com.slickcode.baseframework.domain.IPanelBean;

public class NewUserFirstPage extends LoginBasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7159901658331377650L;

	private NewUserFirstPanel newUserFirstPanel;

	public NewUserFirstPage() {
		loadPanelData();
	}

	@Override
	public void arrangeComponents() {
		return;
	}

	@Override
	public JPanel createPanel() {
		newUserFirstPanel = new NewUserFirstPanel();
		newUserFirstPanel.createPanel();
		return newUserFirstPanel;
	}

	@Override
	public void loadPanelData() {
		this.newUserFirstPanel.loadPanelData();
		prepareTabOutOrderList();
	}

	@Override
	public IPanelBean getPanelDataOnSubmit() {
		return null;
	}

	@Override
	public boolean validatePanelData() {
		return false;
	}

	@Override
	public void applyRights() {
		return;
	}

	@Override
	public void prepareTabOutOrderList() {
		return;
	}
}
