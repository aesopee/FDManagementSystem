package com.slickcode.fdms.client.fd.listner;

import java.awt.event.ActionEvent;

import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.fdms.client.cache.FDMSCache;
import com.slickcode.fdms.client.fd.page.FdMainPage;
import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.client.utils.ScreenMode;
import com.slickcode.fdms.common.bean.FdPanelBean;
import com.slickcode.fdms.common.constant.FdStatusEnum;
import com.slickcode.fdms.common.vo.FdVO;
import com.slickcode.fdms.service.FdServiceImpl;
import com.slickcode.fdms.service.IFdService;
import com.slickcode.fdms.service.serviceobject.FdResult;

public class CopyFdActionListner extends FdmsActionListner {

	private BasePanel basePanel;
	private FdVO fdVO;
	private IFdService fdService;
	
	public CopyFdActionListner(BasePanel basePanel) {
		this.basePanel = basePanel;
		this.fdService = (FdServiceImpl)applicationContext.getBean("fdServiceImpl");
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(
				new FdMainPage(fdVO, ScreenMode.CREATE));
	}

	@Override
	public boolean performAction(ActionEvent e) {
		if (basePanel.validatePanelData()) {
			FdPanelBean personPanelBean = (FdPanelBean) basePanel
					.getPanelDataOnSubmit();
			FdResult result = fdService
					.fetchById(personPanelBean.getFdVO());

			if (result.isSuccess()) {
				fdVO = result.getFdVO();
				fdVO.setFdId(null);
				fdVO.setFdBankReferenceNumber(null);
				fdVO.setOriginalFdNumber(0);
				fdVO.setRenewedFrom(0);
				fdVO.setRenewedTo(0);
				fdVO.setStatusVO(FDMSCache.getInstance().getStatusVOByCode(FdStatusEnum.CURRENT.getCode()));
				return true;
			} else {
				setErrorList(result.getErrorList());
				return false;
			}
		}
		return false;
	}

}
