package com.slickcode.fdms.client.fd.listner;

import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.slickcode.baseframework.table.PaginatedTablePanel;
import com.slickcode.fdms.client.fd.page.FdMainPage;
import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.client.utils.ScreenMode;
import com.slickcode.fdms.common.vo.FdVO;
import com.slickcode.fdms.service.FdServiceImpl;
import com.slickcode.fdms.service.IFdService;
import com.slickcode.fdms.service.serviceobject.FdResult;

public class ShowShowAllFdActionListner extends FdmsActionListner {

	private PaginatedTablePanel panel;
	private FdVO fdVO;
	private IFdService fdService;
	
	public ShowShowAllFdActionListner(PaginatedTablePanel panel) {
		this.panel = panel;
		this.fdService = (FdServiceImpl)applicationContext.getBean("fdServiceImpl");
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(new FdMainPage(fdVO, ScreenMode.VIEW));
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean performAction(ActionEvent e) {
		JTable table = panel.getTable();
		int itemsPerPage = panel.getItemsPerPage();
		int selectedIndexOfPage = panel.getSelectedIndexOfPage();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Vector<Object> rowDataVector = (Vector<Object>) model.getDataVector()
				.elementAt(
						((selectedIndexOfPage - 1) * itemsPerPage)
								+ table.getSelectedRow());

		Integer fdId = (Integer) rowDataVector.get(0);

		fdVO = new FdVO();
		fdVO.setFdId(fdId);
		FdResult result = fdService.fetchById(fdVO);

		if (result.isSuccess()) {
			fdVO = result.getFdVO();
			return true;
		} else {
			setErrorList(result.getErrorList());
			return false;
		}
	}

}
