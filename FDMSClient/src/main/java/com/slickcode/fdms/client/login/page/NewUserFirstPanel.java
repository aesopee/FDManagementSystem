package com.slickcode.fdms.client.login.page;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.slickcode.baseframework.components.BaseButton;
import com.slickcode.baseframework.components.BaseLabel;
import com.slickcode.baseframework.components.BaseTextField;
import com.slickcode.baseframework.domain.IPanelBean;
import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.baseframework.utils.BaseUtils;
import com.slickcode.baseframework.utils.ComponentEnum;
import com.slickcode.basevalidatorframework.NonEmtryValidator;
import com.slickcode.basevalidatorframework.NumericValidator;
import com.slickcode.fdms.client.constants.CommonConstants;
import com.slickcode.fdms.client.constants.HeaderConstants;
import com.slickcode.fdms.client.constants.ImageConstants;
import com.slickcode.fdms.client.constants.LabelConstants;
import com.slickcode.fdms.client.login.listner.GoToLoginPageActionListner;
import com.slickcode.fdms.client.login.listner.GoToNewUserPageSecondActionListner;
import com.slickcode.fdms.client.utils.ButtonGridPanel;
import com.slickcode.fdms.common.bean.PersonPanelBean;
import com.slickcode.fdms.common.vo.PersonVO;

public class NewUserFirstPanel extends BasePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1909844385209542809L;

	private int firstColumnWidth = 0;
	private int secondColumnWidth = 200;
	private int thirdColumnWidth = 0;
	private int rowHeight = 20;
	private int heightPadding = 10;
	private int widthPadding = 10;

	private BaseLabel personIdLabel;
	private BaseTextField personIdField;
	private BaseLabel personIdLabelError;
	private transient NumericValidator personIdValidator;

	private BaseLabel firstNameLabel;
	private BaseTextField firstNameField;
	private BaseLabel firstNameLabelError;
	private transient NonEmtryValidator firstNameValidator;

	private BaseLabel lastNameLabel;
	private BaseTextField lastNameField;
	private BaseLabel lastNameLabelError;
	private transient NonEmtryValidator lastNameValidator;

	private BaseButton nextButton;
	private BaseButton cancelButton;
	private ButtonGridPanel buttonGridPanel;

	private void populatePersonId() {
		personIdLabel = new BaseLabel(LabelConstants.LABEL_PERSON_ID,
				ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, personIdLabel
				.getPreferredSize().getWidth());
		personIdLabel.setLabelFor(personIdField);
		add(personIdLabel);

		personIdField = new BaseTextField(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, personIdField
				.getPreferredSize().getWidth());
		add(personIdField);

		personIdLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);
		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth,
				personIdLabelError.getPreferredSize().getWidth());
		add(personIdLabelError);

		personIdValidator = new NumericValidator(personIdLabel.getText(),
				personIdField, personIdLabelError, true);
		personIdField.getDocument().addDocumentListener(personIdValidator);
	}

	private void populateFirstName() {
		firstNameLabel = new BaseLabel(LabelConstants.LABEL_FIRST_NAME,
				ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, firstNameLabel
				.getPreferredSize().getWidth());
		firstNameLabel.setLabelFor(firstNameField);
		add(firstNameLabel);

		firstNameField = new BaseTextField(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, firstNameField
				.getPreferredSize().getWidth());
		add(firstNameField);

		firstNameLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);
		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth,
				firstNameLabelError.getPreferredSize().getWidth());
		add(firstNameLabelError);

		firstNameValidator = new NonEmtryValidator(
				LabelConstants.LABEL_FIRST_NAME, firstNameField,
				firstNameLabelError, true);
		firstNameField.getDocument().addDocumentListener(firstNameValidator);
	}

	private void populateLastName() {
		lastNameLabel = new BaseLabel(LabelConstants.LABEL_LAST_NAME,
				ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, lastNameLabel
				.getPreferredSize().getWidth());
		lastNameLabel.setLabelFor(lastNameField);
		add(lastNameLabel);

		lastNameField = new BaseTextField(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, lastNameField
				.getPreferredSize().getWidth());
		add(lastNameField);

		lastNameLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);
		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth,
				lastNameLabelError.getPreferredSize().getWidth());
		add(lastNameLabelError);

		lastNameValidator = new NonEmtryValidator(
				LabelConstants.LABEL_LAST_NAME, lastNameField,
				lastNameLabelError, true);
		lastNameField.getDocument().addDocumentListener(lastNameValidator);
	}

	private void addButtonGrid() {
		buttonGridPanel = new ButtonGridPanel(heightPadding, widthPadding,
				rowHeight);
		ImageIcon cancelIcon = BaseUtils.populateImage(
				ImageConstants.CANCEL);
		cancelButton = new BaseButton(cancelIcon,
				new GoToLoginPageActionListner(), CommonConstants.BUTTON_CANCEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, cancelButton
				.getPreferredSize().getWidth());
		buttonGridPanel.addButtonToList(cancelButton);

		ImageIcon nextIcon = BaseUtils.populateImage(ImageConstants.NEXT);
		nextButton = new BaseButton(nextIcon,
				new GoToNewUserPageSecondActionListner(this),
				CommonConstants.BUTTON_NEXT);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, nextButton
				.getPreferredSize().getWidth());
		buttonGridPanel.addButtonToList(nextButton);
		add(buttonGridPanel.createPanel());
	}

	@Override
	public void arrangeComponents() {
		int fromLeft = baseDimension.getWidth();
		int fromTop = baseDimension.getHeight();

		/**
		 * Person Id Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(personIdLabel, fromLeft, fromTop, firstColumnWidth,
				rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(personIdField, fromLeft, fromTop, secondColumnWidth,
				rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + secondColumnWidth + widthPadding;
		BaseUtils.setBound(personIdLabelError, fromLeft, fromTop,
				thirdColumnWidth, rowHeight, 0, 0, baseDimension);

		/**
		 * First Name Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(firstNameLabel, fromLeft, fromTop, firstColumnWidth,
				rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(firstNameField, fromLeft, fromTop,
				secondColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + secondColumnWidth + widthPadding;
		BaseUtils.setBound(firstNameLabelError, fromLeft, fromTop,
				thirdColumnWidth, rowHeight, 0, 0, baseDimension);

		/**
		 * Last Name Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(lastNameLabel, fromLeft, fromTop, firstColumnWidth,
				rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(lastNameField, fromLeft, fromTop, secondColumnWidth,
				rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + secondColumnWidth + widthPadding;
		BaseUtils.setBound(lastNameLabelError, fromLeft, fromTop,
				thirdColumnWidth, rowHeight, 0, 0, baseDimension);

		/**
		 * Button Row
		 */
		buttonGridPanel.arrangeComponents();
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(buttonGridPanel, fromLeft, fromTop, BaseUtils
				.getMax(0, buttonGridPanel.getPreferredSize().getWidth()),
				rowHeight, buttonGridPanel.getPreferredSize().getWidth(),
				buttonGridPanel.getPreferredSize().getHeight(), baseDimension);
	}

	@Override
	public JPanel createPanel() {
		populatePersonId();
		populateFirstName();
		populateLastName();
		addButtonGrid();

		return this;
	}

	@Override
	public void loadPanelData() {
		setTitle(HeaderConstants.NEW_USER);
		arrangeComponents();
		prepareTabOutOrderList();
	}

	@Override
	public IPanelBean getPanelDataOnSubmit() {
		PersonPanelBean bean = new PersonPanelBean();
		PersonVO personVO = new PersonVO();
		personVO.setPersonId(Integer.parseInt(personIdField.getText()));
		personVO.setFirstName(firstNameField.getText());
		personVO.setLastName(lastNameField.getText());

		bean.setPersonVO(personVO);
		return bean;
	}

	@Override
	public boolean validatePanelData() {
		boolean personIdRsult = personIdValidator.validateOnSubmit();
		boolean firstNameRsult = firstNameValidator.validateOnSubmit();
		boolean lastNameIdRsult = lastNameValidator.validateOnSubmit();
		return (personIdRsult && firstNameRsult && lastNameIdRsult);
	}

	@Override
	public void applyRights() {
		return;
	}

	@Override
	public void prepareTabOutOrderList() {
		List<Component> componentList = new ArrayList<>();
		componentList.add(personIdField);
		componentList.add(firstNameField);
		componentList.add(lastNameField);
		componentList.add(nextButton);
		componentList.add(cancelButton);
		setTabOutOrderList(componentList);
	}

}
