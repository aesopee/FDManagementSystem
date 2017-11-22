package com.slickcode.fdms.client.login.page;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.slickcode.baseframework.components.BaseButton;
import com.slickcode.baseframework.components.BaseComboBox;
import com.slickcode.baseframework.components.BaseLabel;
import com.slickcode.baseframework.components.BaseTextField;
import com.slickcode.baseframework.domain.IPanelBean;
import com.slickcode.baseframework.domain.SelectItem;
import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.baseframework.utils.BaseUtils;
import com.slickcode.baseframework.utils.ComponentEnum;
import com.slickcode.basevalidatorframework.NonEmtryValidator;
import com.slickcode.fdms.client.cache.FDMSCache;
import com.slickcode.fdms.client.constants.CommonConstants;
import com.slickcode.fdms.client.constants.ImageConstants;
import com.slickcode.fdms.client.login.listner.GoToForgotPasswordPageThirdActionListner;
import com.slickcode.fdms.client.login.listner.GoToLoginPageActionListner;
import com.slickcode.fdms.client.utils.ButtonGridPanel;
import com.slickcode.fdms.common.bean.LoginPanelBean;
import com.slickcode.fdms.common.utils.SelectItemConvertor;
import com.slickcode.fdms.common.vo.LoginVO;
import com.slickcode.fdms.common.vo.PersonVO;

public class ForgotPasswordSecondPanel extends BasePanel {

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

	private BaseLabel securityQuestionLabel;
	private BaseComboBox<SelectItem> securityQuestionComboBox;

	private BaseLabel securityAnswerLabel;
	private BaseTextField securityAnswerField;
	private BaseLabel securityAnswerLabelError;
	private transient NonEmtryValidator securityAnswerValidator;

	private BaseButton nextButton;
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

	private void populateSecurityQuestion() {
		securityQuestionLabel = new BaseLabel(CommonConstants.LABEL_SECURITY_QUESTION, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, securityQuestionLabel.getPreferredSize().getWidth());
		securityQuestionLabel.setLabelFor(securityQuestionComboBox);
		add(securityQuestionLabel);

		securityQuestionComboBox = new BaseComboBox<>(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, securityQuestionComboBox.getPreferredSize().getWidth());
		add(securityQuestionComboBox);

	}

	private void populateSecurityAnswer() {
		securityAnswerLabel = new BaseLabel(CommonConstants.LABEL_SECURITY_ANSWER, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, securityAnswerLabel.getPreferredSize().getWidth());
		securityAnswerLabel.setLabelFor(securityAnswerField);
		add(securityAnswerLabel);

		securityAnswerField = new BaseTextField(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, securityAnswerField.getPreferredSize().getWidth());
		add(securityAnswerField);

		securityAnswerLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);
		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth, securityAnswerLabelError.getPreferredSize().getWidth());
		add(securityAnswerLabelError);

		securityAnswerValidator = new NonEmtryValidator(CommonConstants.LABEL_SECURITY_ANSWER, securityAnswerField,
				securityAnswerLabelError, true);
		securityAnswerField.getDocument().addDocumentListener(securityAnswerValidator);
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

	private void addButtonGrid() {
		buttonGridPanel = new ButtonGridPanel(heightPadding, widthPadding, rowHeight);
		ImageIcon cancelIcon = BaseUtils.populateImage(ImageConstants.CANCEL);
		cancelButton = new BaseButton(cancelIcon, new GoToLoginPageActionListner(), CommonConstants.BUTTON_CANCEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, cancelButton.getPreferredSize().getWidth());
		buttonGridPanel.addButtonToList(cancelButton);

		ImageIcon nextIcon = BaseUtils.populateImage(ImageConstants.NEXT);
		nextButton = new BaseButton(nextIcon, new GoToForgotPasswordPageThirdActionListner(this),
				CommonConstants.BUTTON_NEXT);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, nextButton.getPreferredSize().getWidth());
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
		 * Security Question Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(securityQuestionLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(securityQuestionComboBox, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0,
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
		populateSecurityQuestion();
		populateSecurityAnswer();
		addButtonGrid();
		arrangeComponents();
		return this;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void loadPanelData() {
		personIdValue.setText(loginVO.getPersonVO().getPersonId());
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, personIdValue.getPreferredSize().getWidth());

		userNameValue.setText(loginVO.getUserName());
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, userNameValue.getPreferredSize().getWidth());

		securityQuestionComboBox.setSelectItemList(SelectItemConvertor
				.populateSecurityQuestionSelectItemList(FDMSCache.getInstance().getSecurityQuestionVOList()));
		securityQuestionComboBox.setSelectedItem(
				new SelectItem(loginVO.getSecurityQuestion().getCode(), loginVO.getSecurityQuestion().getNarrative()));
		securityQuestionComboBox.disable();
		securityQuestionComboBox.setEditable(false);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, securityQuestionComboBox.getPreferredSize().getWidth());

		setTitle(CommonConstants.HEADER_FORGOT_PSWD);
		prepareTabOutOrderList();
	}

	@Override
	public IPanelBean getPanelDataOnSubmit() {
		LoginPanelBean bean = new LoginPanelBean();

		LoginVO panelLoginVO = new LoginVO();
		panelLoginVO.setUserName(userNameValue.getText());
		panelLoginVO.setSecurityAnswer(securityAnswerField.getText());

		PersonVO personVO = new PersonVO();
		personVO.setPersonId(Integer.parseInt(personIdValue.getText()));

		panelLoginVO.setPersonVO(personVO);

		bean.setLoginVO(panelLoginVO);
		return bean;
	}

	@Override
	public boolean validatePanelData() {
		return securityAnswerValidator.validateOnSubmit();
	}

	@Override
	public void applyRights() {
		return;
	}

	@Override
	public void prepareTabOutOrderList() {
		List<Component> components = new ArrayList<>();
		components.add(securityQuestionComboBox);
		components.add(securityAnswerField);
		components.add(nextButton);
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
