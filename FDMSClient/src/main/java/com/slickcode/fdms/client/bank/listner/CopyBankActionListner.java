package com.slickcode.fdms.client.bank.listner;

import java.awt.event.ActionEvent;

import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.fdms.client.bank.page.BankMainPage;
import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.client.utils.ScreenMode;
import com.slickcode.fdms.common.bean.BankPanelBean;
import com.slickcode.fdms.common.vo.BankVO;
import com.slickcode.fdms.service.BankServiceImpl;
import com.slickcode.fdms.service.IBankService;
import com.slickcode.fdms.service.serviceobject.BankResult;

public class CopyBankActionListner extends FdmsActionListner {

	private BasePanel basePanel;
	private BankVO bankVO;
	private IBankService bankService;
	
	public CopyBankActionListner(BasePanel basePanel) {
		this.basePanel = basePanel;
		this.bankService = (BankServiceImpl)applicationContext.getBean("bankServiceImpl");
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(
				new BankMainPage(bankVO, ScreenMode.CREATE));
	}

	@Override
	public boolean performAction(ActionEvent e) {
		if (basePanel.validatePanelData()) {
			BankPanelBean bankPanelBean = (BankPanelBean) basePanel
					.getPanelDataOnSubmit();
			BankResult result = bankService
					.fetchById(bankPanelBean.getBankVO());

			if (result.isSuccess()) {
				bankVO = result.getBankVO();
				bankVO.setBankId(null);
				return true;
			} else {
				setErrorList(result.getErrorList());
				return false;
			}
		}
		return false;
	}

}
