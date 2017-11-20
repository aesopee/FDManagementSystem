package com.slickcode.fdms.client.person.listner;

import java.awt.event.ActionEvent;
import java.util.List;

import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.fdms.client.constants.CommonConstants;
import com.slickcode.fdms.client.fd.page.ShowAllFdPage;
import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.common.bean.PersonPanelBean;
import com.slickcode.fdms.common.utils.SelectItemConvertor;
import com.slickcode.fdms.common.vo.FdVO;
import com.slickcode.fdms.common.vo.PersonVO;
import com.slickcode.fdms.service.FdServiceImpl;
import com.slickcode.fdms.service.IFdService;
import com.slickcode.fdms.service.serviceobject.FdListResult;

public class FetchPersonRelatedActionListner extends FdmsActionListner {

	private BasePanel basePanel;
	private List<FdVO> fdVOList;
	private IFdService fdService;
	private PersonVO personVO;

	public FetchPersonRelatedActionListner(BasePanel basePanel) {
		this.basePanel = basePanel;
		this.fdService = (FdServiceImpl) applicationContext
				.getBean("fdServiceImpl");
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(
				new ShowAllFdPage(fdVOList,
						CommonConstants.HEADER_FDS_RELATED_TO_PERSON
								+ SelectItemConvertor
										.populatePersonSelectItem(personVO)));
	}

	@Override
	public boolean performAction(ActionEvent e) {
		if (basePanel.validatePanelData()) {
			PersonPanelBean bankPanelBean = (PersonPanelBean) basePanel
					.getPanelDataOnSubmit();
			FdVO fdVO = new FdVO();
			this.personVO = bankPanelBean.getPersonVO();
			fdVO.setFirstOwnerVO(this.personVO);
			fdVO.setSecondOwnerVO(this.personVO);
			fdVO.setNomineeVO(this.personVO);
			FdListResult result = fdService.fetchByCriteria(fdVO);

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
