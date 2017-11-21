package com.slickcode.fdms.client.fd.page;

import javax.swing.JPanel;

import com.slickcode.baseframework.domain.IPanelBean;
import com.slickcode.fdms.client.page.FdmsBasePage;
import com.slickcode.fdms.client.utils.ScreenMode;
import com.slickcode.fdms.common.vo.FdVO;

public class FdMainPage extends FdmsBasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5711041324834737768L;

	private FdMainPanel fdMainPanel;
	private FdVO fdVO;
	private ScreenMode screenMode;

	public FdMainPage(FdVO fdVO, ScreenMode screenMode) {
		this.fdVO = fdVO;
		this.screenMode = screenMode;
		loadPanelData();
	}

	@Override
	public void arrangeComponents() {
		return;
	}

	@Override
	public JPanel createPanel() {
		fdMainPanel = new FdMainPanel();
		fdMainPanel.createPanel();
		return fdMainPanel;
	}

	@Override
	public void loadPanelData() {
		this.fdMainPanel.setFdVO(fdVO);
		this.fdMainPanel.setScreenMode(screenMode);
		this.fdMainPanel.loadPanelData();
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
