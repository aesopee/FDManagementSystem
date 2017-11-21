package com.slickcode.fdms.client.fd.page;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import com.slickcode.baseframework.components.BaseButton;
import com.slickcode.baseframework.components.BaseButtonEditor;
import com.slickcode.baseframework.constants.PageDimension;
import com.slickcode.baseframework.domain.ColumnDataVO;
import com.slickcode.baseframework.domain.IPanelBean;
import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.baseframework.table.PaginatedTablePanel;
import com.slickcode.baseframework.utils.AlignmentEnum;
import com.slickcode.baseframework.utils.BaseUtils;
import com.slickcode.basevalidatorframework.DateUtilities;
import com.slickcode.fdms.client.constants.CommonConstants;
import com.slickcode.fdms.client.constants.ImageConstants;
import com.slickcode.fdms.client.fd.listner.EditFdActionListner;
import com.slickcode.fdms.client.fd.listner.ExcelExportFdActionListner;
import com.slickcode.fdms.client.fd.listner.ShowShowAllFdActionListner;
import com.slickcode.fdms.client.utils.ButtonGridPanel;
import com.slickcode.fdms.common.vo.FdVO;

public class ShowAllFDPanel extends BasePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3855115435957689599L;

	private int columnWidth = 200;
	private int heightPadding = 20;
	private int widthPadding = 10;
	private int rowHeight = 20;

	private PaginatedTablePanel panel;
	private List<FdVO> fdVOList;

	private ButtonGridPanel buttonGridPanel;
	private BaseButton excelExportButton;

	private void populateFDTable() {
		List<ColumnDataVO> columnDataVOList = new ArrayList<ColumnDataVO>();
		ColumnDataVO fdIdColumnDataVO = new ColumnDataVO(CommonConstants.LABEL_FD_ID, 5, java.lang.Integer.class, false,
				AlignmentEnum.CENTER);
		columnDataVOList.add(fdIdColumnDataVO);
		ColumnDataVO fdNumberColumnDataVO = new ColumnDataVO(CommonConstants.LABEL_FD_NUMBER, 10,
				java.lang.String.class, false, AlignmentEnum.CENTER);
		columnDataVOList.add(fdNumberColumnDataVO);
		ColumnDataVO bankNameColumnDataVO = new ColumnDataVO(CommonConstants.LABEL_BANK_NAME, 35,
				java.lang.String.class, false, AlignmentEnum.RIGHT);
		columnDataVOList.add(bankNameColumnDataVO);
		ColumnDataVO branchColumnDataVO = new ColumnDataVO(CommonConstants.LABEL_BRANCH, 20, java.lang.String.class,
				false, AlignmentEnum.RIGHT);
		columnDataVOList.add(branchColumnDataVO);
		ColumnDataVO maturityDateColumnDataVO = new ColumnDataVO(CommonConstants.LABEL_MATURITY_DATE, 10,
				java.lang.String.class, false, AlignmentEnum.RIGHT);
		columnDataVOList.add(maturityDateColumnDataVO);
		ColumnDataVO statusColumnDataVO = new ColumnDataVO(CommonConstants.LABEL_STATUS, 10, java.lang.String.class,
				false, AlignmentEnum.RIGHT);
		columnDataVOList.add(statusColumnDataVO);
		ColumnDataVO editButtonColumnDataVO = new ColumnDataVO(CommonConstants.BUTTON_EDIT, 5,
				javax.swing.ImageIcon.class, true);
		columnDataVOList.add(editButtonColumnDataVO);
		ColumnDataVO viewButtonColumnDataVO = new ColumnDataVO(CommonConstants.BUTTON_VIEW, 5,
				javax.swing.ImageIcon.class, true);
		columnDataVOList.add(viewButtonColumnDataVO);

		panel = new PaginatedTablePanel(columnDataVOList, 10,
				PageDimension.getInstance().getMainPanelWidth() - 30 - widthPadding - 50, 200);

		columnWidth = BaseUtils.getMax(columnWidth, panel.getPreferredSize().getWidth());
		add(panel.createPanel());

		ImageIcon editIcon = BaseUtils.populateImage(ImageConstants.EDIT);
		BaseButton editButton = new BaseButton(editIcon, new EditFdActionListner(panel), CommonConstants.BUTTON_EDIT);
		panel.getTable().getColumn(editButtonColumnDataVO.getHeader()).setCellRenderer(editButton);
		panel.getTable().getColumn(editButtonColumnDataVO.getHeader())
				.setCellEditor(new BaseButtonEditor(new JCheckBox(), editButton));

		ImageIcon viewIcon = BaseUtils.populateImage(ImageConstants.VIEW);
		BaseButton viewButton = new BaseButton(viewIcon, new ShowShowAllFdActionListner(panel),
				CommonConstants.BUTTON_VIEW);
		panel.getTable().getColumn(viewButtonColumnDataVO.getHeader()).setCellRenderer(viewButton);
		panel.getTable().getColumn(viewButtonColumnDataVO.getHeader())
				.setCellEditor(new BaseButtonEditor(new JCheckBox(), viewButton));
	}

	private void populateButtons() {
		buttonGridPanel = new ButtonGridPanel(heightPadding, widthPadding, rowHeight);

		ImageIcon excelExportIcon = BaseUtils.populateImage(ImageConstants.EXCEL_EXPORT);
		this.excelExportButton = new BaseButton(excelExportIcon, null, CommonConstants.BUTTON_EXCEL_EXPORT);
		this.columnWidth = BaseUtils.getMax(this.columnWidth, this.excelExportButton.getPreferredSize().getWidth());
		buttonGridPanel.addButtonToList(this.excelExportButton);

		add(buttonGridPanel.createPanel());
	}

	@Override
	public void loadPanelData() {
		panel.setData(populateFDArray());
		panel.loadPanelData();

		ImageIcon excelExportIcon = BaseUtils.populateImage(ImageConstants.EXCEL_EXPORT);
		excelExportButton.setAllValues(excelExportIcon, new ExcelExportFdActionListner(fdVOList),
				CommonConstants.BUTTON_EXCEL_EXPORT);
		arrangeComponents();
		prepareTabOutOrderList();
	}

	private Object[][] populateFDArray() {

		int size = fdVOList.size();
		Object[][] data = new Object[size][];

		for (int i = 0; i < size; i++) {
			FdVO fdVO = fdVOList.get(i);
			Object[] innerData = { fdVO.getFdId(), fdVO.getFdNumber(), fdVO.getBankVO().getName(),
					fdVO.getBankVO().getBranch(),
					DateUtilities.parseDateToString(fdVO.getMaturityDate(), "dd-MMM-YYYY"),
					fdVO.getStatusVO().getNarrative(), null, null };
			data[i] = innerData;
		}
		return data;
	}

	@Override
	public void arrangeComponents() {
		int fromLeft = baseDimension.getWidth();
		int fromTop = baseDimension.getHeight();

		/**
		 * Table
		 */
		fromLeft = widthPadding * 3;
		fromTop = baseDimension.getHeight() + heightPadding;
		fromLeft = fromLeft + widthPadding;

		BaseUtils.setBound(panel, fromLeft, fromTop, columnWidth, panel.getPreferredSize().height,
				panel.getPreferredSize().getWidth(), 0, baseDimension);

		/**
		 * Button Row
		 */
		buttonGridPanel.arrangeComponents();
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(buttonGridPanel, fromLeft, fromTop,
				BaseUtils.getMax(0, buttonGridPanel.getPreferredSize().getWidth()), rowHeight,
				buttonGridPanel.getPreferredSize().getWidth(), buttonGridPanel.getPreferredSize().getHeight(),
				baseDimension);

		baseDimension.setHeight(baseDimension.getHeight() + heightPadding + 20);
		baseDimension.setWidth(baseDimension.getWidth() + widthPadding);

	}

	@Override
	public JPanel createPanel() {
		populateFDTable();
		populateButtons();
		return this;
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
	 * @return the fdVOList
	 */
	public List<FdVO> getFdVOList() {
		return fdVOList;
	}

	/**
	 * @param fdVOList
	 *            the fdVOList to set
	 */
	public void setFdVOList(List<FdVO> fdVOList) {
		this.fdVOList = fdVOList;
	}

	@Override
	public void prepareTabOutOrderList() {
	}
}
