package com.slickcode.fdms.client.page;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import com.slickcode.baseframework.constants.PageDimension;
import com.slickcode.baseframework.domain.IPanelBean;
import com.slickcode.baseframework.page.BaseHeaderPanel;
import com.slickcode.baseframework.utils.BaseUtils;
import com.slickcode.fdms.client.constants.ImageConstants;

public class HeaderPanel extends BaseHeaderPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient Image img;

	@Override
	public void applyRights() {
		return;
	}

	@Override
	public void arrangeComponents() {
		return;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

	@Override
	public JPanel createPanel() {
		img = BaseUtils.populateImage(ImageConstants.FIXED_DEPOSITS, PageDimension.getInstance().getHeaderWidth(),
				PageDimension.getInstance().getHeaderHeight()).getImage();
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
		return null;
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

	@Override
	public void prepareTabOutOrderList() {
		return;
	}

}
