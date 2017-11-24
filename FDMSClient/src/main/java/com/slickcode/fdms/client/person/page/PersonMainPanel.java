package com.slickcode.fdms.client.person.page;

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
import com.slickcode.baseframework.utils.StringUtilities;
import com.slickcode.basevalidatorframework.NonEmtryValidator;
import com.slickcode.basevalidatorframework.NumericValidator;
import com.slickcode.fdms.client.constants.CommonConstants;
import com.slickcode.fdms.client.constants.HeaderConstants;
import com.slickcode.fdms.client.constants.ImageConstants;
import com.slickcode.fdms.client.constants.LabelConstants;
import com.slickcode.fdms.client.login.listner.GoToHomePageListner;
import com.slickcode.fdms.client.person.listner.AddPersonActionListner;
import com.slickcode.fdms.client.person.listner.CopyPersonActionListner;
import com.slickcode.fdms.client.person.listner.EditPersonActionListner;
import com.slickcode.fdms.client.person.listner.FetchPersonRelatedActionListner;
import com.slickcode.fdms.client.person.listner.SearchPersonActionListner;
import com.slickcode.fdms.client.person.listner.UpdatePersonActionListner;
import com.slickcode.fdms.client.utils.ButtonGridPanel;
import com.slickcode.fdms.client.utils.ScreenMode;
import com.slickcode.fdms.common.bean.PersonPanelBean;
import com.slickcode.fdms.common.vo.PersonVO;

public class PersonMainPanel extends BasePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6709253570157676433L;

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

	private BaseLabel middleNameLabel;
	private BaseTextField middleNameField;
	private BaseLabel middleNameLabelError;
	private transient NonEmtryValidator middleNameValidator;

	private BaseLabel lastNameLabel;
	private BaseTextField lastNameField;
	private BaseLabel lastNameLabelError;
	private transient NonEmtryValidator lastNameValidator;

	private BaseButton mainButton;
	private BaseButton copyButton;
	private BaseButton cancelButton;
	private BaseButton relatedFdsButton;
	private ButtonGridPanel buttonGridPanel;

	private PersonVO personVO;
	private ScreenMode screenMode;

	private void populatePersonId() {
		personIdLabel = new BaseLabel(LabelConstants.LABEL_PERSON_ID, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, personIdLabel.getPreferredSize().getWidth());
		personIdLabel.setLabelFor(personIdField);
		add(personIdLabel);

		personIdField = new BaseTextField(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, personIdField.getPreferredSize().getWidth());
		add(personIdField);

		personIdLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);
		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth, personIdLabelError.getPreferredSize().getWidth());
		add(personIdLabelError);

		personIdValidator = new NumericValidator(personIdLabel.getText(), personIdField, personIdLabelError, true);
		personIdField.getDocument().addDocumentListener(personIdValidator);
	}

	private void populateFirstName() {
		firstNameLabel = new BaseLabel(LabelConstants.LABEL_FIRST_NAME, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, firstNameLabel.getPreferredSize().getWidth());
		firstNameLabel.setLabelFor(firstNameField);
		add(firstNameLabel);

		firstNameField = new BaseTextField(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, firstNameField.getPreferredSize().getWidth());
		add(firstNameField);

		firstNameLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);
		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth, firstNameLabelError.getPreferredSize().getWidth());
		add(firstNameLabelError);

		firstNameValidator = new NonEmtryValidator(firstNameLabel.getText(), firstNameField, firstNameLabelError, true);
		firstNameField.getDocument().addDocumentListener(firstNameValidator);
	}

	private void populateMiddleName() {
		middleNameLabel = new BaseLabel(LabelConstants.LABEL_MIDDLE_NAME, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, middleNameLabel.getPreferredSize().getWidth());
		middleNameLabel.setLabelFor(middleNameField);
		add(middleNameLabel);

		middleNameField = new BaseTextField(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, middleNameField.getPreferredSize().getWidth());
		add(middleNameField);

		middleNameLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);
		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth, middleNameLabelError.getPreferredSize().getWidth());
		add(middleNameLabelError);

		middleNameValidator = new NonEmtryValidator(middleNameLabel.getText(), middleNameField, middleNameLabelError,
				false);
		middleNameField.getDocument().addDocumentListener(middleNameValidator);
	}

	private void populateLastName() {
		lastNameLabel = new BaseLabel(LabelConstants.LABEL_LAST_NAME, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, lastNameLabel.getPreferredSize().getWidth());
		lastNameLabel.setLabelFor(lastNameField);
		add(lastNameLabel);

		lastNameField = new BaseTextField(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, lastNameField.getPreferredSize().getWidth());
		add(lastNameField);

		lastNameLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);
		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth, lastNameLabelError.getPreferredSize().getWidth());
		add(lastNameLabelError);

		lastNameValidator = new NonEmtryValidator(lastNameLabel.getText(), lastNameField, lastNameLabelError, true);
		lastNameField.getDocument().addDocumentListener(lastNameValidator);
	}

	private void addButtonGrid() {
		buttonGridPanel = new ButtonGridPanel(heightPadding, widthPadding, rowHeight);
		ImageIcon cancelIcon = BaseUtils.populateImage(ImageConstants.CANCEL);
		cancelButton = new BaseButton(cancelIcon, new GoToHomePageListner(this), CommonConstants.BUTTON_CANCEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, cancelButton.getPreferredSize().getWidth());
		buttonGridPanel.addButtonToList(cancelButton);

		ImageIcon searchIcon = BaseUtils.populateImage(ImageConstants.SEARCH);
		mainButton = new BaseButton(searchIcon, null, CommonConstants.BUTTON_SEARCH);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, mainButton.getPreferredSize().getWidth());
		buttonGridPanel.addButtonToList(mainButton);

		ImageIcon copyIcon = BaseUtils.populateImage(ImageConstants.COPY);
		copyButton = new BaseButton(copyIcon, new CopyPersonActionListner(this), CommonConstants.BUTTON_COPY);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, copyButton.getPreferredSize().getWidth());

		ImageIcon relatedFdsIcon = BaseUtils.populateImage(ImageConstants.RELATED_FDS);
		relatedFdsButton = new BaseButton(relatedFdsIcon, new FetchPersonRelatedActionListner(this),
				CommonConstants.BUTTON_RELATED_FDS);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, relatedFdsButton.getPreferredSize().getWidth());

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
		BaseUtils.setBound(personIdLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(personIdField, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + secondColumnWidth + widthPadding;
		BaseUtils.setBound(personIdLabelError, fromLeft, fromTop, thirdColumnWidth, rowHeight, 0, 0, baseDimension);

		/**
		 * First Name Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(firstNameLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(firstNameField, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + secondColumnWidth + widthPadding;
		BaseUtils.setBound(firstNameLabelError, fromLeft, fromTop, thirdColumnWidth, rowHeight, 0, 0, baseDimension);

		/**
		 * Middle Name Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(middleNameLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(middleNameField, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + secondColumnWidth + widthPadding;
		BaseUtils.setBound(middleNameLabelError, fromLeft, fromTop, thirdColumnWidth, rowHeight, 0, 0, baseDimension);

		/**
		 * Last Name Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(lastNameLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(lastNameField, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + secondColumnWidth + widthPadding;
		BaseUtils.setBound(lastNameLabelError, fromLeft, fromTop, thirdColumnWidth, rowHeight, 0, 0, baseDimension);

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
		populatePersonId();
		populateFirstName();
		populateMiddleName();
		populateLastName();
		addButtonGrid();
		return this;
	}

	@Override
	public void loadPanelData() {

		personIdField.setText(personVO.getPersonId());
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, personIdField.getPreferredSize().getWidth());

		firstNameField.setText(personVO.getFirstName());
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, firstNameField.getPreferredSize().getWidth());

		middleNameField.setText(personVO.getMiddleName());
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, middleNameField.getPreferredSize().getWidth());

		lastNameField.setText(personVO.getLastName());
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, lastNameField.getPreferredSize().getWidth());

		applyRights();
		arrangeComponents();
		prepareTabOutOrderList();
	}

	@Override
	public IPanelBean getPanelDataOnSubmit() {
		PersonPanelBean personPanelBean = new PersonPanelBean();
		PersonVO panelPersonVO = new PersonVO();
		if (!StringUtilities.isEmpty(personIdField.getText())) {
			panelPersonVO.setPersonId(Integer.parseInt(personIdField.getText()));
		}

		panelPersonVO.setFirstName(firstNameField.getText());
		panelPersonVO.setMiddleName(middleNameField.getText());
		panelPersonVO.setLastName(lastNameField.getText());

		personPanelBean.setPersonVO(panelPersonVO);
		return personPanelBean;
	}

	@Override
	public boolean validatePanelData() {
		boolean personIdResult = personIdValidator.validateOnSubmit();
		boolean firstNameResult = firstNameValidator.validateOnSubmit();
		boolean middleNameResult = middleNameValidator.validateOnSubmit();
		boolean lastNameIdResult = lastNameValidator.validateOnSubmit();
		return personIdResult && firstNameResult && middleNameResult && lastNameIdResult;
	}

	public void applyRights() {
		switch (screenMode) {
		case CREATE:
			personIdField.setEditable(false);
			personIdValidator.setMandatory(false);
			firstNameField.setEditable(true);
			firstNameValidator.setMandatory(true);
			middleNameField.setEditable(true);
			middleNameValidator.setMandatory(false);
			lastNameField.setEditable(true);
			lastNameValidator.setMandatory(true);
			setTitle(HeaderConstants.ADD_PERSON);
			ImageIcon addIcon = BaseUtils.populateImage(ImageConstants.ADD);
			mainButton.setAllValues(addIcon, new AddPersonActionListner(this), CommonConstants.BUTTON_ADD);
			break;
		case EDIT:
			personIdField.setEditable(false);
			personIdValidator.setMandatory(false);
			firstNameField.setEditable(true);
			firstNameValidator.setMandatory(true);
			middleNameField.setEditable(true);
			middleNameValidator.setMandatory(false);
			lastNameField.setEditable(true);
			lastNameValidator.setMandatory(true);
			setTitle(HeaderConstants.EDIT_PERSON);
			ImageIcon updateIcon = BaseUtils.populateImage(ImageConstants.UPDATE);
			mainButton.setAllValues(updateIcon, new UpdatePersonActionListner(this), CommonConstants.BUTTON_UPDATE);
			break;
		case SEARCH:
			personIdField.setEditable(true);
			personIdValidator.setMandatory(false);
			firstNameField.setEditable(true);
			firstNameValidator.setMandatory(false);
			middleNameField.setEditable(true);
			middleNameValidator.setMandatory(false);
			lastNameField.setEditable(true);
			lastNameValidator.setMandatory(false);
			setTitle(HeaderConstants.SEARCH_PERSON);
			ImageIcon searchIcon = BaseUtils.populateImage(ImageConstants.SEARCH);
			mainButton.setAllValues(searchIcon, new SearchPersonActionListner(this), CommonConstants.BUTTON_SEARCH);
			break;
		case VIEW:
			personIdField.setEditable(false);
			personIdValidator.setMandatory(false);
			firstNameField.setEditable(false);
			firstNameValidator.setMandatory(false);
			middleNameField.setEditable(false);
			middleNameValidator.setMandatory(false);
			lastNameField.setEditable(false);
			lastNameValidator.setMandatory(false);
			setTitle(HeaderConstants.SHOW_PERSON);
			ImageIcon editIcon = BaseUtils.populateImage(ImageConstants.EDIT);
			mainButton.setAllValues(editIcon, new EditPersonActionListner(this), CommonConstants.BUTTON_EDIT);
			buttonGridPanel.addButtonToList(copyButton);
			buttonGridPanel.addButtonToList(relatedFdsButton);
			break;
		case RENEW:
			break;
		default:
			break;
		}
	}

	@Override
	public void prepareTabOutOrderList() {
		List<Component> componentList = new ArrayList<>();
		componentList.add(personIdField);
		componentList.add(firstNameField);
		componentList.add(middleNameField);
		componentList.add(lastNameField);
		componentList.add(mainButton);
		componentList.add(cancelButton);
		componentList.add(copyButton);
		componentList.add(relatedFdsButton);
		setTabOutOrderList(componentList);
	}

	/**
	 * @param personVO
	 *            the personVO to set
	 */
	public void setPersonVO(PersonVO personVO) {
		this.personVO = personVO;
	}

	/**
	 * @param screenMode
	 *            the screenMode to set
	 */
	public void setScreenMode(ScreenMode screenMode) {
		this.screenMode = screenMode;
	}

}
