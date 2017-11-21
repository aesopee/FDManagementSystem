package com.slickcode.fdms.client.home.page;

import java.util.List;

import javax.swing.JPanel;

import com.slickcode.baseframework.domain.IPanelBean;
import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.baseframework.utils.BaseUtils;
import com.slickcode.fdms.client.fd.page.ShowAllFDPanel;
import com.slickcode.fdms.common.vo.FdVO;

public class HomePanel extends BasePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 140002980268121131L;
	private int widthPadding = 10;
	private int heightPadding = 10;
	private ShowAllFDPanel showMaturedFDPanel;
	private ShowAllFDPanel showFDsInNextWeekPanel;
	private ShowAllFDPanel showCurrentFDsPanel;

	private List<FdVO> currentFdVOList;
	private List<FdVO> maturedFdVOList;
	private List<FdVO> nextWeekFdVOList;

	@Override
	public void arrangeComponents() {
		int fromLeft = baseDimension.getWidth();
		int fromTop = baseDimension.getHeight();

		/**
		 * showMaturedFDPanel
		 */
		fromLeft = fromLeft + widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(showMaturedFDPanel, fromLeft, fromTop,
				BaseUtils.getMax(0, showMaturedFDPanel.getPreferredSize().getWidth()),
				BaseUtils.getMax(0, showMaturedFDPanel.getPreferredSize().getHeight()),
				showMaturedFDPanel.getPreferredSize().getWidth(), showMaturedFDPanel.getPreferredSize().getHeight(),
				baseDimension);

		/**
		 * showFDsInNextWeekPanel
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(showFDsInNextWeekPanel, fromLeft, fromTop,
				BaseUtils.getMax(0, showFDsInNextWeekPanel.getPreferredSize().getWidth()),
				BaseUtils.getMax(0, showFDsInNextWeekPanel.getPreferredSize().getHeight()),
				showFDsInNextWeekPanel.getPreferredSize().getWidth(),
				showFDsInNextWeekPanel.getPreferredSize().getHeight(), baseDimension);

		/**
		 * showCurrentFDsPanel
		 */
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(showCurrentFDsPanel, fromLeft, fromTop,
				BaseUtils.getMax(0, showCurrentFDsPanel.getPreferredSize().getWidth()),
				BaseUtils.getMax(0, showCurrentFDsPanel.getPreferredSize().getHeight()),
				showCurrentFDsPanel.getPreferredSize().getWidth(), showCurrentFDsPanel.getPreferredSize().getHeight(),
				baseDimension);

	}

	@Override
	public JPanel createPanel() {
		showMaturedFDPanel = new ShowAllFDPanel();
		showMaturedFDPanel.createPanel();
		add(showMaturedFDPanel);

		showFDsInNextWeekPanel = new ShowAllFDPanel();
		showFDsInNextWeekPanel.createPanel();
		add(showFDsInNextWeekPanel);

		showCurrentFDsPanel = new ShowAllFDPanel();
		showCurrentFDsPanel.createPanel();
		add(showCurrentFDsPanel);
		return this;
	}

	@Override
	public void loadPanelData() {
		this.showMaturedFDPanel.setFdVOList(maturedFdVOList);
		this.showMaturedFDPanel.loadPanelData();

		this.showFDsInNextWeekPanel.setFdVOList(nextWeekFdVOList);
		this.showFDsInNextWeekPanel.loadPanelData();

		this.showCurrentFDsPanel.setFdVOList(currentFdVOList);
		this.showCurrentFDsPanel.loadPanelData();
		arrangeComponents();
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

	/**
	 * @return the currentFdVOList
	 */
	public List<FdVO> getCurrentFdVOList() {
		return currentFdVOList;
	}

	/**
	 * @param currentFdVOList
	 *            the currentFdVOList to set
	 */
	public void setCurrentFdVOList(List<FdVO> currentFdVOList) {
		this.currentFdVOList = currentFdVOList;
	}

	/**
	 * @return the maturedFdVOList
	 */
	public List<FdVO> getMaturedFdVOList() {
		return maturedFdVOList;
	}

	/**
	 * @param maturedFdVOList
	 *            the maturedFdVOList to set
	 */
	public void setMaturedFdVOList(List<FdVO> maturedFdVOList) {
		this.maturedFdVOList = maturedFdVOList;
	}

	/**
	 * @return the nextWeekFdVOList
	 */
	public List<FdVO> getNextWeekFdVOList() {
		return nextWeekFdVOList;
	}

	/**
	 * @param nextWeekFdVOList
	 *            the nextWeekFdVOList to set
	 */
	public void setNextWeekFdVOList(List<FdVO> nextWeekFdVOList) {
		this.nextWeekFdVOList = nextWeekFdVOList;
	}

	@Override
	public void prepareTabOutOrderList() {
	}

	/**
	 * @return the showMaturedFDPanel
	 */
	public ShowAllFDPanel getShowMaturedFDPanel() {
		return showMaturedFDPanel;
	}

	/**
	 * @return the showFDsInNextWeekPanel
	 */
	public ShowAllFDPanel getShowFDsInNextWeekPanel() {
		return showFDsInNextWeekPanel;
	}

	/**
	 * @return the showCurrentFDsPanel
	 */
	public ShowAllFDPanel getShowCurrentFDsPanel() {
		return showCurrentFDsPanel;
	}

}
