package com.slickcode.fdms.client.bank.listner;

import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.baseframework.table.PaginatedTablePanel;
import com.slickcode.fdms.client.bank.page.BankMainPage;
import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.client.utils.ScreenMode;
import com.slickcode.fdms.common.bean.BankPanelBean;
import com.slickcode.fdms.common.vo.BankVO;
import com.slickcode.fdms.service.BankServiceImpl;
import com.slickcode.fdms.service.IBankService;
import com.slickcode.fdms.service.serviceobject.BankResult;

public class EditBankActionListner extends FdmsActionListner {

	private PaginatedTablePanel paginatedTablePanel;
	private BasePanel basePanel;
	private BankVO bankVO;
	private IBankService bankService;
	
	public EditBankActionListner(PaginatedTablePanel paginatedTablePanel) {
		this.paginatedTablePanel = paginatedTablePanel;
		this.bankService = (BankServiceImpl)applicationContext.getBean("bankServiceImpl");
	}

	public EditBankActionListner(BasePanel basePanel) {
		this.basePanel = basePanel;
		this.bankService = (BankServiceImpl)applicationContext.getBean("bankServiceImpl");
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(
				new BankMainPage(bankVO, ScreenMode.EDIT));
	}

	@Override
	public boolean performAction(ActionEvent e) {
		if (poppulateBankVO()) {
			BankResult result = bankService.fetchById(bankVO);

			if (result.isSuccess()) {
				bankVO = result.getBankVO();
				return true;
			} else {
				setErrorList(result.getErrorList());
				return false;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private boolean poppulateBankVO() {
		if (null != paginatedTablePanel) {
			JTable table = paginatedTablePanel.getTable();
			int itemsPerPage = paginatedTablePanel.getItemsPerPage();
			int selectedIndexOfPage = paginatedTablePanel
					.getSelectedIndexOfPage();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			Vector<Object> rowDataVector = (Vector<Object>) model
					.getDataVector().elementAt(
							((selectedIndexOfPage - 1) * itemsPerPage)
									+ table.getSelectedRow());

			Integer bankId = (Integer) rowDataVector.get(0);
			bankVO = new BankVO();
			bankVO.setBankId(bankId);
			return true;
		} else {
			if (basePanel.validatePanelData()) {
				BankPanelBean bankPanelBean = (BankPanelBean) basePanel
						.getPanelDataOnSubmit();
				bankVO = bankPanelBean.getBankVO();
				return true;
			}
			return false;
		}
	}
}
