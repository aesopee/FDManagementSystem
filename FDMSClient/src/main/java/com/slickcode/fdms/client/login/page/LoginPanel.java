package com.slickcode.fdms.client.login.page;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.slickcode.baseframework.components.BaseButton;
import com.slickcode.baseframework.components.BaseLabel;
import com.slickcode.baseframework.components.BasePasswordField;
import com.slickcode.baseframework.components.BaseTextField;
import com.slickcode.baseframework.domain.IPanelBean;
import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.baseframework.utils.BaseUtils;
import com.slickcode.baseframework.utils.ComponentEnum;
import com.slickcode.basevalidatorframework.NonEmtryValidator;
import com.slickcode.fdms.client.constants.CommonConstants;
import com.slickcode.fdms.client.constants.HeaderConstants;
import com.slickcode.fdms.client.constants.ImageConstants;
import com.slickcode.fdms.client.constants.LabelConstants;
import com.slickcode.fdms.client.login.listner.GoToForgotPasswordPageFirstActionListner;
import com.slickcode.fdms.client.login.listner.GoToNewUserPageFirstActionListner;
import com.slickcode.fdms.client.login.listner.LoginButtonActionListner;
import com.slickcode.fdms.client.utils.ButtonGridPanel;
import com.slickcode.fdms.common.bean.LoginPanelBean;
import com.slickcode.fdms.common.vo.LoginVO;

@Entity
public class LoginPanel extends BasePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int firstColumnWidth = 0;
	private int secondColumnWidth = 200;
	private int thirdColumnWidth = 0;
	private int rowHeight = 20;
	private int heightPadding = 20;
	private int widthPadding = 20;

	private BaseLabel userNameLabel;
	private BaseTextField userNameField;
	private BaseLabel userNameLabelError;
	private transient NonEmtryValidator userNameValidator;

	private BaseLabel passwordLabel;
	private BasePasswordField passwordField;
	private BaseLabel passwordLabelError;
	private transient NonEmtryValidator passwordValidator;

	private BaseButton loginButton;
	private BaseButton newUserButton;
	private BaseButton forgotPasswordButton;
	private ButtonGridPanel buttonGridPanel;

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

		passwordValidator = new NonEmtryValidator(LabelConstants.LABEL_PSWD, passwordField, passwordLabelError, true);
		passwordField.getDocument().addDocumentListener(passwordValidator);
	}

	private void addButtonGrid() {
		buttonGridPanel = new ButtonGridPanel(heightPadding, widthPadding, 50);

		ImageIcon newUserIcon = BaseUtils.populateImage(ImageConstants.NEW_USER);

		newUserButton = new BaseButton(newUserIcon, new GoToNewUserPageFirstActionListner(),
				CommonConstants.BUTTON_NEW_USER);

		buttonGridPanel.addButtonToList(newUserButton);

		ImageIcon loginIcon = BaseUtils.populateImage(ImageConstants.LOGIN);
		loginButton = new BaseButton(loginIcon, new LoginButtonActionListner(this), CommonConstants.Button.BUTTON_LOGIN);
		buttonGridPanel.addButtonToList(loginButton);

		ImageIcon forgotIcon = BaseUtils.populateImage(ImageConstants.FORGOT_PSWD);
		forgotPasswordButton = new BaseButton(forgotIcon, new GoToForgotPasswordPageFirstActionListner(),
				CommonConstants.BUTTON_FORGOT_PSWD);

		buttonGridPanel.addButtonToList(forgotPasswordButton);
		add(buttonGridPanel.createPanel());
	}

	@Override
	public void arrangeComponents() {
		int fromLeft = baseDimension.getWidth();
		int fromTop = baseDimension.getHeight();

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
		populateUserName();
		populatePassword();
		addButtonGrid();
		return this;
	}

	@Override
	public void loadPanelData() {
		arrangeComponents();
		setTitle(HeaderConstants.LOGIN);
		prepareTabOutOrderList();
	}

	@SuppressWarnings("deprecation")
	@Override
	public IPanelBean getPanelDataOnSubmit() {
		LoginPanelBean loginPanelBean = new LoginPanelBean();
		LoginVO loginVO = new LoginVO();
		loginVO.setPassword(passwordField.getText());
		loginVO.setUserName(userNameField.getText());
		loginPanelBean.setLoginVO(loginVO);
		return loginPanelBean;
	}

	@Override
	public boolean validatePanelData() {
		boolean usernameResult = userNameValidator.validateOnSubmit();
		boolean passwordResult = passwordValidator.validateOnSubmit();
		return usernameResult && passwordResult;
	}

	@Override
	public void applyRights() {
		return;
	}

	@Override
	public void prepareTabOutOrderList() {
		List<Component> components = new ArrayList<>();
		components.add(userNameField);
		components.add(passwordField);
		components.add(loginButton);
		components.add(forgotPasswordButton);
		components.add(newUserButton);
		setTabOutOrderList(components);
	}
}
