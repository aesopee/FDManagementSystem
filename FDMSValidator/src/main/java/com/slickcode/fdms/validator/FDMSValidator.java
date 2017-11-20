package com.slickcode.fdms.validator;

import java.util.Date;

import com.slickcode.baseframework.components.BaseComboBox;
import com.slickcode.baseframework.components.BaseLabel;
import com.slickcode.baseframework.components.BasePasswordField;
import com.slickcode.baseframework.components.BaseTextField;
import com.slickcode.baseframework.domain.SelectItem;
import com.slickcode.baseframework.utils.ComponentEnum;
import com.slickcode.basevalidatorframework.DateUtilities;
import com.slickcode.fdms.common.constant.FDMConstant;
import com.slickcode.fdms.common.utils.SelectItemConvertor;

public class FDMSValidator {
	public static boolean validateBaseComboBox(
			BaseComboBox<SelectItem> baseComboBox, BaseLabel errorLabel,
			String label) {
		if ((null == baseComboBox.getSelectedValue())
				|| (SelectItemConvertor.SELECT_ITEM_DEFAULT.getValue()
						.equalsIgnoreCase(baseComboBox.getSelectedValue()))) {
			errorLabel.addError("Please select " + label);
			baseComboBox.changeComponentLayout(ComponentEnum.ERROR);
			return false;
		} else {
			errorLabel.addNoError();
			baseComboBox.changeComponentLayout(ComponentEnum.VALUE);
			return true;
		}
	}

	public static boolean validateSimilarOwner(
			BaseComboBox<SelectItem> firstBaseComboBox,
			BaseLabel firstErrorLabel, String firstLabel,
			BaseComboBox<SelectItem> secondBaseComboBox,
			BaseLabel secondErrorLabel, String secondLabel) {
		if ((null == firstBaseComboBox.getSelectedValue())
				|| (SelectItemConvertor.SELECT_ITEM_DEFAULT.getValue()
						.equalsIgnoreCase(firstBaseComboBox.getSelectedValue()))) {
			firstErrorLabel.addNoError();
			firstBaseComboBox.changeComponentLayout(ComponentEnum.VALUE);
			secondErrorLabel.addNoError();
			secondBaseComboBox.changeComponentLayout(ComponentEnum.VALUE);
			return true;
		} else if ((null == secondBaseComboBox.getSelectedValue())
				|| (SelectItemConvertor.SELECT_ITEM_DEFAULT.getValue()
						.equalsIgnoreCase(secondBaseComboBox.getSelectedValue()))) {
			firstErrorLabel.addNoError();
			firstBaseComboBox.changeComponentLayout(ComponentEnum.VALUE);
			secondErrorLabel.addNoError();
			secondBaseComboBox.changeComponentLayout(ComponentEnum.VALUE);
			return true;
		} else if (firstBaseComboBox.getSelectedValue().equals(
				secondBaseComboBox.getSelectedValue())) {
			firstErrorLabel.addError(firstLabel + " and " + secondLabel
					+ " cannot be same.");
			firstBaseComboBox.changeComponentLayout(ComponentEnum.ERROR);
			secondErrorLabel.addError(firstLabel + " and " + secondLabel
					+ " cannot be same.");
			secondBaseComboBox.changeComponentLayout(ComponentEnum.ERROR);
			return false;
		} else {
			firstErrorLabel.addNoError();
			firstBaseComboBox.changeComponentLayout(ComponentEnum.VALUE);
			secondErrorLabel.addNoError();
			secondBaseComboBox.changeComponentLayout(ComponentEnum.VALUE);
			return true;
		}
	}

	@SuppressWarnings("deprecation")
	public static boolean checkPasswordSimilarity(
			BasePasswordField passwordField,
			BasePasswordField confirmPasswordField,
			BaseLabel passwordLabelError, BaseLabel confirmPasswordLabelError) {
		if (!passwordField.getText().equals(confirmPasswordField.getText())) {
			confirmPasswordLabelError.addError("Passwords don't match.");
			passwordLabelError.addError("Passwords don't match.");
			passwordField.changeComponentLayout(ComponentEnum.ERROR);
			confirmPasswordField.changeComponentLayout(ComponentEnum.ERROR);
			return false;
		} else {
			confirmPasswordLabelError.addNoError();
			passwordLabelError.addNoError();
			passwordField.changeComponentLayout(ComponentEnum.VALUE);
			confirmPasswordField.changeComponentLayout(ComponentEnum.VALUE);
			return true;
		}
	}

	public static boolean compareStartAndEndDate(BaseTextField startDateField,
			BaseLabel startDateErrorLabel, String startDateLabel,
			BaseTextField endDateField, BaseLabel endDateErrorLabel,
			String endDateLabel) {
		Date startDate = DateUtilities.parseStringToDate(
				startDateField.getText(), FDMConstant.DATE_FORMAT);
		Date endDate = DateUtilities.parseStringToDate(endDateField.getText(),
				FDMConstant.DATE_FORMAT);
		int result = startDate.compareTo(endDate);
		if (result == 0) {
			startDateErrorLabel.addError(startDateLabel + " and "
					+ endDateLabel + " cannot be same.");
			startDateField.changeComponentLayout(ComponentEnum.ERROR);
			endDateErrorLabel.addError(startDateLabel + " and " + endDateLabel
					+ " cannot be same.");
			endDateField.changeComponentLayout(ComponentEnum.ERROR);
			return false;
		} else if (result > 0) {
			startDateErrorLabel.addError(startDateLabel + " is greater than "
					+ endDateLabel + ".");
			startDateField.changeComponentLayout(ComponentEnum.ERROR);
			endDateErrorLabel.addError(startDateLabel + " is greater than "
					+ endDateLabel + ".");
			endDateField.changeComponentLayout(ComponentEnum.ERROR);
			return false;
		} else if (result < 0) {
			startDateErrorLabel.addNoError();
			startDateField.changeComponentLayout(ComponentEnum.VALUE);
			endDateErrorLabel.addNoError();
			endDateField.changeComponentLayout(ComponentEnum.VALUE);
			return true;
		}
		return true;
	}
}
