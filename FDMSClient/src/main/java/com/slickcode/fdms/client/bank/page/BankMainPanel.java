package com.slickcode.fdms.client.bank.page;

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
import com.slickcode.fdms.client.bank.listner.AddBankActionListner;
import com.slickcode.fdms.client.bank.listner.CopyBankActionListner;
import com.slickcode.fdms.client.bank.listner.EditBankActionListner;
import com.slickcode.fdms.client.bank.listner.FetchBankRelatedActionListner;
import com.slickcode.fdms.client.bank.listner.SearchBankActionListner;
import com.slickcode.fdms.client.bank.listner.UpdateBankActionListner;
import com.slickcode.fdms.client.constants.ButtonConstants;
import com.slickcode.fdms.client.constants.HeaderConstants;
import com.slickcode.fdms.client.constants.ImageConstants;
import com.slickcode.fdms.client.constants.LabelConstants;
import com.slickcode.fdms.client.login.listner.GoToHomePageListner;
import com.slickcode.fdms.client.utils.ButtonGridPanel;
import com.slickcode.fdms.client.utils.ScreenMode;
import com.slickcode.fdms.common.bean.BankPanelBean;
import com.slickcode.fdms.common.vo.BankVO;

public class BankMainPanel extends BasePanel {

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

	private BaseLabel bankIdLabel;
	private BaseTextField bankIdField;
	private BaseLabel bankIdLabelError;
	private transient NumericValidator bankIdValidator;

	private BaseLabel bankNameLabel;
	private BaseTextField bankNameField;
	private BaseLabel bankNameLabelError;
	private transient NonEmtryValidator bankNameValidator;

	private BaseLabel branchLabel;
	private BaseTextField branchField;
	private BaseLabel branchLabelError;
	private transient NonEmtryValidator branchValidator;

	private ButtonGridPanel buttonGridPanel;
	private BaseButton mainButton;
	private BaseButton copyButton;
	private BaseButton relatedFdsButton;
	private BaseButton cancelButton;

	private BankVO bankVO;
	private ScreenMode screenMode;

	private void populateButtons() {
		buttonGridPanel = new ButtonGridPanel(heightPadding, widthPadding,
				rowHeight);
		
		ImageIcon cancelIcon = BaseUtils.populateImage(
				ImageConstants.CANCEL);
		this.cancelButton = new BaseButton(cancelIcon,
				new GoToHomePageListner(this), ButtonConstants.CANCEL);
		this.firstColumnWidth = BaseUtils.getMax(this.firstColumnWidth,
				this.cancelButton.getPreferredSize().getWidth());
		buttonGridPanel.addButtonToList(this.cancelButton);

		ImageIcon searchIcon = BaseUtils.populateImage(
				ImageConstants.SEARCH);
		this.mainButton = new BaseButton(searchIcon, null,
				ButtonConstants.SEARCH);
		this.secondColumnWidth = BaseUtils.getMax(this.secondColumnWidth,
				this.mainButton.getPreferredSize().getWidth());
		buttonGridPanel.addButtonToList(this.mainButton);

		ImageIcon copyIcon = BaseUtils.populateImage(
				ImageConstants.COPY);
		copyButton = new BaseButton(copyIcon,
				new CopyBankActionListner(this), ButtonConstants.COPY);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, copyButton
				.getPreferredSize().getWidth());

		ImageIcon relatedFdsIcon = BaseUtils.populateImage(
				ImageConstants.RELATED_FDS);
		relatedFdsButton = new BaseButton(relatedFdsIcon,
				new FetchBankRelatedActionListner(this),
				ButtonConstants.RELATED_FDS);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth,
				relatedFdsButton.getPreferredSize().getWidth());

		add(buttonGridPanel.createPanel());
	}

	@Override
	public void arrangeComponents() {
		/**
		 * Bank Id Row
		 */
		int fromLeft = this.widthPadding;
		int fromTop = this.baseDimension.getHeight() + this.heightPadding;
		BaseUtils.setBound(this.bankIdLabel, fromLeft, fromTop,
				this.firstColumnWidth, this.rowHeight, 0, 0,
				this.baseDimension);

		fromLeft = fromLeft + this.firstColumnWidth + this.widthPadding;
		BaseUtils.setBound(this.bankIdField, fromLeft, fromTop,
				this.secondColumnWidth, this.rowHeight, 0, 0,
				this.baseDimension);

		fromLeft = fromLeft + this.secondColumnWidth + this.widthPadding;
		BaseUtils.setBound(this.bankIdLabelError, fromLeft, fromTop,
				this.thirdColumnWidth, this.rowHeight, 0, 0,
				this.baseDimension);
		
		/**
		 * Bank Name Row
		 */
		fromLeft = this.widthPadding;
		fromTop = this.baseDimension.getHeight() + this.heightPadding;
		BaseUtils.setBound(this.bankNameLabel, fromLeft, fromTop,
				this.firstColumnWidth, this.rowHeight, 0, 0,
				this.baseDimension);

		fromLeft = fromLeft + this.firstColumnWidth + this.widthPadding;
		BaseUtils.setBound(this.bankNameField, fromLeft, fromTop,
				this.secondColumnWidth, this.rowHeight, 0, 0,
				this.baseDimension);

		fromLeft = fromLeft + this.secondColumnWidth + this.widthPadding;
		BaseUtils.setBound(this.bankNameLabelError, fromLeft, fromTop,
				this.thirdColumnWidth, this.rowHeight, 0, 0,
				this.baseDimension);

		/**
		 * Branch Row
		 */
		fromLeft = this.widthPadding;
		fromTop = this.baseDimension.getHeight() + this.heightPadding;
		BaseUtils.setBound(this.branchLabel, fromLeft, fromTop,
				this.firstColumnWidth, this.rowHeight, 0, 0,
				this.baseDimension);

		fromLeft = fromLeft + this.firstColumnWidth + this.widthPadding;
		BaseUtils.setBound(this.branchField, fromLeft, fromTop,
				this.secondColumnWidth, this.rowHeight, 0, 0,
				this.baseDimension);

		fromLeft = fromLeft + this.secondColumnWidth + this.widthPadding;
		BaseUtils.setBound(this.branchLabelError, fromLeft, fromTop,
				this.thirdColumnWidth, this.rowHeight, 0, 0,
				this.baseDimension);

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
		populateBankId();
		populateBankName();
		populateBranch();
		populateButtons();
		return this;
	}

	/**
	 * 
	 */
	private void populateBranch() {
		this.branchLabel = new BaseLabel(LabelConstants.BRANCH,
				ComponentEnum.LABEL);
		this.firstColumnWidth = BaseUtils.getMax(this.firstColumnWidth,
				this.branchLabel.getPreferredSize().getWidth());
		this.branchLabel.setLabelFor(this.branchField);
		add(this.branchLabel);

		this.branchField = new BaseTextField(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, branchField
				.getPreferredSize().getWidth());
		add(branchField);

		branchLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);
		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth, branchLabelError
				.getPreferredSize().getWidth());
		add(branchLabelError);

		branchValidator = new NonEmtryValidator(branchLabel.getText(),
				branchField, branchLabelError, true);
		branchField.getDocument().addDocumentListener(branchValidator);
	}

	/**
	 * 
	 */
	private void populateBankName() {
		bankNameLabel = new BaseLabel(LabelConstants.BANK_NAME,
				ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, bankNameLabel
				.getPreferredSize().getWidth());
		bankNameLabel.setLabelFor(bankNameField);
		add(bankNameLabel);

		bankNameField = new BaseTextField(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, bankNameField
				.getPreferredSize().getWidth());
		add(bankNameField);

		bankNameLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);
		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth,
				bankNameLabelError.getPreferredSize().getWidth());
		add(bankNameLabelError);

		bankNameValidator = new NonEmtryValidator(bankNameLabel.getText(),
				bankNameField, bankNameLabelError, true);
		bankNameField.getDocument().addDocumentListener(bankNameValidator);
	}

	/**
	 * 
	 */
	private void populateBankId() {
		bankIdLabel = new BaseLabel(LabelConstants.BANK_ID,
				ComponentEnum.LABEL);
		firstColumnWidth = BaseUtils.getMax(firstColumnWidth, bankIdLabel
				.getPreferredSize().getWidth());
		bankIdLabel.setLabelFor(bankIdField);
		add(bankIdLabel);

		bankIdField = new BaseTextField(ComponentEnum.VALUE);
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, bankIdField
				.getPreferredSize().getWidth());
		add(bankIdField);

		bankIdLabelError = new BaseLabel(ComponentEnum.NO_ERROR_IMAGE);
		thirdColumnWidth = BaseUtils.getMax(thirdColumnWidth, bankIdLabelError
				.getPreferredSize().getWidth());
		add(bankIdLabelError);

		bankIdValidator = new NumericValidator(bankIdLabel.getText(),
				bankIdField, bankIdLabelError, true);
		bankIdField.getDocument().addDocumentListener(bankIdValidator);
	}

	@Override
	public void loadPanelData() {
		bankIdField.setText(bankVO.getBankId());
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, bankIdField
				.getPreferredSize().getWidth());

		bankNameField.setText(bankVO.getName());
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, bankNameField
				.getPreferredSize().getWidth());

		branchField.setText(bankVO.getBranch());
		secondColumnWidth = BaseUtils.getMax(secondColumnWidth, branchField
				.getPreferredSize().getWidth());

		applyRights();
		arrangeComponents();
		prepareTabOutOrderList();
	}

	@Override
	public IPanelBean getPanelDataOnSubmit() {
		BankVO panelBankVO = new BankVO();
		panelBankVO.setName(bankNameField.getText());
		panelBankVO.setBranch(branchField.getText());

		if (null != bankIdField.getText()) {
			panelBankVO.setBankId(Integer.parseInt(bankIdField.getText()));
		}

		BankPanelBean bankPanelBean = new BankPanelBean();
		bankPanelBean.setBankVO(panelBankVO);

		return bankPanelBean;
	}

	@Override
	public boolean validatePanelData() {
		boolean bankIdResult = bankIdValidator.validateOnSubmit();
		boolean bankNameResult = bankNameValidator.validateOnSubmit();
		boolean branchResult = branchValidator.validateOnSubmit();
		return bankIdResult && bankNameResult && branchResult;
	}

	public void applyRights() {
		switch (screenMode) {
		case CREATE:
			bankIdField.setEditable(false);
			bankIdValidator.setMandatory(false);
			bankNameField.setEditable(true);
			bankNameValidator.setMandatory(true);
			branchField.setEditable(true);
			branchValidator.setMandatory(true);
			setTitle(HeaderConstants.ADD_BANK);
			ImageIcon addIcon = BaseUtils.populateImage(
					ImageConstants.ADD);
			mainButton.setAllValues(addIcon,
					new AddBankActionListner(this), ButtonConstants.ADD);
			break;

		case EDIT:
			bankIdField.setEditable(false);
			bankIdValidator.setMandatory(false);
			bankNameField.setEditable(true);
			bankNameValidator.setMandatory(true);
			branchField.setEditable(true);
			branchValidator.setMandatory(true);
			setTitle(HeaderConstants.EDIT_BANK);
			ImageIcon updateIcon = BaseUtils.populateImage(
					ImageConstants.UPDATE);
			mainButton.setAllValues(updateIcon,
					new UpdateBankActionListner(this),
					ButtonConstants.UPDATE);
			break;

		case SEARCH:
			bankIdField.setEditable(true);
			bankIdValidator.setMandatory(false);
			bankNameField.setEditable(true);
			bankNameValidator.setMandatory(false);
			branchField.setEditable(true);
			branchValidator.setMandatory(false);
			setTitle(HeaderConstants.SEARCH_BANK);
			ImageIcon searchIcon = BaseUtils.populateImage(
					ImageConstants.SEARCH);
			mainButton.setAllValues(searchIcon,
					new SearchBankActionListner(this),
					ButtonConstants.SEARCH);
			break;

		case VIEW:
			bankIdField.setEditable(false);
			bankIdValidator.setMandatory(false);
			bankNameField.setEditable(false);
			bankNameValidator.setMandatory(false);
			branchField.setEditable(false);
			branchValidator.setMandatory(false);
			setTitle(HeaderConstants.SHOW_BANK);
			ImageIcon editIcon = BaseUtils.populateImage(
					ImageConstants.EDIT);
			mainButton.setAllValues(editIcon,
					new EditBankActionListner(this),
					ButtonConstants.EDIT);
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
		List<Component> components = new ArrayList<>();
		components.add(bankIdField);
		components.add(bankNameField);
		components.add(branchField);
		components.add(mainButton);
		components.add(cancelButton);
		components.add(copyButton);
		components.add(relatedFdsButton);
		
		setTabOutOrderList(components);
	}

	/**
	 * @param bankVO the bankVO to set
	 */
	public void setBankVO(BankVO bankVO) {
		this.bankVO = bankVO;
	}

	/**
	 * @param screenMode the screenMode to set
	 */
	public void setScreenMode(ScreenMode screenMode) {
		this.screenMode = screenMode;
	}
	
	
}
