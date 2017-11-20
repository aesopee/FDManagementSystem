package com.slickcode.fdms.client.login.page;

import javax.swing.JPanel;

import com.slickcode.baseframework.domain.IPanelBean;
import com.slickcode.fdms.client.login.page.LoginBasePage;
import com.slickcode.fdms.common.vo.LoginVO;

public class NewUserSecondPage extends LoginBasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5630664639286940253L;
	private NewUserSecondPanel newUserSecondPanel;
	private LoginVO loginVO;

	public NewUserSecondPage(LoginVO loginVO) {
		this.loginVO = loginVO;
		loadPanelData();
	}

	@Override
	public void arrangeComponents() {
	}

	@Override
	public JPanel createPanel() {
		newUserSecondPanel = new NewUserSecondPanel();
		newUserSecondPanel.createPanel();
		return newUserSecondPanel;
	}

	@Override
	public void loadPanelData() {
		this.newUserSecondPanel.setLoginVO(this.loginVO);
		this.newUserSecondPanel.loadPanelData();
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
