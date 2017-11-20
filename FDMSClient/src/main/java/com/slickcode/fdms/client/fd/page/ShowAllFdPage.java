package com.slickcode.fdms.client.fd.page;

import java.util.List;

import javax.swing.JPanel;

import com.slickcode.baseframework.domain.IPanelBean;
import com.slickcode.fdms.client.page.FdmsBasePage;
import com.slickcode.fdms.common.vo.FdVO;

public class ShowAllFdPage extends FdmsBasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3510370658865013698L;
	private ShowAllFDPanel showAllFDPanel;
	private List<FdVO> fdVOList;
	private String title;

	public ShowAllFdPage(List<FdVO> fdVOList, String title) {
		this.fdVOList = fdVOList;
		this.title = title;
		loadPanelData();
	}

	@Override
	public void arrangeComponents() {
	}

	@Override
	public JPanel createPanel() {
		showAllFDPanel = new ShowAllFDPanel();
		showAllFDPanel.createPanel();
		return showAllFDPanel;
	}

	@Override
	public void loadPanelData() {
		this.showAllFDPanel.setFdVOList(fdVOList);
		this.showAllFDPanel.setTitle(title);
		this.showAllFDPanel.loadPanelData();
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
