package com.slickcode.fdms.client.bank.page;

import java.util.List;

import javax.swing.JPanel;

import com.slickcode.baseframework.domain.IPanelBean;
import com.slickcode.fdms.client.page.FdmsBasePage;
import com.slickcode.fdms.common.vo.BankVO;

public class ShowAllBankPage extends FdmsBasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3510370658865013698L;
	private ShowAllBankPanel showAllBankPanel;
	private List<BankVO> bankVOList;
	
	public ShowAllBankPage(List<BankVO> bankVOList) {
		this.bankVOList = bankVOList;
		loadPanelData();
	}

	@Override
	public void arrangeComponents() {
		return;
	}

	@Override
	public JPanel createPanel() {
		showAllBankPanel = new ShowAllBankPanel();
		showAllBankPanel.createPanel();
		return showAllBankPanel;
	}

	@Override
	public void loadPanelData() {
		showAllBankPanel.setBankVOList(bankVOList);
		showAllBankPanel.loadPanelData();
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
