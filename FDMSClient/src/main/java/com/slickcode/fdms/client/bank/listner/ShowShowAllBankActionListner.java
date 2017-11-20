package com.slickcode.fdms.client.bank.listner;

import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.slickcode.baseframework.table.PaginatedTablePanel;
import com.slickcode.fdms.client.bank.page.BankMainPage;
import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.client.utils.ScreenMode;
import com.slickcode.fdms.common.vo.BankVO;
import com.slickcode.fdms.service.BankServiceImpl;
import com.slickcode.fdms.service.IBankService;
import com.slickcode.fdms.service.serviceobject.BankResult;

public class ShowShowAllBankActionListner extends FdmsActionListner {

	private PaginatedTablePanel panel;
	private BankVO bankVO;
	private IBankService bankService;
	
	public ShowShowAllBankActionListner(PaginatedTablePanel panel) {
		this.panel = panel;
		this.bankService = (BankServiceImpl)applicationContext.getBean("bankServiceImpl");
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(
				new BankMainPage(bankVO, ScreenMode.VIEW));
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean performAction(ActionEvent e) {
		JTable table = panel.getTable();
		int itemsPerPage = panel.getItemsPerPage();
		int selectedIndexOfPage = panel.getSelectedIndexOfPage();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Vector<Object> rowDataVector = (Vector<Object>) model.getDataVector()
				.elementAt(
						((selectedIndexOfPage - 1) * itemsPerPage)
								+ table.getSelectedRow());

		Integer bankId = (Integer) rowDataVector.get(0);

		bankVO = new BankVO();
		bankVO.setBankId(bankId);

		BankResult result = bankService.fetchById(bankVO);

		if (result.isSuccess()) {
			bankVO = result.getBankVO();
			return true;
		} else {
			setErrorList(result.getErrorList());
			return false;
		}
	}

}
