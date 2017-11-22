package com.slickcode.fdms.client.login.page;

import javax.swing.JPanel;

import com.slickcode.baseframework.domain.IPanelBean;

public class LoginPage extends LoginBasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LoginPanel loginPanel;

	public LoginPage() {
		loadPanelData();
	}

	@Override
	public void arrangeComponents() {
		return;
	}

	@Override
	public JPanel createPanel() {
		loginPanel = new LoginPanel();
		loginPanel.createPanel();
		return loginPanel;
	}

	@Override
	public void loadPanelData() {
		this.loginPanel.loadPanelData();
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
