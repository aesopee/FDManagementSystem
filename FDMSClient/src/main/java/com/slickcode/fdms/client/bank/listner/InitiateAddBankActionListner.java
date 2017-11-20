package com.slickcode.fdms.client.bank.listner;

import java.awt.event.ActionEvent;

import com.slickcode.fdms.client.bank.page.BankMainPage;
import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.client.utils.ScreenMode;
import com.slickcode.fdms.common.vo.BankVO;

public class InitiateAddBankActionListner extends FdmsActionListner {

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(
				new BankMainPage(new BankVO(), ScreenMode.CREATE));
	}

	@Override
	public boolean performAction(ActionEvent e) {
		return true;
	}

}
