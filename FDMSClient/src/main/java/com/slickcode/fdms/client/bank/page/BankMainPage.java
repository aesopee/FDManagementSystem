package com.slickcode.fdms.client.bank.page;

import javax.swing.JPanel;

import com.slickcode.baseframework.domain.IPanelBean;
import com.slickcode.fdms.client.page.FdmsBasePage;
import com.slickcode.fdms.client.utils.ScreenMode;
import com.slickcode.fdms.common.vo.BankVO;

public class BankMainPage extends FdmsBasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4170131650355391634L;
	private BankMainPanel bankMainPanel;
	private BankVO bankVO;
	private ScreenMode screenMode;

	public BankMainPage(BankVO bankVO, ScreenMode screenMode) {
		this.bankVO = bankVO;
		this.screenMode = screenMode;
		loadPanelData();
	}

	@Override
	public void arrangeComponents() {
	}

	@Override
	public JPanel createPanel() {
		this.bankMainPanel = new BankMainPanel();
		this.bankMainPanel.createPanel();
		return this.bankMainPanel;
	}

	@Override
	public void loadPanelData() {
		this.bankMainPanel.setBankVO(this.bankVO);
		this.bankMainPanel.setScreenMode(this.screenMode);
		this.bankMainPanel.loadPanelData();
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
