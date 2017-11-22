package com.slickcode.fdms.client.login.page;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.slickcode.baseframework.components.BaseButton;
import com.slickcode.baseframework.components.BaseLabel;
import com.slickcode.baseframework.components.BasePasswordField;
import com.slickcode.baseframework.domain.IPanelBean;
import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.baseframework.utils.BaseUtils;
import com.slickcode.baseframework.utils.ComponentEnum;
import com.slickcode.basevalidatorframework.NonEmtryValidator;
import com.slickcode.fdms.client.constants.CommonConstants;
import com.slickcode.fdms.client.constants.ImageConstants;
import com.slickcode.fdms.client.login.listner.ChangePasswordButtonActionListner;
import com.slickcode.fdms.client.login.listner.GoToLoginPageActionListner;
import com.slickcode.fdms.client.utils.ButtonGridPanel;
import com.slickcode.fdms.common.bean.LoginPanelBean;
import com.slickcode.fdms.common.vo.LoginVO;
import com.slickcode.fdms.common.vo.PersonVO;
import com.slickcode.fdms.validator.FDMSValidator;

public class ForgotPasswordThirdPanel extends BasePanel {

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

	private BaseLabel userNameLabel;
	private BaseLabel userNameValue;

	private BaseLabel passwordLabel;
	private BasePasswordField passwordField;
	private BaseLabel passwordLabelError;
	private transient NonEmtryValidator passwordValidator;

	private BaseLabel confirmPasswordLabel;
	private BasePasswordField confirmPasswordField;
	private BaseLabel confirmPasswordLabelError;
	private transient NonEmtryValidator confirmPasswordValidator;

	private BaseButton changePasswordButton;
	private BaseButton cancelButton;
	private ButtonGridPanel buttonGridPanel;

	private LoginVO loginVO;

	private void populatePersonId() {
		personIdLabel = new BaseLabel(CommonConstants.LABEL_PERSON_ID, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, personIdLabel.getPreferredSize().getWidth());
		personIdLabel.setLabelFor(personIdValue);
		add(personIdLabel);

		personIdValue = new BaseLabel(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, personIdValue.getPreferredSize().getWidth());
		add(personIdValue);
	}

	private void populateUserName() {
		userNameLabel = new BaseLabel(CommonConstants.LABEL_USER_NAME, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, userNameLabel.getPreferredSize().getWidth());
		userNameLabel.setLabelFor(userNameValue);
		add(userNameLabel);

		userNameValue = new BaseLabel(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, userNameValue.getPreferredSize().getWidth());
		add(userNameValue);
	}

	private void populatePassword() {
		passwordLabel = new BaseLabel(CommonConstants.LABEL_PSWD, ComponentEnum.LABEL);
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
		confirmPasswordLabel = new BaseLabel(CommonConstants.LABEL_CONFIRM_PSWD, ComponentEnum.LABEL);
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

		ImageIcon changePasswordIcon = BaseUtils.populateImage(ImageConstants.CHANGE_PSWD);
		changePasswordButton = new BaseButton(changePasswordIcon, new ChangePasswordButtonActionListner(this),
				CommonConstants.BUTTON_CHANGE_PSWD);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, changePasswordButton.getPreferredSize().getWidth());
		buttonGridPanel.addButtonToList(changePasswordButton);
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
		 * User Name Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(userNameLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(userNameValue, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0, baseDimension);

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

		userNameValue.setText(loginVO.getUserName());
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, userNameValue.getPreferredSize().getWidth());

		arrangeComponents();
		setTitle(CommonConstants.HEADER_FORGOT_PSWD);
		prepareTabOutOrderList();
	}

	@SuppressWarnings("deprecation")
	@Override
	public IPanelBean getPanelDataOnSubmit() {
		LoginPanelBean bean = new LoginPanelBean();

		LoginVO panelLoginVO = new LoginVO();
		panelLoginVO.setUserName(userNameValue.getText());
		panelLoginVO.setPassword(passwordField.getText());

		PersonVO personVO = new PersonVO();
		personVO.setPersonId(Integer.parseInt(personIdValue.getText()));

		panelLoginVO.setPersonVO(personVO);

		bean.setLoginVO(panelLoginVO);
		return bean;
	}

	@Override
	public boolean validatePanelData() {
		boolean passwordResult = passwordValidator.validateOnSubmit();
		boolean confirmPasswordResult = confirmPasswordValidator.validateOnSubmit();
		boolean passwordCheckResult = true;
		if (passwordResult && confirmPasswordResult) {
			passwordCheckResult = FDMSValidator.checkPasswordSimilarity(passwordField, confirmPasswordField,
					passwordLabelError, confirmPasswordLabelError);
		}
		return passwordResult && confirmPasswordResult && passwordCheckResult;
	}

	@Override
	public void applyRights() {
		return;
	}

	@Override
	public void prepareTabOutOrderList() {
		List<Component> components = new ArrayList<>();
		components.add(passwordField);
		components.add(confirmPasswordField);
		components.add(changePasswordButton);
		components.add(cancelButton);
		setTabOutOrderList(components);
	}

	/**
	 * @param loginVO
	 *            the loginVO to set
	 */
	public void setLoginVO(LoginVO loginVO) {
		this.loginVO = loginVO;
	}
}
