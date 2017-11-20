package com.slickcode.fdms.client.person.listner;

import java.awt.event.ActionEvent;

import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.client.person.page.PersonMainPage;
import com.slickcode.fdms.client.utils.ScreenMode;
import com.slickcode.fdms.client.utils.SpringContextLoader;
import com.slickcode.fdms.common.bean.PersonPanelBean;
import com.slickcode.fdms.common.vo.PersonVO;
import com.slickcode.fdms.service.IPersonService;
import com.slickcode.fdms.service.PersonServiceImpl;
import com.slickcode.fdms.service.serviceobject.PersonResult;

public class AddPersonActionListner extends FdmsActionListner {

	private BasePanel basePanel;
	private PersonVO personVO;
	private IPersonService personService;

	public AddPersonActionListner(BasePanel basePanel) {
		this.basePanel = basePanel;
		this.personService = (PersonServiceImpl) SpringContextLoader
				.getInstance().loadContext().getBean("personServiceImpl");
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(
				new PersonMainPage(personVO, ScreenMode.VIEW));
	}

	@Override
	public boolean performAction(ActionEvent e) {
		if (basePanel.validatePanelData()) {
			PersonPanelBean personPanelBean = (PersonPanelBean) basePanel
					.getPanelDataOnSubmit();
			PersonResult result = personService.create(personPanelBean
					.getPersonVO());
			if (result.isSuccess()) {
				showPopUp("New person added.", "Message");
				personVO = result.getPersonVO();
				return true;
			} else {
				setErrorList(result.getErrorList());
				return false;
			}

		}
		return false;
	}
}
