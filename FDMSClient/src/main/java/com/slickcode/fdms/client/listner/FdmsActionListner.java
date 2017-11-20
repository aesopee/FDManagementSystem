package com.slickcode.fdms.client.listner;

import java.awt.Cursor;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.springframework.context.ApplicationContext;

import com.slickcode.baseframework.listner.BaseActionListner;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.client.utils.SpringContextLoader;

public abstract class FdmsActionListner extends BaseActionListner {

	private List<String> errorList;
	protected ApplicationContext applicationContext;
	
	public FdmsActionListner() {
		applicationContext = SpringContextLoader.getInstance().loadContext();
	}
	
	@Override
	public void mask() {
		MainPage.getInstance().setCursor(
				Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	}

	@Override
	public void unmask() {
		MainPage.getInstance().setCursor(Cursor.getDefaultCursor());
	}

	@Override
	public void onFailure() {
		String msg = null;
		if ((null == getErrorList()) || (null == getErrorList().get(0))) {
			msg = "Please resolve the errors on page";
		} else {
			msg = getErrorList().get(0);
		}

		showPopUp(msg, "Error Message");
	}

	/**
	 * @return the errorList
	 */
	public List<String> getErrorList() {
		return errorList;
	}

	/**
	 * @param errorList
	 *            the errorList to set
	 */
	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}

	protected void showPopUp(String msg, String title) {
		JOptionPane optionPane = new JOptionPane();
		optionPane.setMessage(msg);
		optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = optionPane.createDialog(MainPage.getInstance(), title);
		dialog.setVisible(true);

	}

	protected int showYesNoPopUp(String msg, String title) {
		int dialogButton = JOptionPane.YES_NO_OPTION;
		return JOptionPane.showConfirmDialog(MainPage.getInstance(), msg,
				title, dialogButton);
	}
}
