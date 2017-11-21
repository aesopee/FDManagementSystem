package com.slickcode.fdms.client.home.page;

import java.util.List;

import javax.swing.JPanel;

import com.slickcode.baseframework.domain.IPanelBean;
import com.slickcode.fdms.client.page.FdmsBasePage;
import com.slickcode.fdms.common.vo.FdVO;

public class HomePage extends FdmsBasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6498166622458096985L;
	private HomePanel homePanel;
	private List<FdVO> currentFdVOList;
	private List<FdVO> maturedFdVOList;
	private List<FdVO> nextWeekFdVOList;
	private String currentFdsTitle;
	private String maturedFdVOsTitle;
	private String nextWeekFdVOsTitle;

	public HomePage(List<FdVO> currentFdVOList, List<FdVO> maturedFdVOList, List<FdVO> nextWeekFdVOList,
			String currentFdsTitle, String maturedFdVOsTitle, String nextWeekFdVOsTitle) {
		this.currentFdVOList = currentFdVOList;
		this.maturedFdVOList = maturedFdVOList;
		this.nextWeekFdVOList = nextWeekFdVOList;
		this.currentFdsTitle = currentFdsTitle;
		this.maturedFdVOsTitle = maturedFdVOsTitle;
		this.nextWeekFdVOsTitle = nextWeekFdVOsTitle;
		loadPanelData();
	}

	@Override
	public JPanel populateSideMenu() {
		return new JPanel();
	}

	@Override
	public void arrangeComponents() {
		return;
	}

	@Override
	public JPanel createPanel() {
		homePanel = new HomePanel();
		homePanel.createPanel();
		return homePanel;
	}

	@Override
	public void loadPanelData() {
		this.homePanel.setCurrentFdVOList(currentFdVOList);
		this.homePanel.setMaturedFdVOList(maturedFdVOList);
		this.homePanel.setNextWeekFdVOList(nextWeekFdVOList);
		this.homePanel.getShowCurrentFDsPanel().setTitle(currentFdsTitle);
		this.homePanel.getShowMaturedFDPanel().setTitle(maturedFdVOsTitle);
		this.homePanel.getShowFDsInNextWeekPanel().setTitle(nextWeekFdVOsTitle);
		this.homePanel.loadPanelData();
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
