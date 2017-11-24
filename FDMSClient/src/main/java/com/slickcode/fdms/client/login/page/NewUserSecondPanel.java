package com.slickcode.fdms.client.login.page;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.slickcode.baseframework.components.BaseButton;
import com.slickcode.baseframework.components.BaseComboBox;
import com.slickcode.baseframework.components.BaseLabel;
import com.slickcode.baseframework.components.BasePasswordField;
import com.slickcode.baseframework.components.BaseTextField;
import com.slickcode.baseframework.domain.IPanelBean;
import com.slickcode.baseframework.domain.SelectItem;
import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.baseframework.utils.BaseUtils;
import com.slickcode.baseframework.utils.ComponentEnum;
import com.slickcode.basevalidatorframework.NonEmtryValidator;
import com.slickcode.fdms.client.cache.FDMSCache;
import com.slickcode.fdms.client.constants.CommonConstants;
import com.slickcode.fdms.client.constants.HeaderConstants;
import com.slickcode.fdms.client.constants.ImageConstants;
import com.slickcode.fdms.client.constants.LabelConstants;
import com.slickcode.fdms.client.login.listner.GoToLoginPageActionListner;
import com.slickcode.fdms.client.login.listner.SignUpButtonActionListner;
import com.slickcode.fdms.client.utils.ButtonGridPanel;
import com.slickcode.fdms.common.bean.LoginPanelBean;
import com.slickcode.fdms.common.utils.SelectItemConvertor;
import com.slickcode.fdms.common.vo.LoginVO;
import com.slickcode.fdms.common.vo.PersonVO;
import com.slickcode.fdms.validator.FDMSValidator;

public class NewUserSecondPanel extends BasePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8890632949562582136L;

	private int firstColumnWidth = 0;
	private int secondColumnWidth = 200;
	private int thirdColumnWidth = 0;
	private int rowHeight = 20;
	private int heightPadding = 10;
	private int widthPadding = 10;

	private BaseLabel personIdLabel;
	private BaseLabel personIdValue;

	private BaseLabel firstNameLabel;
	private BaseLabel firstNameValue;

	private BaseLabel lastNameLabel;
	private BaseLabel lastNameValue;

	private BaseLabel securityQuestionLabel;
	private BaseComboBox<SelectItem> securityQuestionComboBox;
	private BaseLabel securityQuestionLabelError;

	private BaseLabel securityAnswerLabel;
	private BaseTextField securityAnswerField;
	private BaseLabel securityAnswerLabelError;
	private transient NonEmtryValidator securityAnswerValidator;

	private BaseLabel userNameLabel;
	private BaseTextField userNameField;
	private BaseLabel userNameLabelError;
	private transient NonEmtryValidator userNameValidator;

	private BaseLabel passwordLabel;
	private BasePasswordField passwordField;
	private BaseLabel passwordLabelError;
	private transient NonEmtryValidator passwordValidator;

	private BaseLabel confirmPasswordLabel;
	private BasePasswordField confirmPasswordField;
	private BaseLabel confirmPasswordLabelError;
	private transient NonEmtryValidator confirmPasswordValidator;

	private BaseButton signUpButton;
	private BaseButton cancelButton;
	private ButtonGridPanel buttonGridPanel;

	private LoginVO loginVO;

	private void populatePersonId() {
		personIdLabel = new BaseLabel(LabelConstants.LABEL_PERSON_ID, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, personIdLabel.getPreferredSize().getWidth());
		personIdLabel.setLabelFor(personIdValue);
		add(personIdLabel);

		personIdValue = new BaseLabel(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, personIdValue.getPreferredSize().getWidth());
		add(personIdValue);
	}

	private void populateFirstName() {
		firstNameLabel = new BaseLabel(LabelConstants.LABEL_FIRST_NAME, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, firstNameLabel.getPreferredSize().getWidth());
		firstNameLabel.setLabelFor(firstNameValue);
		add(firstNameLabel);

		firstNameValue = new BaseLabel(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, firstNameValue.getPreferredSize().getWidth());
		add(firstNameValue);
	}

	private void populateLastName() {
		lastNameLabel = new BaseLabel(LabelConstants.LABEL_LAST_NAME, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, lastNameLabel.getPreferredSize().getWidth());
		lastNameLabel.setLabelFor(lastNameValue);
		add(lastNameLabel);

		lastNameValue = new BaseLabel(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, lastNameValue.getPreferredSize().getWidth());
		add(lastNameValue);
	}

	private void populateSecurityQuestion() {
		securityQuestionLabel = new BaseLabel(LabelConstants.LABEL_SECURITY_QUESTION, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, securityQuestionLabel.getPreferredSize().getWidth());
		securityQuestionLabel.setLabelFor(securityQuestionComboBox);
		add(securityQuestionLabel);

		securityQuestionComboBox = new BaseComboBox<>(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, securityQuestionComboBox.getPreferredSize().getWidth());
		add(securityQuestionComboBox);

		securityQuestionLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);
		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth, securityQuestionLabelError.getPreferredSize().getWidth());
		add(securityQuestionLabelError);
	}

	private void populateSecurityAnswer() {
		securityAnswerLabel = new BaseLabel(LabelConstants.LABEL_SECURITY_ANSWER, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, securityAnswerLabel.getPreferredSize().getWidth());
		securityAnswerLabel.setLabelFor(securityAnswerField);
		add(securityAnswerLabel);

		securityAnswerField = new BaseTextField(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, securityAnswerField.getPreferredSize().getWidth());
		add(securityAnswerField);

		securityAnswerLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);
		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth, securityAnswerLabelError.getPreferredSize().getWidth());
		add(securityAnswerLabelError);

		securityAnswerValidator = new NonEmtryValidator(LabelConstants.LABEL_SECURITY_ANSWER, securityAnswerField,
				securityAnswerLabelError, true);
		securityAnswerField.getDocument().addDocumentListener(securityAnswerValidator);
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

		userNameValidator = new NonEmtryValidator(userNameField.getText(), userNameField, userNameLabelError, true);
		userNameField.getDocument().addDocumentListener(userNameValidator);
	}

	private void populatePassword() {
		passwordLabel = new BaseLabel(LabelConstants.LABEL_PSWD, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, passwordLabel.getPreferredSize().getWidth());
		passwordLabel.setLabelFor(passwordField);
		add(passwordLabel);

		passwordField = new BasePasswordField(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, passwordField.getPreferredSize().getWidth());
		add(passwordField);

		passwordLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);
		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth, passwordLabelError.getPreferredSize().getWidth());
		add(passwordLabelError);

		passwordValidator = new NonEmtryValidator(passwordLabel.getText(), passwordField, passwordLabelError, true);
		passwordField.getDocument().addDocumentListener(passwordValidator);
	}

	private void populateConfirmPassword() {
		confirmPasswordLabel = new BaseLabel(LabelConstants.LABEL_CONFIRM_PSWD, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, confirmPasswordLabel.getPreferredSize().getWidth());
		passwordLabel.setLabelFor(confirmPasswordField);
		add(confirmPasswordLabel);

		confirmPasswordField = new BasePasswordField(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, confirmPasswordField.getPreferredSize().getWidth());
		add(confirmPasswordField);

		confirmPasswordLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);
		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth, confirmPasswordLabelError.getPreferredSize().getWidth());
		add(confirmPasswordLabelError);

		confirmPasswordValidator = new NonEmtryValidator(confirmPasswordLabel.getText(), confirmPasswordField,
				confirmPasswordLabelError, true);
		confirmPasswordField.getDocument().addDocumentListener(confirmPasswordValidator);
	}

	private void addButtonGrid() {
		buttonGridPanel = new ButtonGridPanel(heightPadding, widthPadding, rowHeight);
		ImageIcon cancelIcon = BaseUtils.populateImage(ImageConstants.CANCEL);
		cancelButton = new BaseButton(cancelIcon, new GoToLoginPageActionListner(), CommonConstants.BUTTON_CANCEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, cancelButton.getPreferredSize().getWidth());
		buttonGridPanel.addButtonToList(cancelButton);

		ImageIcon signupIcon = BaseUtils.populateImage(ImageConstants.SIGN_UP);
		signUpButton = new BaseButton(signupIcon, new SignUpButtonActionListner(this), CommonConstants.BUTTON_SIGN_UP);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, signUpButton.getPreferredSize().getWidth());
		buttonGridPanel.addButtonToList(signUpButton);
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
		BaseUtils.setBound(personIdValue, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0, baseDimension);

		/**
		 * First Name Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(firstNameLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(firstNameValue, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0, baseDimension);

		/**
		 * Last Name Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(lastNameLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(lastNameValue, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0, baseDimension);

		/**
		 * Security Question Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(securityQuestionLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(securityQuestionComboBox, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0,
				baseDimension);

		fromLeft = fromLeft + secondColumnWidth + widthPadding;
		BaseUtils.setBound(securityQuestionLabelError, fromLeft, fromTop, thirdColumnWidth, rowHeight, 0, 0,
				baseDimension);

		/**
		 * Security Answer Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(securityAnswerLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(securityAnswerField, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + secondColumnWidth + widthPadding;
		BaseUtils.setBound(securityAnswerLabelError, fromLeft, fromTop, thirdColumnWidth, rowHeight, 0, 0,
				baseDimension);

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
		 * Password Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(passwordLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(passwordField, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + secondColumnWidth + widthPadding;
		BaseUtils.setBound(passwordLabelError, fromLeft, fromTop, thirdColumnWidth, rowHeight, 0, 0, baseDimension);

		/**
		 * Confirm Password Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(confirmPasswordLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(confirmPasswordField, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + secondColumnWidth + widthPadding;
		BaseUtils.setBound(confirmPasswordLabelError, fromLeft, fromTop, thirdColumnWidth, rowHeight, 0, 0,
				baseDimension);

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
		populateLastName();
		populateSecurityQuestion();
		populateSecurityAnswer();
		populateUserName();
		populatePassword();
		populateConfirmPassword();
		addButtonGrid();
		return this;
	}

	@Override
	public void loadPanelData() {
		personIdValue.setText(loginVO.getPersonVO().getPersonId());
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, personIdValue.getPreferredSize().getWidth());

		firstNameValue.setText(loginVO.getPersonVO().getFirstName());
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, firstNameValue.getPreferredSize().getWidth());

		lastNameValue.setText(loginVO.getPersonVO().getLastName());
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, lastNameValue.getPreferredSize().getWidth());

		securityQuestionComboBox.setSelectItemList(SelectItemConvertor
				.populateSecurityQuestionSelectItemList(FDMSCache.getInstance().getSecurityQuestionVOList()));
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, securityQuestionComboBox.getPreferredSize().getWidth());

		securityAnswerField.setText(loginVO.getSecurityAnswer());
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, securityAnswerField.getPreferredSize().getWidth());

		passwordField.setText(loginVO.getPassword());
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, passwordField.getPreferredSize().getWidth());

		confirmPasswordField.setText(loginVO.getPassword());
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, confirmPasswordField.getPreferredSize().getWidth());

		setTitle(HeaderConstants.NEW_USER);
		arrangeComponents();
		prepareTabOutOrderList();
	}

	@SuppressWarnings("deprecation")
	@Override
	public IPanelBean getPanelDataOnSubmit() {
		PersonVO personVO = new PersonVO();
		personVO.setPersonId(Integer.parseInt(personIdValue.getText()));
		personVO.setFirstName(firstNameValue.getText());
		personVO.setLastName(lastNameValue.getText());

		LoginVO panelLoginVO = new LoginVO();
		panelLoginVO.setPersonVO(personVO);
		panelLoginVO.setUserName(userNameField.getText());
		panelLoginVO.setPassword(passwordField.getText());
		panelLoginVO.setSecurityQuestion(
				FDMSCache.getInstance().getSecurityQuestionVOByCode(securityQuestionComboBox.getSelectedValue()));
		panelLoginVO.setSecurityAnswer(securityAnswerField.getText());

		LoginPanelBean bean = new LoginPanelBean();
		bean.setLoginVO(panelLoginVO);
		return bean;
	}

	@Override
	public boolean validatePanelData() {

		boolean usernameResult = userNameValidator.validateOnSubmit();
		boolean passwordResult = passwordValidator.validateOnSubmit();
		boolean confirmPasswordResult = confirmPasswordValidator.validateOnSubmit();
		boolean securityQuestionResult = FDMSValidator.validateBaseComboBox(securityQuestionComboBox,
				securityQuestionLabelError, securityQuestionLabel.getText());
		boolean securityAnswerResult = securityAnswerValidator.validateOnSubmit();
		boolean passwordCheckResult = true;
		if (passwordResult && confirmPasswordResult) {
			passwordCheckResult = FDMSValidator.checkPasswordSimilarity(passwordField, confirmPasswordField,
					passwordLabelError, confirmPasswordLabelError);
		}
		return usernameResult && passwordResult && confirmPasswordResult && securityQuestionResult
				&& securityAnswerResult && passwordCheckResult;
	}

	@Override
	public void applyRights() {
		return;
	}

	@Override
	public void prepareTabOutOrderList() {
		List<Component> componentList = new ArrayList<>();
		componentList.add(securityQuestionComboBox);
		componentList.add(securityAnswerField);
		componentList.add(userNameField);
		componentList.add(passwordField);
		componentList.add(confirmPasswordField);
		componentList.add(signUpButton);
		componentList.add(cancelButton);
		setTabOutOrderList(componentList);
	}

	/**
	 * @param loginVO
	 *            the loginVO to set
	 */
	public void setLoginVO(LoginVO loginVO) {
		this.loginVO = loginVO;
	}
}
