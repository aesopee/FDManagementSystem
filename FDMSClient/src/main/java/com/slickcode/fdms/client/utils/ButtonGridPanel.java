package com.slickcode.fdms.client.utils;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.slickcode.baseframework.components.BaseButton;
import com.slickcode.baseframework.domain.IPanelBean;
import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.baseframework.utils.BaseUtils;

public class ButtonGridPanel extends BasePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<BaseButton> baseButtonList;
	private int rowHeight = 20;
	private int heightPadding = 20;
	private int widthPadding = 20;

	public ButtonGridPanel(int heightPadding, int widthPadding, int rowHeight) {
		this.heightPadding = heightPadding;
		this.widthPadding = widthPadding;
		this.rowHeight = rowHeight;
	}

	public List<BaseButton> getBaseButtonList() {
		if (null == baseButtonList) {
			baseButtonList = new ArrayList<>();
		}
		return baseButtonList;
	}

	public void setBaseButtonList(List<BaseButton> baseButtonList) {
		this.baseButtonList = baseButtonList;
	}

	public void addButtonToList(BaseButton baseButton) {
		getBaseButtonList().add(baseButton);
		rowHeight = BaseUtils.getMax(rowHeight,
				null == baseButton.getIcon() ? baseButton.getHeight() : baseButton.getIcon().getIconHeight());
		add(baseButton);
	}

	@Override
	public JPanel createPanel() {
		return this;
	}

	@Override
	public void applyRights() {
		return;
	}

	@Override
	public void arrangeComponents() {
		if ((null == baseButtonList) || baseButtonList.isEmpty()) {
			return;
		}
		int fromLeft = baseDimension.getWidth();
		int fromTop = baseDimension.getHeight();

		fromTop = baseDimension.getHeight() + heightPadding;
		for (BaseButton baseButton : baseButtonList) {
			fromLeft = baseDimension.getWidth() + widthPadding;

			BaseUtils.setBound(baseButton, fromLeft, fromTop,
					BaseUtils.getMax(0, baseButton.getPreferredSize().getWidth()), rowHeight,
					baseButton.getPreferredSize().getWidth(), 0, baseDimension);
		}
	}

	@Override
	public IPanelBean getPanelDataOnSubmit() {
		return null;
	}

	@Override
	public void loadPanelData() {
		prepareTabOutOrderList();
	}

	@Override
	public boolean validatePanelData() {
		return false;
	}

	/**
	 * @param rowHeight
	 *            the rowHeight to set
	 */
	public void setRowHeight(int rowHeight) {
		this.rowHeight = rowHeight;
	}

	@Override
	public void prepareTabOutOrderList() {
		return;
	}
}
