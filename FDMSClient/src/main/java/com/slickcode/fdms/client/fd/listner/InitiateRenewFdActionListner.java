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

public class InitiateRenewFdActionListner extends FdmsActionListner {

	private BasePanel basePanel;
	private FdVO fdVO;
	private IFdService fdService;

	public InitiateRenewFdActionListner(BasePanel basePanel) {
		this.basePanel = basePanel;
		this.fdService = (FdServiceImpl) applicationContext
				.getBean("fdServiceImpl");
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance()
				.showPanel(new FdMainPage(fdVO, ScreenMode.RENEW));
	}

	@Override
	public boolean performAction(ActionEvent e) {
		if (basePanel.validatePanelData()) {
			FdPanelBean fdPanelBean = (FdPanelBean) basePanel
					.getPanelDataOnSubmit();
			FdResult result = fdService.fetchById(fdPanelBean.getFdVO());
			if (result.isSuccess()) {
				fdVO = result.getFdVO();
				if ((null == fdVO.getOriginalFdNumber())
						|| (0 == fdVO.getOriginalFdNumber())) {
					fdVO.setOriginalFdNumber(fdVO.getFdBankReferenceNumber());
				}

				fdVO.setRenewedFrom(fdVO.getFdBankReferenceNumber());
				fdVO.setInvestedAmount(fdVO.getMaturityAmount());
				fdVO.setInvestmentDate(fdVO.getMaturityDate());

				fdVO.setFdId(null);
				fdVO.setFdBankReferenceNumber(null);
				fdVO.setMaturityAmount(null);
				fdVO.setMaturityDate(null);
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
