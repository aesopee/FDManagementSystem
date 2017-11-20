package com.slickcode.fdms.client.login.page;

import javax.swing.JPanel;

import com.slickcode.baseframework.domain.IPanelBean;
import com.slickcode.fdms.client.login.page.LoginBasePage;
import com.slickcode.fdms.common.vo.LoginVO;

public class ForgotPasswordSecondPage extends LoginBasePage {

	/*
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LoginVO loginVO;
	private ForgotPasswordSecondPanel forgotPasswordSecondPanel;

	public ForgotPasswordSecondPage(LoginVO loginVO) {
		this.loginVO = loginVO;
		loadPanelData();
	}

	@Override
	public void arrangeComponents() {
	}

	@Override
	public JPanel createPanel() {
		forgotPasswordSecondPanel = new ForgotPasswordSecondPanel();
		forgotPasswordSecondPanel.createPanel();
		return forgotPasswordSecondPanel;
	}

	@Override
	public void loadPanelData() {
		this.forgotPasswordSecondPanel.setLoginVO(loginVO);
		this.forgotPasswordSecondPanel.loadPanelData();
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
	}

	@Override
	public void prepareTabOutOrderList() {
	}
}
