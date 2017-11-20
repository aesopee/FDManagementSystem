package com.slickcode.fdms.client.person.listner;

import java.awt.event.ActionEvent;
import java.util.List;

import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.client.person.page.ShowAllPersonPage;
import com.slickcode.fdms.client.utils.SpringContextLoader;
import com.slickcode.fdms.common.vo.PersonVO;
import com.slickcode.fdms.service.IPersonService;
import com.slickcode.fdms.service.PersonServiceImpl;
import com.slickcode.fdms.service.serviceobject.PersonListResult;

public class InitiateShowAllPersonActionListner extends FdmsActionListner {

	private List<PersonVO> personVOList;
	private IPersonService personService;

	public InitiateShowAllPersonActionListner() {
		this.personService = (PersonServiceImpl) SpringContextLoader
				.getInstance().loadContext().getBean("personServiceImpl");
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(new ShowAllPersonPage(personVOList));
	}

	@Override
	public boolean performAction(ActionEvent e) {
		PersonListResult result = personService.fetchByCriteria(null);

		if (result.isSuccess()) {
			personVOList = result.getPersonVOList();
			return true;
		} else {
			setErrorList(result.getErrorList());
			return false;
		}
	}

}
