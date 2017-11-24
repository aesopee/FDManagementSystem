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
import com.slickcode.fdms.client.login.listner.GoToForgotPasswordPageSecondActionListner;
import com.slickcode.fdms.client.login.listner.GoToLoginPageActionListner;
import com.slickcode.fdms.client.utils.ButtonGridPanel;
import com.slickcode.fdms.common.bean.LoginPanelBean;
import com.slickcode.fdms.common.vo.LoginVO;
import com.slickcode.fdms.common.vo.PersonVO;

public class ForgotPasswordFirstPanel extends BasePanel {

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

	private BaseLabel userNameLabel;
	private BaseTextField userNameField;
	private BaseLabel userNameLabelError;
	private transient NonEmtryValidator userNameValidator;

	private BaseButton nextButton;
	private BaseButton cancelButton;
	private ButtonGridPanel buttonGridPanel;

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

		personIdValidator = new NumericValidator(LabelConstants.LABEL_PERSON_ID, personIdField, personIdLabelError,
				true);
		personIdField.getDocument().addDocumentListener(personIdValidator);
	}

	private void populateUserName() {
		userNameLabel = new BaseLabel(LabelConstants.LABEL_USER_NAME, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, userNameLabel.getPreferredSize().getWidth());
		userNameLabel.setLabelFor(userNameField);
		add(userNameLabel);

		userNameField = new BaseTextField(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, userNameField.getPreferredSize().getWidth());
		add(userNameField);

		userNameLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);

		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth, userNameLabelError.getPreferredSize().getWidth());
		add(userNameLabelError);

		userNameValidator = new NonEmtryValidator(LabelConstants.LABEL_USER_NAME, userNameField, userNameLabelError,
				true);
		userNameField.getDocument().addDocumentListener(userNameValidator);
	}

	private void addButtonGrid() {
		buttonGridPanel = new ButtonGridPanel(heightPadding, widthPadding, 50);

		ImageIcon cancelIcon = BaseUtils.populateImage(ImageConstants.CANCEL);
		cancelButton = new BaseButton(cancelIcon, new GoToLoginPageActionListner(), CommonConstants.BUTTON_CANCEL);
		buttonGridPanel.addButtonToList(cancelButton);

		ImageIcon nextIcon = BaseUtils.populateImage(ImageConstants.NEXT);
		nextButton = new BaseButton(nextIcon, new GoToForgotPasswordPageSecondActionListner(this),
				CommonConstants.BUTTON_NEXT);
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
		BaseUtils.setBound(personIdLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(personIdField, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + secondColumnWidth + widthPadding;
		BaseUtils.setBound(personIdLabelError, fromLeft, fromTop, thirdColumnWidth, rowHeight, 0, 0, baseDimension);

		/**
		 * User Name Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(userNameLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(userNameField, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + secondColumnWidth + widthPadding;
		BaseUtils.setBound(userNameLabelError, fromLeft, fromTop, thirdColumnWidth, rowHeight, 0, 0, baseDimension);

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
		populateUserName();
		addButtonGrid();
		arrangeComponents();
		return this;
	}

	@Override
	public void loadPanelData() {
		setTitle(HeaderConstants.FORGOT_PSWD);
		prepareTabOutOrderList();
	}

	@Override
	public IPanelBean getPanelDataOnSubmit() {
		LoginPanelBean bean = new LoginPanelBean();
		LoginVO loginVO = new LoginVO();
		loginVO.setUserName(userNameField.getText());

		PersonVO personVO = new PersonVO();
		personVO.setPersonId(Integer.parseInt(personIdField.getText()));

		loginVO.setPersonVO(personVO);

		bean.setLoginVO(loginVO);
		return bean;
	}

	@Override
	public boolean validatePanelData() {
		boolean personIdRsult = personIdValidator.validateOnSubmit();
		boolean usernameResult = userNameValidator.validateOnSubmit();
		return personIdRsult && usernameResult;
	}

	@Override
	public void applyRights() {
		return;
	}

	@Override
	public void prepareTabOutOrderList() {
		List<Component> components = new ArrayList<>();
		components.add(personIdField);
		components.add(userNameField);
		components.add(nextButton);
		components.add(cancelButton);
		setTabOutOrderList(components);
	}

}
