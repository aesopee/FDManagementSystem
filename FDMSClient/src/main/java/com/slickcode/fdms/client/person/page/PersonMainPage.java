package com.slickcode.fdms.client.person.page;

import javax.swing.JPanel;

import com.slickcode.baseframework.domain.IPanelBean;
import com.slickcode.fdms.client.page.FdmsBasePage;
import com.slickcode.fdms.client.utils.ScreenMode;
import com.slickcode.fdms.common.vo.PersonVO;

public class PersonMainPage extends FdmsBasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4170131650355391634L;
	private PersonMainPanel searchPersonPanel;
	private PersonVO personVO;
	private ScreenMode screenMode;

	public PersonMainPage(PersonVO personVO, ScreenMode screenMode) {
		this.personVO = personVO;
		this.screenMode = screenMode;
		loadPanelData();
	}

	@Override
	public void arrangeComponents() {
	}

	@Override
	public JPanel createPanel() {
		searchPersonPanel = new PersonMainPanel();
		searchPersonPanel.createPanel();
		return searchPersonPanel;
	}

	@Override
	public void loadPanelData() {
		searchPersonPanel.setPersonVO(personVO);
		searchPersonPanel.setScreenMode(screenMode);
		searchPersonPanel.loadPanelData();
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
	}

	@Override
	public void prepareTabOutOrderList() {
	}
}
