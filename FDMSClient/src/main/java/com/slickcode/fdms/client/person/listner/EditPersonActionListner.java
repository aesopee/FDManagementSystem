package com.slickcode.fdms.client.person.listner;

import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.baseframework.table.PaginatedTablePanel;
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

public class EditPersonActionListner extends FdmsActionListner {

	private PaginatedTablePanel paginatedTablePanel;
	private BasePanel basePanel;
	private PersonVO personVO;
	private IPersonService personService;

	public EditPersonActionListner(PaginatedTablePanel paginatedTablePanel) {
		this.paginatedTablePanel = paginatedTablePanel;
		this.personService = (PersonServiceImpl) SpringContextLoader
				.getInstance().loadContext().getBean("personServiceImpl");
	}

	public EditPersonActionListner(BasePanel basePanel) {
		this.basePanel = basePanel;
		this.personService = (PersonServiceImpl) SpringContextLoader
				.getInstance().loadContext().getBean("personServiceImpl");
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(
				new PersonMainPage(personVO, ScreenMode.EDIT));
	}

	@Override
	public boolean performAction(ActionEvent e) {
		if (populatePersonVO()) {
			PersonResult result = personService.fetchById(personVO);

			if (result.isSuccess()) {
				personVO = result.getPersonVO();
				return true;
			} else {
				setErrorList(result.getErrorList());
				return false;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private boolean populatePersonVO() {
		if (null != paginatedTablePanel) {
			JTable table = paginatedTablePanel.getTable();
			int itemsPerPage = paginatedTablePanel.getItemsPerPage();
			int selectedIndexOfPage = paginatedTablePanel
					.getSelectedIndexOfPage();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			Vector<Object> rowDataVector = (Vector<Object>) model
					.getDataVector().elementAt(
							((selectedIndexOfPage - 1) * itemsPerPage)
									+ table.getSelectedRow());

			Integer personId = (Integer) rowDataVector.get(0);

			personVO = new PersonVO();
			personVO.setPersonId(personId);
			return true;
		} else {
			if (basePanel.validatePanelData()) {
				PersonPanelBean personPanelBean = (PersonPanelBean) basePanel
						.getPanelDataOnSubmit();
				personVO = personPanelBean.getPersonVO();

				return true;
			}
		}
		return false;
	}

}
