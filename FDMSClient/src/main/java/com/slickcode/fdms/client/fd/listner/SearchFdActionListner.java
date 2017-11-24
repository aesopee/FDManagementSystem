package com.slickcode.fdms.client.fd.listner;

import java.awt.event.ActionEvent;
import java.util.List;

import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.fdms.client.constants.HeaderConstants;
import com.slickcode.fdms.client.fd.page.ShowAllFdPage;
import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.common.bean.FdPanelBean;
import com.slickcode.fdms.common.vo.FdVO;
import com.slickcode.fdms.service.FdServiceImpl;
import com.slickcode.fdms.service.IFdService;
import com.slickcode.fdms.service.serviceobject.FdListResult;

public class SearchFdActionListner extends FdmsActionListner {

	private List<FdVO> fdVOList;
	private BasePanel basePanel;
	private IFdService fdService;
	
	public SearchFdActionListner(BasePanel basePanel) {
		this.basePanel = basePanel;
		this.fdService = (FdServiceImpl)applicationContext.getBean("fdServiceImpl");
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(new ShowAllFdPage(fdVOList, HeaderConstants.SHOW_FD_SEARCH_RESULT));
	}

	@Override
	public boolean performAction(ActionEvent e) {
		if (basePanel.validatePanelData()) {
			FdPanelBean fdPanelBean = (FdPanelBean) basePanel
					.getPanelDataOnSubmit();

			FdListResult result = fdService.searchByCriteria(fdPanelBean
					.getFdVO());

			if (result.isSuccess()) {
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
