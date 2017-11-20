package com.slickcode.fdms.client.person.listner;

import java.awt.event.ActionEvent;
import java.util.List;

import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.client.person.page.ShowAllPersonPage;
import com.slickcode.fdms.client.utils.SpringContextLoader;
import com.slickcode.fdms.common.bean.PersonPanelBean;
import com.slickcode.fdms.common.vo.PersonVO;
import com.slickcode.fdms.service.IPersonService;
import com.slickcode.fdms.service.PersonServiceImpl;
import com.slickcode.fdms.service.serviceobject.PersonListResult;

public class SearchPersonActionListner extends FdmsActionListner {
	private BasePanel basePanel;
	private List<PersonVO> personVOList;
	private IPersonService personService;

	public SearchPersonActionListner(BasePanel basePanel) {
		this.basePanel = basePanel;
		this.personService = (PersonServiceImpl) SpringContextLoader
				.getInstance().loadContext().getBean("personServiceImpl");
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(new ShowAllPersonPage(personVOList));
	}

	@Override
	public boolean performAction(ActionEvent e) {
		if (basePanel.validatePanelData()) {
			PersonPanelBean personPanelBean = (PersonPanelBean) basePanel
					.getPanelDataOnSubmit();

			PersonListResult result = personService
					.searchByCriteria(personPanelBean.getPersonVO());

			if (result.isSuccess()) {
				personVOList = result.getPersonVOList();
				return true;
			} else {
				setErrorList(result.getErrorList());
				return false;
			}

		}
		return false;
	}

}
