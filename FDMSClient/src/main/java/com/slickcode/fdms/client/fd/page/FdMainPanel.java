package com.slickcode.fdms.client.fd.page;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.slickcode.baseframework.components.BaseButton;
import com.slickcode.baseframework.components.BaseComboBox;
import com.slickcode.baseframework.components.BaseLabel;
import com.slickcode.baseframework.components.BaseTextField;
import com.slickcode.baseframework.domain.IPanelBean;
import com.slickcode.baseframework.domain.SelectItem;
import com.slickcode.baseframework.listner.DatePickerListner;
import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.baseframework.utils.BaseUtils;
import com.slickcode.baseframework.utils.ComponentEnum;
import com.slickcode.basevalidatorframework.AmountValidator;
import com.slickcode.basevalidatorframework.DateUtilities;
import com.slickcode.basevalidatorframework.DateValidator;
import com.slickcode.basevalidatorframework.NonEmtryValidator;
import com.slickcode.basevalidatorframework.NumericUtilities;
import com.slickcode.basevalidatorframework.NumericValidator;
import com.slickcode.fdms.client.cache.FDMSCache;
import com.slickcode.fdms.client.constants.CommonConstants;
import com.slickcode.fdms.client.constants.HeaderConstants;
import com.slickcode.fdms.client.constants.ImageConstants;
import com.slickcode.fdms.client.constants.LabelConstants;
import com.slickcode.fdms.client.fd.listner.AddFdActionListner;
import com.slickcode.fdms.client.fd.listner.CopyFdActionListner;
import com.slickcode.fdms.client.fd.listner.EditFdActionListner;
import com.slickcode.fdms.client.fd.listner.InitiateRenewFdActionListner;
import com.slickcode.fdms.client.fd.listner.RenewFdActionListner;
import com.slickcode.fdms.client.fd.listner.SearchFdActionListner;
import com.slickcode.fdms.client.fd.listner.TrackFdActionListner;
import com.slickcode.fdms.client.fd.listner.UpdateFdActionListner;
import com.slickcode.fdms.client.fd.listner.WithdrawFdActionListner;
import com.slickcode.fdms.client.login.listner.GoToHomePageListner;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.client.utils.ButtonGridPanel;
import com.slickcode.fdms.client.utils.ScreenMode;
import com.slickcode.fdms.common.bean.FdPanelBean;
import com.slickcode.fdms.common.constant.FDMConstant;
import com.slickcode.fdms.common.constant.FdStatusEnum;
import com.slickcode.fdms.common.utils.SelectItemConvertor;
import com.slickcode.fdms.common.vo.BankVO;
import com.slickcode.fdms.common.vo.FdVO;
import com.slickcode.fdms.common.vo.PersonVO;
import com.slickcode.fdms.validator.FDMSValidator;

public class FdMainPanel extends BasePanel {

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

	private BaseLabel fdIdLabel;
	private BaseTextField fdIdField;
	private BaseLabel fdIdLabelError;
	private transient NumericValidator fdIdValidator;

	private BaseLabel fdBankReferenceNumberLabel;
	private BaseTextField fdBankReferenceNumberField;
	private BaseLabel fdBankReferenceNumberLabelError;
	private transient NumericValidator fdBankReferenceNumberValidator;

	private BaseLabel originalFdNumberLabel;
	private BaseTextField originalFdNumberField;

	private BaseLabel renewedFromFdNumberLabel;
	private BaseTextField renewedFromFdNumberField;

	private BaseLabel renewedToFdNumberLabel;
	private BaseTextField renewedToFdNumberField;

	private BaseLabel fdNumberLabel;
	private BaseTextField fdNumberField;
	private BaseLabel fdNumberLabelError;
	private transient NonEmtryValidator fdNumberValidator;

	private BaseLabel bankNameLabel;
	private BaseComboBox<SelectItem> bankNameComboBox;
	private BaseLabel bankNameLabelError;

	private BaseLabel firstOwnerLabel;
	private BaseComboBox<SelectItem> firstOwnerComboBox;
	private BaseLabel firstOwnerLabelError;

	private BaseLabel secondOwnerLabel;
	private BaseComboBox<SelectItem> secondOwnerComboBox;
	private BaseLabel secondOwnerLabelError;

	private BaseLabel nomineeLabel;
	private BaseComboBox<SelectItem> nomineeComboBox;
	private BaseLabel nomineeLabelError;

	private BaseLabel investedAmountLabel;
	private BaseTextField investedAmountField;
	private BaseLabel investedAmountLabelError;
	private transient AmountValidator investedAmountValidator;

	private BaseLabel maturityAmountLabel;
	private BaseTextField maturityAmountField;
	private BaseLabel maturityAmountLabelError;
	private transient AmountValidator maturityAmountValidator;

	private BaseLabel investmentDateLabel;
	private BaseTextField investmentDateField;
	private BaseButton investmentDateButton;
	private BaseLabel investmentDateLabelError;
	private transient DateValidator investmentDateValidator;

	private BaseLabel maturityDateLabel;
	private BaseTextField maturityDateField;
	private BaseButton maturityDateButton;
	private BaseLabel maturityDateLabelError;
	private transient DateValidator maturityDateValidator;

	private BaseLabel remarkLabel;
	private BaseTextField remarkField;
	private BaseLabel remarkLabelError;

	private BaseLabel statusLabel;
	private BaseComboBox<SelectItem> statusComboBox;

	private BaseButton mainButton;
	private BaseButton copyButton;
	private BaseButton renewButton;
	private BaseButton withdrawButton;
	private BaseButton trackFdButton;
	private BaseButton cancelButton;
	private ButtonGridPanel buttonGridPanel;

	private FdVO fdVO;
	private ScreenMode screenMode;

	private void populateFDId() {
		fdIdLabel = new BaseLabel(LabelConstants.LABEL_FD_ID, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, fdIdLabel.getPreferredSize().getWidth());
		fdIdLabel.setLabelFor(fdIdField);
		add(fdIdLabel);

		fdIdField = new BaseTextField(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, fdIdField.getPreferredSize().getWidth());
		add(fdIdField);

		fdIdLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);
		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth, fdIdLabelError.getPreferredSize().getWidth());
		add(fdIdLabelError);

		fdIdValidator = new NumericValidator(fdIdLabel.getText(), fdIdField, fdIdLabelError, true);
		fdIdField.getDocument().addDocumentListener(fdIdValidator);
	}

	private void populateFdBankReferenceNumber() {
		fdBankReferenceNumberLabel = new BaseLabel(LabelConstants.LABEL_FD_BANK_REFERENCE_NUMBAI, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, fdBankReferenceNumberLabel.getPreferredSize().getWidth());
		fdBankReferenceNumberLabel.setLabelFor(fdBankReferenceNumberField);
		add(fdBankReferenceNumberLabel);

		fdBankReferenceNumberField = new BaseTextField(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth,
				fdBankReferenceNumberField.getPreferredSize().getWidth());
		add(fdBankReferenceNumberField);

		fdBankReferenceNumberLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);
		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth,
				fdBankReferenceNumberLabelError.getPreferredSize().getWidth());
		add(fdBankReferenceNumberLabelError);

		fdBankReferenceNumberValidator = new NumericValidator(fdBankReferenceNumberLabel.getText(),
				fdBankReferenceNumberField, fdBankReferenceNumberLabelError, true);
		fdBankReferenceNumberField.getDocument().addDocumentListener(fdBankReferenceNumberValidator);
	}

	private void populateFDNumber() {
		fdNumberLabel = new BaseLabel(LabelConstants.LABEL_FD_NUMBER, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, fdNumberLabel.getPreferredSize().getWidth());
		fdNumberLabel.setLabelFor(fdNumberField);
		add(fdNumberLabel);

		fdNumberField = new BaseTextField(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, fdNumberField.getPreferredSize().getWidth());
		add(fdNumberField);

		fdNumberLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);
		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth, fdNumberLabelError.getPreferredSize().getWidth());
		add(fdNumberLabelError);

		fdNumberValidator = new NonEmtryValidator(fdNumberLabel.getText(), fdNumberField, fdNumberLabelError, true);
		fdNumberField.getDocument().addDocumentListener(fdNumberValidator);
	}

	private void populateRenewedToFDNumber() {
		renewedToFdNumberLabel = new BaseLabel(LabelConstants.LABEL_RENEWED_TO_FD_NUMBER, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, renewedToFdNumberLabel.getPreferredSize().getWidth());
		renewedToFdNumberLabel.setLabelFor(renewedToFdNumberField);
		add(renewedToFdNumberLabel);

		renewedToFdNumberField = new BaseTextField(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, renewedToFdNumberField.getPreferredSize().getWidth());
		renewedToFdNumberField.setEnabled(false);
		add(renewedToFdNumberField);

	}

	private void populateRenewedFromFDNumber() {
		renewedFromFdNumberLabel = new BaseLabel(LabelConstants.LABEL_RENEWED_FROM_FD_NUMBER, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, renewedFromFdNumberLabel.getPreferredSize().getWidth());
		renewedFromFdNumberLabel.setLabelFor(renewedFromFdNumberField);
		add(renewedFromFdNumberLabel);

		renewedFromFdNumberField = new BaseTextField(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, renewedFromFdNumberField.getPreferredSize().getWidth());
		renewedFromFdNumberField.setEnabled(false);
		add(renewedFromFdNumberField);

	}

	private void populateOriginalFDNumber() {
		originalFdNumberLabel = new BaseLabel(LabelConstants.LABEL_ORIGINAL_FD_NUMBER, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, originalFdNumberLabel.getPreferredSize().getWidth());
		originalFdNumberLabel.setLabelFor(originalFdNumberField);
		add(originalFdNumberLabel);

		originalFdNumberField = new BaseTextField(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, originalFdNumberField.getPreferredSize().getWidth());
		originalFdNumberField.setEnabled(false);
		add(originalFdNumberField);

	}

	private void populateBankName() {
		bankNameLabel = new BaseLabel(LabelConstants.LABEL_BANK_NAME, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, bankNameLabel.getPreferredSize().getWidth());
		bankNameLabel.setLabelFor(bankNameComboBox);
		add(bankNameLabel);

		bankNameComboBox = new BaseComboBox<>(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, bankNameComboBox.getPreferredSize().getWidth());
		add(bankNameComboBox);

		bankNameLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);
		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth, bankNameLabelError.getPreferredSize().getWidth());
		add(bankNameLabelError);
	}

	private void populateFirstOwnerName() {
		firstOwnerLabel = new BaseLabel(LabelConstants.LABEL_FIRST_OWNER, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, firstOwnerLabel.getPreferredSize().getWidth());
		firstOwnerLabel.setLabelFor(firstOwnerComboBox);
		add(firstOwnerLabel);

		firstOwnerComboBox = new BaseComboBox<>(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, firstOwnerComboBox.getPreferredSize().getWidth());
		add(firstOwnerComboBox);

		firstOwnerLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);
		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth, firstOwnerLabelError.getPreferredSize().getWidth());
		add(firstOwnerLabelError);
	}

	private void populateSecondOwner() {
		secondOwnerLabel = new BaseLabel(LabelConstants.LABEL_SECOND_OWNER, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, secondOwnerLabel.getPreferredSize().getWidth());
		secondOwnerLabel.setLabelFor(secondOwnerComboBox);
		add(secondOwnerLabel);

		secondOwnerComboBox = new BaseComboBox<>(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, secondOwnerComboBox.getPreferredSize().getWidth());
		add(secondOwnerComboBox);

		secondOwnerLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);
		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth, secondOwnerLabelError.getPreferredSize().getWidth());
		add(secondOwnerLabelError);
	}

	private void populateNominee() {
		nomineeLabel = new BaseLabel(LabelConstants.LABEL_NOMINEE, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, nomineeLabel.getPreferredSize().getWidth());
		nomineeLabel.setLabelFor(nomineeComboBox);
		add(nomineeLabel);

		nomineeComboBox = new BaseComboBox<>(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, nomineeComboBox.getPreferredSize().getWidth());
		add(nomineeComboBox);

		nomineeLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);
		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth, nomineeLabelError.getPreferredSize().getWidth());
		add(nomineeLabelError);
	}

	private void populateInvestedAmount() {
		investedAmountLabel = new BaseLabel(LabelConstants.LABEL_INVESTED_AMOUNT, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, investedAmountLabel.getPreferredSize().getWidth());
		investedAmountLabel.setLabelFor(investedAmountField);
		add(investedAmountLabel);

		investedAmountField = new BaseTextField(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, investedAmountField.getPreferredSize().getWidth());
		add(investedAmountField);

		investedAmountLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);
		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth, investedAmountLabelError.getPreferredSize().getWidth());
		add(investedAmountLabelError);

		investedAmountValidator = new AmountValidator(investedAmountLabel.getText(), investedAmountField,
				investedAmountLabelError, true);
		investedAmountField.getDocument().addDocumentListener(investedAmountValidator);
	}

	private void populateMaturityAmount() {
		maturityAmountLabel = new BaseLabel(LabelConstants.LABEL_MATURITY_AMOUNT, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, maturityAmountLabel.getPreferredSize().getWidth());
		maturityAmountLabel.setLabelFor(maturityAmountField);
		add(maturityAmountLabel);

		maturityAmountField = new BaseTextField(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, maturityAmountField.getPreferredSize().getWidth());
		add(maturityAmountField);

		maturityAmountLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);
		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth, maturityAmountLabelError.getPreferredSize().getWidth());
		add(maturityAmountLabelError);

		maturityAmountValidator = new AmountValidator(maturityAmountLabel.getText(), maturityAmountField,
				maturityAmountLabelError, true);
		maturityAmountField.getDocument().addDocumentListener(maturityAmountValidator);
	}

	private void populateInvestmentDate() {
		investmentDateLabel = new BaseLabel(LabelConstants.LABEL_INVESTMENT_DATE, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, investmentDateLabel.getPreferredSize().getWidth());
		investmentDateLabel.setLabelFor(investmentDateField);
		add(investmentDateLabel);

		investmentDateField = new BaseTextField(ComponentEnum.VALUE);
		investmentDateField.setEnabled(false);
		add(investmentDateField);

		ImageIcon calendarIcon = BaseUtils.populateImage(ImageConstants.CALENDAR, 50, 50);
		investmentDateButton = new BaseButton(calendarIcon,
				new DatePickerListner(MainPage.getInstance(), investmentDateField), CommonConstants.BUTTON_PICK_DATE);
		add(investmentDateButton);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, investmentDateField.getPreferredSize().getWidth()
				+ investmentDateButton.getPreferredSize().getWidth() + widthPadding);

		investmentDateLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);
		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth, investmentDateLabelError.getPreferredSize().getWidth());
		add(investmentDateLabelError);

		investmentDateValidator = new DateValidator(investmentDateLabel.getText(), investmentDateField,
				investmentDateLabelError, true, "DD-MM-YYYY");
		investmentDateField.getDocument().addDocumentListener(investmentDateValidator);
	}

	private void populateMaturityDate() {
		maturityDateLabel = new BaseLabel(LabelConstants.LABEL_MATURITY_DATE, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, maturityDateLabel.getPreferredSize().getWidth());
		maturityDateLabel.setLabelFor(maturityDateField);
		add(maturityDateLabel);

		maturityDateField = new BaseTextField(ComponentEnum.VALUE);
		maturityDateField.setEnabled(false);
		add(maturityDateField);
		ImageIcon calendarIcon = BaseUtils.populateImage(ImageConstants.CALENDAR, 50, 50);
		maturityDateButton = new BaseButton(calendarIcon,
				new DatePickerListner(MainPage.getInstance(), maturityDateField), CommonConstants.BUTTON_PICK_DATE);
		add(maturityDateButton);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, maturityDateField.getPreferredSize().getWidth()
				+ maturityDateButton.getPreferredSize().getWidth() + widthPadding);

		maturityDateLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);
		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth, maturityDateLabelError.getPreferredSize().getWidth());
		add(maturityDateLabelError);

		maturityDateValidator = new DateValidator(maturityDateLabel.getText(), maturityDateField,
				maturityDateLabelError, true, "DD-MM-YYYY");
		maturityDateField.getDocument().addDocumentListener(maturityDateValidator);
	}

	private void populateRemark() {
		remarkLabel = new BaseLabel(LabelConstants.LABEL_REMARK, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, remarkLabel.getPreferredSize().getWidth());
		remarkLabel.setLabelFor(remarkField);
		add(remarkLabel);

		remarkField = new BaseTextField(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, remarkField.getPreferredSize().getWidth());
		add(remarkField);

		remarkLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);
		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth, remarkLabelError.getPreferredSize().getWidth());
		add(remarkLabelError);
	}

	private void populateStatus() {
		statusLabel = new BaseLabel(LabelConstants.LABEL_STATUS, ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, statusLabel.getPreferredSize().getWidth());
		statusLabel.setLabelFor(statusComboBox);
		add(statusLabel);

		statusComboBox = new BaseComboBox<>(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, statusComboBox.getPreferredSize().getWidth());
		statusComboBox.setEnabled(false);
		add(statusComboBox);
	}

	private void addButtonGrid() {
		buttonGridPanel = new ButtonGridPanel(heightPadding, widthPadding, rowHeight);

		ImageIcon cancelIcon = BaseUtils.populateImage(ImageConstants.CANCEL);
		cancelButton = new BaseButton(cancelIcon, new GoToHomePageListner(this), CommonConstants.BUTTON_CANCEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, cancelButton.getPreferredSize().getWidth());
		buttonGridPanel.addButtonToList(cancelButton);

		ImageIcon updateIcon = BaseUtils.populateImage(ImageConstants.UPDATE);
		mainButton = new BaseButton(updateIcon, null, CommonConstants.BUTTON_UPDATE);
		buttonGridPanel.addButtonToList(mainButton);

		ImageIcon copyIcon = BaseUtils.populateImage(ImageConstants.COPY);
		copyButton = new BaseButton(copyIcon, new CopyFdActionListner(this), CommonConstants.BUTTON_COPY);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, copyButton.getPreferredSize().getWidth());

		ImageIcon renewIcon = BaseUtils.populateImage(ImageConstants.RENEW);
		renewButton = new BaseButton(renewIcon, new InitiateRenewFdActionListner(this), CommonConstants.BUTTON_RENEW);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, renewButton.getPreferredSize().getWidth());

		ImageIcon withdrawIcon = BaseUtils.populateImage(ImageConstants.WITHDRAW);
		withdrawButton = new BaseButton(withdrawIcon, new WithdrawFdActionListner(this),
				CommonConstants.BUTTON_WITHDRAW);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, withdrawButton.getPreferredSize().getWidth());

		ImageIcon trackIcon = BaseUtils.populateImage(ImageConstants.TRACK);
		trackFdButton = new BaseButton(trackIcon, new TrackFdActionListner(this), CommonConstants.BUTTON_TRACK);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, trackFdButton.getPreferredSize().getWidth());

		add(buttonGridPanel.createPanel());
	}

	@Override
	public void arrangeComponents() {
		int fromLeft = baseDimension.getWidth();
		int fromTop = baseDimension.getHeight();

		/**
		 * FD Id Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(fdIdLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(fdIdField, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + secondColumnWidth + widthPadding;
		BaseUtils.setBound(fdIdLabelError, fromLeft, fromTop, thirdColumnWidth, rowHeight, 0, 0, baseDimension);

		/**
		 * FD Bank Reference Number Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(fdBankReferenceNumberLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0,
				baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(fdBankReferenceNumberField, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0,
				baseDimension);

		fromLeft = fromLeft + secondColumnWidth + widthPadding;
		BaseUtils.setBound(fdBankReferenceNumberLabelError, fromLeft, fromTop, thirdColumnWidth, rowHeight, 0, 0,
				baseDimension);

		/**
		 * FD Number Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(fdNumberLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(fdNumberField, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + secondColumnWidth + widthPadding;
		BaseUtils.setBound(fdNumberLabelError, fromLeft, fromTop, thirdColumnWidth, rowHeight, 0, 0, baseDimension);

		/**
		 * Starting FD Number Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(originalFdNumberLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(originalFdNumberField, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0, baseDimension);

		/**
		 * Renewed From FD Number Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(renewedFromFdNumberLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0,
				baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(renewedFromFdNumberField, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0,
				baseDimension);

		/**
		 * Renewed To FD Number Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(renewedToFdNumberLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(renewedToFdNumberField, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0,
				baseDimension);

		/**
		 * Bank Name Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(bankNameLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(bankNameComboBox, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + secondColumnWidth + widthPadding;
		BaseUtils.setBound(bankNameLabelError, fromLeft, fromTop, thirdColumnWidth, rowHeight, 0, 0, baseDimension);

		/**
		 * First Owner Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(firstOwnerLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(firstOwnerComboBox, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + secondColumnWidth + widthPadding;
		BaseUtils.setBound(firstOwnerLabelError, fromLeft, fromTop, thirdColumnWidth, rowHeight, 0, 0, baseDimension);

		/**
		 * Second Owner Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(secondOwnerLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(secondOwnerComboBox, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + secondColumnWidth + widthPadding;
		BaseUtils.setBound(secondOwnerLabelError, fromLeft, fromTop, thirdColumnWidth, rowHeight, 0, 0, baseDimension);

		/**
		 * Nominee Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(nomineeLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(nomineeComboBox, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + secondColumnWidth + widthPadding;
		BaseUtils.setBound(nomineeLabelError, fromLeft, fromTop, thirdColumnWidth, rowHeight, 0, 0, baseDimension);

		/**
		 * Investment Amount Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(investedAmountLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(investedAmountField, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + secondColumnWidth + widthPadding;
		BaseUtils.setBound(investedAmountLabelError, fromLeft, fromTop, thirdColumnWidth, rowHeight, 0, 0,
				baseDimension);

		/**
		 * Maturity Amount Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(maturityAmountLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(maturityAmountField, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + secondColumnWidth + widthPadding;
		BaseUtils.setBound(maturityAmountLabelError, fromLeft, fromTop, thirdColumnWidth, rowHeight, 0, 0,
				baseDimension);

		/**
		 * Investment Date Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(investmentDateLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		List<Component> componentList = new ArrayList<>();
		componentList.add(investmentDateField);
		componentList.add(investmentDateButton);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		baseDimension = BaseUtils.arrangeComponentInRow(componentList, secondColumnWidth, widthPadding, 2, fromLeft,
				fromTop, rowHeight, heightPadding, baseDimension);

		fromLeft = fromLeft + secondColumnWidth + widthPadding;
		BaseUtils.setBound(investmentDateLabelError, fromLeft, fromTop, thirdColumnWidth, rowHeight, 0, 0,
				baseDimension);

		/**
		 * Maturity Date Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(maturityDateLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		componentList = new ArrayList<>();
		componentList.add(maturityDateField);
		componentList.add(maturityDateButton);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		baseDimension = BaseUtils.arrangeComponentInRow(componentList, secondColumnWidth, widthPadding, 2, fromLeft,
				fromTop, rowHeight, heightPadding, baseDimension);

		fromLeft = fromLeft + secondColumnWidth + widthPadding;
		BaseUtils.setBound(maturityDateLabelError, fromLeft, fromTop, thirdColumnWidth, rowHeight, 0, 0, baseDimension);

		/**
		 * Status Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(statusLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(statusComboBox, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0, baseDimension);

		/**
		 * Remark Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(remarkLabel, fromLeft, fromTop, firstColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + firstColumnWidth + widthPadding;
		BaseUtils.setBound(remarkField, fromLeft, fromTop, secondColumnWidth, rowHeight, 0, 0, baseDimension);

		fromLeft = fromLeft + secondColumnWidth + widthPadding;
		BaseUtils.setBound(remarkLabelError, fromLeft, fromTop, thirdColumnWidth, rowHeight, 0, 0, baseDimension);

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
		populateFDId();
		populateFdBankReferenceNumber();
		populateBankName();
		populateFDNumber();
		populateOriginalFDNumber();
		populateRenewedFromFDNumber();
		populateRenewedToFDNumber();
		populateFirstOwnerName();
		populateSecondOwner();
		populateNominee();
		populateInvestedAmount();
		populateMaturityAmount();
		populateInvestmentDate();
		populateMaturityDate();
		populateStatus();
		populateRemark();
		addButtonGrid();

		return this;
	}

	@Override
	public void loadPanelData() {
		fdIdField.setText(fdVO.getFdId());
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, fdIdField.getPreferredSize().getWidth());

		fdBankReferenceNumberField.setText(fdVO.getFdBankReferenceNumber());
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth,
				fdBankReferenceNumberField.getPreferredSize().getWidth());

		fdNumberField.setText(fdVO.getFdNumber());
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, fdNumberField.getPreferredSize().getWidth());

		originalFdNumberField.setText(fdVO.getOriginalFdNumber());
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, originalFdNumberField.getPreferredSize().getWidth());

		renewedFromFdNumberField.setText(fdVO.getRenewedFrom());
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, renewedFromFdNumberField.getPreferredSize().getWidth());

		renewedToFdNumberField.setText(fdVO.getRenewedTo());
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, renewedToFdNumberField.getPreferredSize().getWidth());

		bankNameComboBox.setSelectItemList(
				SelectItemConvertor.populateBankSelectItemList(FDMSCache.getInstance().getBankVOList()));
		bankNameComboBox.setSelectedItem(SelectItemConvertor.populateBankSelectItem(fdVO.getBankVO()));

		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, bankNameComboBox.getPreferredSize().getWidth());

		firstOwnerComboBox.setSelectItemList(
				SelectItemConvertor.populatePersonSelectItemList(FDMSCache.getInstance().getPersonVOList()));
		firstOwnerComboBox.setSelectedItem(SelectItemConvertor.populatePersonSelectItem(fdVO.getFirstOwnerVO()));
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, firstOwnerComboBox.getPreferredSize().getWidth());

		secondOwnerComboBox.setSelectItemList(
				SelectItemConvertor.populatePersonSelectItemList(FDMSCache.getInstance().getPersonVOList()));
		secondOwnerComboBox.setSelectedItem(SelectItemConvertor.populatePersonSelectItem(fdVO.getSecondOwnerVO()));
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, secondOwnerComboBox.getPreferredSize().getWidth());

		nomineeComboBox.setSelectItemList(
				SelectItemConvertor.populatePersonSelectItemList(FDMSCache.getInstance().getPersonVOList()));
		nomineeComboBox.setSelectedItem(SelectItemConvertor.populatePersonSelectItem(fdVO.getNomineeVO()));
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, nomineeComboBox.getPreferredSize().getWidth());

		investedAmountField.setText(fdVO.getInvestedAmount());
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, investedAmountField.getPreferredSize().getWidth());

		maturityAmountField.setText(fdVO.getMaturityAmount());
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, maturityAmountField.getPreferredSize().getWidth());

		investmentDateField.setText(DateUtilities.parseDateToString(fdVO.getInvestmentDate(), FDMConstant.DATE_FORMAT));
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, investmentDateField.getPreferredSize().getWidth()
				+ investmentDateButton.getPreferredSize().getWidth() + widthPadding);

		maturityDateField.setText(DateUtilities.parseDateToString(fdVO.getMaturityDate(), FDMConstant.DATE_FORMAT));
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, maturityDateField.getPreferredSize().getWidth()
				+ maturityDateButton.getPreferredSize().getWidth() + widthPadding);

		statusComboBox.setSelectItemList(
				SelectItemConvertor.populateStatusSelectItemList(FDMSCache.getInstance().getStatusVOList()));
		statusComboBox.setSelectedItem(SelectItemConvertor.populateStatusSelectItem(fdVO.getStatusVO()));
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, statusComboBox.getPreferredSize().getWidth());

		remarkField.setText(fdVO.getRemark());
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, remarkField.getPreferredSize().getWidth());

		applyRights();
		arrangeComponents();
		prepareTabOutOrderList();
	}

	@Override
	public IPanelBean getPanelDataOnSubmit() {
		FdPanelBean fdPanelBean = new FdPanelBean();
		FdVO panelFdVO = new FdVO();
		if (null != fdIdField.getText()) {
			panelFdVO.setFdId(Integer.parseInt(fdIdField.getText()));
		}

		if (null != fdBankReferenceNumberField.getText()) {
			panelFdVO.setFdBankReferenceNumber(Integer.parseInt(fdBankReferenceNumberField.getText()));
		}

		if (null != originalFdNumberField.getText()) {
			panelFdVO.setOriginalFdNumber(Integer.parseInt(originalFdNumberField.getText()));
		}

		if (null != renewedFromFdNumberField.getText()) {
			panelFdVO.setRenewedFrom(Integer.parseInt(renewedFromFdNumberField.getText()));
		}

		if (null != renewedToFdNumberField.getText()) {
			panelFdVO.setRenewedTo(Integer.parseInt(renewedToFdNumberField.getText()));
		}

		panelFdVO.setFdNumber(fdNumberField.getText());

		if (NumericUtilities.isInteger(bankNameComboBox.getSelectedValue())) {
			BankVO bankVO = new BankVO();
			bankVO.setBankId(Integer.parseInt(bankNameComboBox.getSelectedValue()));
			panelFdVO.setBankVO(bankVO);
		}

		if (NumericUtilities.isInteger(firstOwnerComboBox.getSelectedValue())) {
			PersonVO firstOwner = new PersonVO();
			firstOwner.setPersonId(Integer.parseInt(firstOwnerComboBox.getSelectedValue()));
			panelFdVO.setFirstOwnerVO(firstOwner);
		}
		if (NumericUtilities.isInteger(secondOwnerComboBox.getSelectedValue())) {
			PersonVO secondOwner = new PersonVO();
			secondOwner.setPersonId(Integer.parseInt(secondOwnerComboBox.getSelectedValue()));
			panelFdVO.setSecondOwnerVO(secondOwner);
		}

		if (NumericUtilities.isInteger(nomineeComboBox.getSelectedValue())) {
			PersonVO nomineeVO = new PersonVO();
			nomineeVO.setPersonId(Integer.parseInt(nomineeComboBox.getSelectedValue()));
			panelFdVO.setNomineeVO(nomineeVO);
		}

		if (NumericUtilities.isAmount(investedAmountField.getText())) {
			panelFdVO.setInvestedAmount(Float.parseFloat(investedAmountField.getText()));
		}

		if (NumericUtilities.isAmount(maturityAmountField.getText())) {
			panelFdVO.setMaturityAmount(Float.parseFloat(maturityAmountField.getText()));
		}

		panelFdVO.setInvestmentDate(
				DateUtilities.parseStringToDate(investmentDateField.getText(), FDMConstant.DATE_FORMAT));
		panelFdVO
				.setMaturityDate(DateUtilities.parseStringToDate(maturityDateField.getText(), FDMConstant.DATE_FORMAT));
		panelFdVO.setRemark(remarkField.getText());
		panelFdVO.setStatusVO(FDMSCache.getInstance().getStatusVOByCode(statusComboBox.getSelectedValue()));

		fdPanelBean.setFdVO(panelFdVO);
		return fdPanelBean;
	}

	@Override
	public boolean validatePanelData() {
		boolean isSearchMode = isSearchMode();
		boolean fdIdResult = fdIdValidator.validateOnSubmit();
		boolean fdBankReferenceNumberResult = fdBankReferenceNumberValidator.validateOnSubmit();
		boolean fdNumberResult = fdNumberValidator.validateOnSubmit();
		boolean bankNameResult = true;

		boolean firstOwnerResult = true;
		boolean ownerSimilarityResult = true;

		if (!isSearchMode) {
			bankNameResult = FDMSValidator.validateBaseComboBox(bankNameComboBox, bankNameLabelError,
					bankNameLabel.getText());
			firstOwnerResult = FDMSValidator.validateBaseComboBox(firstOwnerComboBox, firstOwnerLabelError,
					firstOwnerLabel.getText());

			if (firstOwnerResult) {
				ownerSimilarityResult = FDMSValidator.validateSimilarOwner(firstOwnerComboBox, firstOwnerLabelError,
						firstOwnerLabel.getText(), secondOwnerComboBox, secondOwnerLabelError,
						secondOwnerLabel.getText());
				if (ownerSimilarityResult) {
					ownerSimilarityResult = FDMSValidator.validateSimilarOwner(firstOwnerComboBox, firstOwnerLabelError,
							firstOwnerLabel.getText(), nomineeComboBox, nomineeLabelError, nomineeLabel.getText());
					if (ownerSimilarityResult) {
						ownerSimilarityResult = FDMSValidator.validateSimilarOwner(secondOwnerComboBox,
								secondOwnerLabelError, secondOwnerLabel.getText(), nomineeComboBox, nomineeLabelError,
								nomineeLabel.getText());
					}
				}
			}
		}

		boolean investedAmountResult = investedAmountValidator.validateOnSubmit();

		boolean maturityAmountResult = maturityAmountValidator.validateOnSubmit();

		boolean investmentDateResult = investmentDateValidator.validateOnSubmit();

		boolean maturityDateResult = maturityDateValidator.validateOnSubmit();

		boolean compareDateResult = true;
		if (investmentDateResult && maturityDateResult && maturityDateValidator.isMandatory()
				&& investmentDateValidator.isMandatory()) {
			compareDateResult = FDMSValidator.compareStartAndEndDate(investmentDateField, investmentDateLabelError,
					investmentDateLabel.getText(), maturityDateField, maturityDateLabelError,
					maturityAmountLabel.getText());
		}
		return fdIdResult && fdBankReferenceNumberResult && fdNumberResult && bankNameResult && firstOwnerResult
				&& investedAmountResult && maturityAmountResult && investmentDateResult && maturityDateResult
				&& ownerSimilarityResult && compareDateResult;
	}

	public void applyRights() {
		switch (screenMode) {
		case CREATE:
			applyCreateRights();
			break;

		case EDIT:
			applyEditRights();
			break;

		case SEARCH:
			applySearchRights();
			break;

		case VIEW:
			applyViewRights();
			break;

		case RENEW:
			applyRenewRights();
			break;
		default:
			break;

		}
	}

	private void applyRenewRights() {
		setTitle(HeaderConstants.RENEW_FD);
		ImageIcon renewIcon = BaseUtils.populateImage(ImageConstants.RENEW);
		mainButton.setAllValues(renewIcon, new RenewFdActionListner(this), CommonConstants.BUTTON_RENEW);

		fdIdField.setEnabled(false);
		fdIdValidator.setMandatory(false);

		fdBankReferenceNumberField.setEnabled(false);
		fdBankReferenceNumberValidator.setMandatory(false);

		fdNumberField.setEnabled(true);
		fdNumberValidator.setMandatory(true);

		bankNameComboBox.setEnabled(false);

		firstOwnerComboBox.setEnabled(true);

		secondOwnerComboBox.setEnabled(true);

		nomineeComboBox.setEnabled(true);

		investedAmountField.setEnabled(true);
		investedAmountValidator.setMandatory(true);

		maturityAmountField.setEnabled(true);
		maturityAmountValidator.setMandatory(true);

		investmentDateButton.setEnabled(true);
		investmentDateValidator.setMandatory(true);

		maturityDateButton.setEnabled(true);
		maturityDateValidator.setMandatory(true);

		remarkField.setEnabled(true);
	}

	/**
	 * 
	 */
	private void applyViewRights() {
		setTitle(HeaderConstants.SHOW_FD);
		ImageIcon editIcon = BaseUtils.populateImage(ImageConstants.EDIT);
		mainButton.setAllValues(editIcon, new EditFdActionListner(this), CommonConstants.BUTTON_EDIT);

		if ((FdStatusEnum.CURRENT.getCode().equalsIgnoreCase(fdVO.getStatusVO().getCode()))
				&& (fdVO.getMaturityDate().compareTo(Calendar.getInstance().getTime()) < 0)) {
			buttonGridPanel.addButtonToList(renewButton);
		}
		if (FdStatusEnum.CURRENT.getCode().equalsIgnoreCase(fdVO.getStatusVO().getCode())) {
			buttonGridPanel.addButtonToList(withdrawButton);
		}

		buttonGridPanel.addButtonToList(copyButton);
		buttonGridPanel.addButtonToList(trackFdButton);

		fdIdField.setEnabled(false);
		fdIdValidator.setMandatory(false);

		fdBankReferenceNumberField.setEnabled(false);
		fdBankReferenceNumberValidator.setMandatory(false);

		fdNumberField.setEnabled(false);
		fdNumberValidator.setMandatory(true);

		bankNameComboBox.setEnabled(false);

		firstOwnerComboBox.setEnabled(false);

		secondOwnerComboBox.setEnabled(false);

		nomineeComboBox.setEnabled(false);

		investedAmountField.setEnabled(false);
		investedAmountValidator.setMandatory(false);

		maturityAmountField.setEnabled(false);
		maturityAmountValidator.setMandatory(false);

		investmentDateButton.setEnabled(false);
		maturityDateButton.setEnabled(false);

		remarkField.setEnabled(false);

	}

	/**
	 * 
	 */
	private void applySearchRights() {
		setTitle(HeaderConstants.SEARCH_FD);
		ImageIcon searchIcon = BaseUtils.populateImage(ImageConstants.SEARCH);
		mainButton.setAllValues(searchIcon, new SearchFdActionListner(this), CommonConstants.BUTTON_SEARCH);

		fdIdField.setEnabled(true);
		fdIdValidator.setMandatory(false);

		fdBankReferenceNumberField.setEnabled(true);
		fdBankReferenceNumberValidator.setMandatory(false);

		fdNumberField.setEnabled(true);
		fdNumberValidator.setMandatory(false);

		bankNameComboBox.setEnabled(true);

		firstOwnerComboBox.setEnabled(true);

		secondOwnerComboBox.setEnabled(true);

		nomineeComboBox.setEnabled(true);

		investedAmountField.setEnabled(true);
		investedAmountValidator.setMandatory(false);

		maturityAmountField.setEnabled(true);
		maturityAmountValidator.setMandatory(false);

		statusComboBox.setEnabled(true);

		investmentDateButton.setEnabled(true);
		investmentDateValidator.setMandatory(false);

		maturityDateButton.setEnabled(true);
		maturityDateValidator.setMandatory(false);

		remarkField.setEnabled(true);
	}

	/**
	 * 
	 */
	private void applyEditRights() {
		setTitle(HeaderConstants.EDIT_FD);
		ImageIcon updateIcon = BaseUtils.populateImage(ImageConstants.UPDATE);
		mainButton.setAllValues(updateIcon, new UpdateFdActionListner(this), CommonConstants.BUTTON_UPDATE);

		fdIdField.setEnabled(false);
		fdIdValidator.setMandatory(false);

		fdBankReferenceNumberField.setEnabled(false);
		fdBankReferenceNumberValidator.setMandatory(false);

		fdNumberField.setEnabled(true);
		fdNumberValidator.setMandatory(true);

		bankNameComboBox.setEnabled(false);

		firstOwnerComboBox.setEnabled(true);

		secondOwnerComboBox.setEnabled(true);

		nomineeComboBox.setEnabled(true);

		investedAmountField.setEnabled(true);
		investedAmountValidator.setMandatory(true);

		maturityAmountField.setEnabled(true);
		maturityAmountValidator.setMandatory(true);

		if (FdStatusEnum.CURRENT.getCode().equalsIgnoreCase(fdVO.getStatusVO().getCode())) {
			investmentDateButton.setEnabled(true);
			maturityDateButton.setEnabled(true);
		} else {
			investmentDateButton.setEnabled(false);
			maturityDateButton.setEnabled(false);
		}

		investmentDateValidator.setMandatory(true);
		maturityDateValidator.setMandatory(true);

		statusComboBox.setEnabled(false);

		remarkField.setEnabled(true);
	}

	/**
	 * 
	 */
	private void applyCreateRights() {
		setTitle(HeaderConstants.ADD_FD);
		ImageIcon addIcon = BaseUtils.populateImage(ImageConstants.ADD);
		mainButton.setAllValues(addIcon, new AddFdActionListner(this), CommonConstants.BUTTON_ADD);

		fdIdField.setEnabled(false);
		fdIdValidator.setMandatory(false);

		fdBankReferenceNumberField.setEnabled(false);
		fdBankReferenceNumberValidator.setMandatory(false);

		fdNumberField.setEnabled(true);
		fdNumberValidator.setMandatory(true);

		bankNameComboBox.setEnabled(true);

		firstOwnerComboBox.setEnabled(true);

		secondOwnerComboBox.setEnabled(true);

		nomineeComboBox.setEnabled(true);

		investedAmountField.setEnabled(true);
		investedAmountValidator.setMandatory(true);

		maturityAmountField.setEnabled(true);
		maturityAmountValidator.setMandatory(true);

		investmentDateButton.setEnabled(true);
		investmentDateValidator.setMandatory(true);

		maturityDateButton.setEnabled(true);
		maturityDateValidator.setMandatory(true);

		statusComboBox.setEnabled(false);
		remarkField.setEnabled(true);
	}

	private boolean isSearchMode() {
		return (ScreenMode.SEARCH.equals(screenMode));
	}

	@Override
	public void prepareTabOutOrderList() {
		List<Component> components = new ArrayList<>();
		components.add(fdNumberField);
		components.add(fdIdField);
		components.add(fdBankReferenceNumberField);
		components.add(bankNameComboBox);
		components.add(firstOwnerComboBox);
		components.add(secondOwnerComboBox);
		components.add(nomineeComboBox);
		components.add(investedAmountField);
		components.add(maturityAmountField);
		components.add(investmentDateButton);
		components.add(maturityDateButton);
		components.add(remarkField);
		components.add(mainButton);
		components.add(cancelButton);
		components.add(copyButton);
		components.add(trackFdButton);
		components.add(withdrawButton);
		components.add(renewButton);
		setTabOutOrderList(components);
	}

	/**
	 * @param fdVO
	 *            the fdVO to set
	 */
	public void setFdVO(FdVO fdVO) {
		this.fdVO = fdVO;
	}

	/**
	 * @param screenMode
	 *            the screenMode to set
	 */
	public void setScreenMode(ScreenMode screenMode) {
		this.screenMode = screenMode;
	}
}
