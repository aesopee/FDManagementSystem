package com.slickcode.fdms.client.person.page;

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
import com.slickcode.fdms.client.constants.CommonConstants;
import com.slickcode.fdms.client.constants.ImageConstants;
import com.slickcode.fdms.client.person.listner.EditPersonActionListner;
import com.slickcode.fdms.client.person.listner.ExcelExportPersonActionListner;
import com.slickcode.fdms.client.person.listner.ShowShowAllPersonActionListner;
import com.slickcode.fdms.client.utils.ButtonGridPanel;
import com.slickcode.fdms.common.vo.PersonVO;

public class ShowAllPersonPanel extends BasePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3855115435957689599L;

	private int columnWidth = 200;
	private int heightPadding = 10;
	private int widthPadding = 10;
	private int rowHeight = 20;

	private PaginatedTablePanel panel;
	private List<PersonVO> personVOList;

	private ButtonGridPanel buttonGridPanel;
	private BaseButton excelExportButton;

	private void populatePersonTable() {
		List<ColumnDataVO> columnDataVOList = new ArrayList<>();
		ColumnDataVO personIdColumnDataVO = new ColumnDataVO(CommonConstants.LABEL_PERSON_ID, 10,
				java.lang.Integer.class, false, AlignmentEnum.CENTER);
		columnDataVOList.add(personIdColumnDataVO);
		ColumnDataVO firstNameColumnDataVO = new ColumnDataVO(CommonConstants.LABEL_FIRST_NAME, 40,
				java.lang.String.class, false, AlignmentEnum.RIGHT);
		columnDataVOList.add(firstNameColumnDataVO);
		ColumnDataVO lastNameColumnDataVO = new ColumnDataVO(CommonConstants.LABEL_LAST_NAME, 40,
				java.lang.String.class, false, AlignmentEnum.RIGHT);
		columnDataVOList.add(lastNameColumnDataVO);
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
		BaseButton editButton = new BaseButton(editIcon, new EditPersonActionListner(panel),
				CommonConstants.BUTTON_EDIT);
		panel.getTable().getColumn(editButtonColumnDataVO.getHeader()).setCellRenderer(editButton);
		panel.getTable().getColumn(editButtonColumnDataVO.getHeader())
				.setCellEditor(new BaseButtonEditor(new JCheckBox(), editButton));

		ImageIcon viewIcon = BaseUtils.populateImage(ImageConstants.VIEW);
		BaseButton viewButton = new BaseButton(viewIcon, new ShowShowAllPersonActionListner(panel),
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
	public void arrangeComponents() {
		int fromLeft = baseDimension.getWidth();
		int fromTop = baseDimension.getHeight();

		/**
		 * Table
		 */
		fromLeft = widthPadding;
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
	}

	@Override
	public JPanel createPanel() {
		populatePersonTable();
		populateButtons();
		return this;
	}

	@Override
	public void loadPanelData() {
		panel.setData(populatePersonArray());
		panel.loadPanelData();

		setTitle(CommonConstants.HEADER_SHOW_PERSON);

		ImageIcon excelExportIcon = BaseUtils.populateImage(ImageConstants.EXCEL_EXPORT);
		excelExportButton.setAllValues(excelExportIcon, new ExcelExportPersonActionListner(personVOList),
				CommonConstants.BUTTON_EXCEL_EXPORT);
		arrangeComponents();
		prepareTabOutOrderList();
	}

	private Object[][] populatePersonArray() {
		int size = personVOList.size();
		Object[][] data = new Object[size][];
		for (int i = 0; i < size; i++) {
			PersonVO personVO = personVOList.get(i);
			Object[] innerData = { personVO.getPersonId(), personVO.getFirstName(), personVO.getLastName(), null,
					null };
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

	@Override
	public void prepareTabOutOrderList() {
		return;
	}

	/**
	 * @param personVOList
	 *            the personVOList to set
	 */
	public void setPersonVOList(List<PersonVO> personVOList) {
		this.personVOList = personVOList;
	}

}
