package com.slickcode.fdms.client.person.listner;

import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.slickcode.baseframework.table.PaginatedTablePanel;
import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.client.person.page.PersonMainPage;
import com.slickcode.fdms.client.utils.ScreenMode;
import com.slickcode.fdms.client.utils.SpringContextLoader;
import com.slickcode.fdms.common.vo.PersonVO;
import com.slickcode.fdms.service.IPersonService;
import com.slickcode.fdms.service.PersonServiceImpl;
import com.slickcode.fdms.service.serviceobject.PersonResult;

public class ShowShowAllPersonActionListner extends FdmsActionListner {
	private PaginatedTablePanel panel;
	private PersonVO personVO;
	private IPersonService personService;

	public ShowShowAllPersonActionListner(PaginatedTablePanel panel) {
		this.panel = panel;
		this.personService = (PersonServiceImpl) SpringContextLoader
				.getInstance().loadContext().getBean("personServiceImpl");
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(
				new PersonMainPage(personVO, ScreenMode.VIEW));
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean performAction(ActionEvent e) {
		JTable table = panel.getTable();
		int itemsPerPage = panel.getItemsPerPage();
		int selectedIndexOfPage = panel.getSelectedIndexOfPage();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Vector<Object> rowDataVector = (Vector<Object>) model.getDataVector()
				.elementAt(
						((selectedIndexOfPage - 1) * itemsPerPage)
								+ table.getSelectedRow());

		Integer personId = (Integer) rowDataVector.get(0);
		personVO = new PersonVO();
		personVO.setPersonId(personId);
		PersonResult result = personService.fetchById(personVO);

		if (result.isSuccess()) {
			personVO = result.getPersonVO();
			return true;
		} else {
			setErrorList(result.getErrorList());
			return false;
		}
	}

}
