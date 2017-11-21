package com.slickcode.fdms.client.login.page;

import javax.swing.JPanel;

import com.slickcode.baseframework.domain.IPanelBean;

public class ForgotPasswordFirstPage extends LoginBasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 818047004991722239L;
	private ForgotPasswordFirstPanel forgotPasswordFirstPanel;

	public ForgotPasswordFirstPage() {
		loadPanelData();
	}

	@Override
	public void arrangeComponents() {
		return;
	}

	@Override
	public JPanel createPanel() {
		forgotPasswordFirstPanel = new ForgotPasswordFirstPanel();
		forgotPasswordFirstPanel.createPanel();
		return forgotPasswordFirstPanel;
	}

	@Override
	public void loadPanelData() {
		this.forgotPasswordFirstPanel.loadPanelData();
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
