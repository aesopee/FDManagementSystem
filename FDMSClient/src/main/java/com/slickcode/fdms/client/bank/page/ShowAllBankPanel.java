package com.slickcode.fdms.client.bank.page;

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
import com.slickcode.fdms.client.bank.listner.EditBankActionListner;
import com.slickcode.fdms.client.bank.listner.ExcelExportBankActionListner;
import com.slickcode.fdms.client.bank.listner.ShowShowAllBankActionListner;
import com.slickcode.fdms.client.constants.ButtonConstants;
import com.slickcode.fdms.client.constants.HeaderConstants;
import com.slickcode.fdms.client.constants.ImageConstants;
import com.slickcode.fdms.client.constants.LabelConstants;
import com.slickcode.fdms.client.utils.ButtonGridPanel;
import com.slickcode.fdms.common.vo.BankVO;

public class ShowAllBankPanel extends BasePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3855115435957689599L;

	private int columnWidth = 400;
	private int heightPadding = 10;
	private int widthPadding = 10;
	private int rowHeight = 20;

	private PaginatedTablePanel panel;

	private ButtonGridPanel buttonGridPanel;
	private BaseButton excelExportButton;

	private List<BankVO> bankVOList;

	private void populateBankTable() {
		List<ColumnDataVO> columnDataVOList = new ArrayList<>();
		ColumnDataVO bankIdColumnDataVO = new ColumnDataVO(LabelConstants.BANK_ID, 10, java.lang.String.class,
				false, AlignmentEnum.CENTER);
		columnDataVOList.add(bankIdColumnDataVO);
		ColumnDataVO bankNameColumnDataVO = new ColumnDataVO(LabelConstants.BANK_NAME, 50,
				java.lang.String.class, false, AlignmentEnum.RIGHT);
		columnDataVOList.add(bankNameColumnDataVO);
		ColumnDataVO branchColumnDataVO = new ColumnDataVO(LabelConstants.BRANCH, 30, java.lang.String.class,
				false, AlignmentEnum.RIGHT);
		columnDataVOList.add(branchColumnDataVO);
		ColumnDataVO editButtonColumnDataVO = new ColumnDataVO(ButtonConstants.EDIT, 5,
				javax.swing.ImageIcon.class, true);
		columnDataVOList.add(editButtonColumnDataVO);
		ColumnDataVO viewButtonColumnDataVO = new ColumnDataVO(ButtonConstants.VIEW, 5,
				javax.swing.ImageIcon.class, true);
		columnDataVOList.add(viewButtonColumnDataVO);

		panel = new PaginatedTablePanel(columnDataVOList, 10,
				PageDimension.getInstance().getMainPanelWidth() - 30 - widthPadding - 50, 200);

		columnWidth = BaseUtils.getMax(columnWidth, panel.getPreferredSize().getWidth());
		add(panel.createPanel());

		ImageIcon editIcon = BaseUtils.populateImage(ImageConstants.EDIT);
		BaseButton editButton = new BaseButton(editIcon, new EditBankActionListner(panel), ButtonConstants.EDIT);
		panel.getTable().getColumn(editButtonColumnDataVO.getHeader()).setCellRenderer(editButton);
		panel.getTable().getColumn(editButtonColumnDataVO.getHeader())
				.setCellEditor(new BaseButtonEditor(new JCheckBox(), editButton));

		ImageIcon viewIcon = BaseUtils.populateImage(ImageConstants.VIEW);
		BaseButton viewButton = new BaseButton(viewIcon, new ShowShowAllBankActionListner(panel),
				ButtonConstants.VIEW);
		panel.getTable().getColumn(viewButtonColumnDataVO.getHeader()).setCellRenderer(viewButton);
		panel.getTable().getColumn(viewButtonColumnDataVO.getHeader())
				.setCellEditor(new BaseButtonEditor(new JCheckBox(), viewButton));

	}

	private void populateButtons() {
		buttonGridPanel = new ButtonGridPanel(heightPadding, widthPadding, rowHeight);

		ImageIcon excelExportIcon = BaseUtils.populateImage(ImageConstants.EXCEL_EXPORT);
		this.excelExportButton = new BaseButton(excelExportIcon, null, ButtonConstants.EXCEL_EXPORT);
		this.columnWidth = BaseUtils.getMax(this.columnWidth, this.excelExportButton.getPreferredSize().getWidth());
		buttonGridPanel.addButtonToList(this.excelExportButton);

		add(buttonGridPanel.createPanel());
	}

	@Override
	public void arrangeComponents() {
		/**
		 * Table
		 */
		int fromLeft = widthPadding;
		int fromTop = baseDimension.getHeight() + heightPadding;
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
	}

	@Override
	public JPanel createPanel() {
		populateBankTable();
		populateButtons();
		return this;
	}

	@Override
	public void loadPanelData() {
		panel.setData(populateBankArray());
		panel.loadPanelData();
		columnWidth = BaseUtils.getMax(columnWidth, panel.getPreferredSize().getWidth());

		setTitle(HeaderConstants.SHOW_BANK);
		ImageIcon excelExportIcon = BaseUtils.populateImage(ImageConstants.EXCEL_EXPORT);
		excelExportButton.setAllValues(excelExportIcon, new ExcelExportBankActionListner(bankVOList),
				ButtonConstants.EXCEL_EXPORT);
		arrangeComponents();
		prepareTabOutOrderList();
	}

	private Object[][] populateBankArray() {
		int size = bankVOList.size();
		Object[][] data = new Object[size][];
		for (int i = 0; i < size; i++) {
			BankVO bankVO = bankVOList.get(i);
			Object[] innerData = { bankVO.getBankId(), bankVO.getName(), bankVO.getBranch(), null, null };
			data[i] = innerData;
		}
		return data;
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

	/**
	 * @return the bankVOList
	 */
	public List<BankVO> getBankVOList() {
		return bankVOList;
	}

	/**
	 * @param bankVOList
	 *            the bankVOList to set
	 */
	public void setBankVOList(List<BankVO> bankVOList) {
		this.bankVOList = bankVOList;
	}

	@Override
	public void prepareTabOutOrderList() {
		return;
	}

}
