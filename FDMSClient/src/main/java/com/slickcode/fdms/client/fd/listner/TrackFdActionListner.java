package com.slickcode.fdms.client.fd.listner;

import java.awt.event.ActionEvent;
import java.util.List;

import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.fdms.client.fd.page.ShowAllFdPage;
import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.common.bean.FdPanelBean;
import com.slickcode.fdms.common.vo.FdVO;
import com.slickcode.fdms.service.FdServiceImpl;
import com.slickcode.fdms.service.IFdService;
import com.slickcode.fdms.service.serviceobject.FdListResult;

public class TrackFdActionListner extends FdmsActionListner {

	private BasePanel basePanel;
	private List<FdVO> fdVOList;
	private IFdService fdService;
	public TrackFdActionListner(BasePanel basePanel) {
		this.basePanel = basePanel;
		this.fdService = (FdServiceImpl)applicationContext.getBean("fdServiceImpl");
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(new ShowAllFdPage(fdVOList, "Track FD"));
	}

	@Override
	public boolean performAction(ActionEvent e) {
		if (basePanel.validatePanelData()) {
			FdPanelBean fdPanelBean = (FdPanelBean) basePanel
					.getPanelDataOnSubmit();
			FdListResult result = fdService.track(fdPanelBean.getFdVO());
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
