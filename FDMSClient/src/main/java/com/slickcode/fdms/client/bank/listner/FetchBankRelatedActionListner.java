package com.slickcode.fdms.client.bank.listner;

import java.awt.event.ActionEvent;
import java.util.List;

import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.fdms.client.constants.HeaderConstants;
import com.slickcode.fdms.client.fd.page.ShowAllFdPage;
import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.common.bean.BankPanelBean;
import com.slickcode.fdms.common.utils.SelectItemConvertor;
import com.slickcode.fdms.common.vo.BankVO;
import com.slickcode.fdms.common.vo.FdVO;
import com.slickcode.fdms.service.FdServiceImpl;
import com.slickcode.fdms.service.IFdService;
import com.slickcode.fdms.service.serviceobject.FdListResult;

public class FetchBankRelatedActionListner extends FdmsActionListner {

	private BasePanel basePanel;
	private List<FdVO> fdVOList;
	private IFdService fdService;
	private BankVO bankVO;
	
	public FetchBankRelatedActionListner(BasePanel basePanel) {
		this.basePanel = basePanel;
		this.fdService = (FdServiceImpl)applicationContext.getBean("fdServiceImpl");
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(new ShowAllFdPage(fdVOList,HeaderConstants.FDS_RELATED_TO_BANK+SelectItemConvertor.populateBankSelectItem(bankVO).getLabel()));
	}

	@Override
	public boolean performAction(ActionEvent e) {
		if(basePanel.validatePanelData()) {
			BankPanelBean bankPanelBean = (BankPanelBean)basePanel.getPanelDataOnSubmit();
			FdVO fdVO = new FdVO();
			this.bankVO = bankPanelBean.getBankVO();
			fdVO.setBankVO(this.bankVO);
			FdListResult result = fdService.fetchByCriteria(fdVO);
			
			if(result.isSuccess()) {
				fdVOList = result.getFdVOList();
				return true;
			} else {
				setErrorList(result.getErrorList());
				return false;
			}
		}
		return false;
	}

}
