package com.slickcode.fdms.client.person.page;

import java.util.List;

import javax.swing.JPanel;

import com.slickcode.baseframework.domain.IPanelBean;
import com.slickcode.fdms.client.page.FdmsBasePage;
import com.slickcode.fdms.common.vo.PersonVO;

public class ShowAllPersonPage extends FdmsBasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3510370658865013698L;

	private ShowAllPersonPanel showAllPersonPanel;
	private List<PersonVO> personVOList;

	public ShowAllPersonPage(List<PersonVO> personVOList) {
		this.personVOList = personVOList;
		loadPanelData();
	}

	@Override
	public void arrangeComponents() {
		return;
	}

	@Override
	public JPanel createPanel() {
		showAllPersonPanel = new ShowAllPersonPanel();
		showAllPersonPanel.createPanel();

		return showAllPersonPanel;
	}

	@Override
	public void loadPanelData() {
		this.showAllPersonPanel.setPersonVOList(personVOList);
		this.showAllPersonPanel.loadPanelData();
		prepareTabOutOrderList();
	}

	@Override
	public IPanelBean getPanelDataOnSubmit() {
		return null;
	}

	@Override
	public boolean validatePanelData() {
		return false;
	}

	@Override
	public void applyRights() {
		return;
	}

	@Override
	public void prepareTabOutOrderList() {
		return;
	}
}
