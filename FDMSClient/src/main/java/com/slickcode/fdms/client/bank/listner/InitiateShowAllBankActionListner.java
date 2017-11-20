package com.slickcode.fdms.client.bank.listner;

import java.awt.event.ActionEvent;
import java.util.List;

import com.slickcode.fdms.client.bank.page.ShowAllBankPage;
import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.common.vo.BankVO;
import com.slickcode.fdms.service.BankServiceImpl;
import com.slickcode.fdms.service.IBankService;
import com.slickcode.fdms.service.serviceobject.BankListResult;

public class InitiateShowAllBankActionListner extends FdmsActionListner {

	private List<BankVO> bankVOList;
	private IBankService bankService;
	
	public InitiateShowAllBankActionListner() {
		this.bankService = (BankServiceImpl)applicationContext.getBean("bankServiceImpl");
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(new ShowAllBankPage(bankVOList));
	}

	@Override
	public boolean performAction(ActionEvent e) {
		BankListResult result = bankService.fetchByCriteria(null);

		if (result.isSuccess()) {
			bankVOList = result.getBankVOList();
			return true;
		} else {
			setErrorList(result.getErrorList());
			return false;
		}
	}

}
