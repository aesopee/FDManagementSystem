package com.slickcode.fdms.client.login.page;

import javax.swing.JPanel;

import com.slickcode.baseframework.domain.IPanelBean;
import com.slickcode.fdms.client.login.page.LoginBasePage;
import com.slickcode.fdms.common.vo.LoginVO;

public class ForgotPasswordThirdPage extends LoginBasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 522419597220878726L;

	private LoginVO loginVO;
	private ForgotPasswordThirdPanel forgotPasswordThirdPanel;

	public ForgotPasswordThirdPage(LoginVO loginVO) {
		this.loginVO = loginVO;
		loadPanelData();
	}

	public void arrangeComponents() {
		return;
	}

	@Override
	public JPanel createPanel() {
		forgotPasswordThirdPanel = new ForgotPasswordThirdPanel();
		forgotPasswordThirdPanel.createPanel();
		return forgotPasswordThirdPanel;
	}

	@Override
	public void loadPanelData() {
		this.forgotPasswordThirdPanel.setLoginVO(this.loginVO);
		this.forgotPasswordThirdPanel.loadPanelData();
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
