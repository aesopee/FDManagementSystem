package com.slickcode.fdms.client.bank.listner;

import java.awt.event.ActionEvent;
import java.util.List;

import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.fdms.client.bank.page.ShowAllBankPage;
import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.common.bean.BankPanelBean;
import com.slickcode.fdms.common.vo.BankVO;
import com.slickcode.fdms.service.BankServiceImpl;
import com.slickcode.fdms.service.IBankService;
import com.slickcode.fdms.service.serviceobject.BankListResult;

public class SearchBankActionListner extends FdmsActionListner {
	private BasePanel basePanel;
	private List<BankVO> bankVOList;
	private IBankService bankService;
	
	public SearchBankActionListner(BasePanel basePanel) {
		this.basePanel = basePanel;
		this.bankService = (BankServiceImpl)applicationContext.getBean("bankServiceImpl");
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(new ShowAllBankPage(bankVOList));
	}

	@Override
	public boolean performAction(ActionEvent e) {
		if (basePanel.validatePanelData()) {
			BankPanelBean bankPanelBean = (BankPanelBean) basePanel
					.getPanelDataOnSubmit();

			BankListResult result = bankService.searchByCriteria(bankPanelBean
					.getBankVO());

			if (result.isSuccess()) {
				bankVOList = result.getBankVOList();
				return true;
			} else {
				setErrorList(result.getErrorList());
				return false;
			}

		}
		return false;
	}

}
