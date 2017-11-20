package com.slickcode.fdms.client.fd.listner;

import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.baseframework.table.PaginatedTablePanel;
import com.slickcode.fdms.client.fd.page.FdMainPage;
import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.client.utils.ScreenMode;
import com.slickcode.fdms.common.bean.FdPanelBean;
import com.slickcode.fdms.common.vo.FdVO;
import com.slickcode.fdms.service.FdServiceImpl;
import com.slickcode.fdms.service.IFdService;
import com.slickcode.fdms.service.serviceobject.FdResult;

public class EditFdActionListner extends FdmsActionListner {

	private PaginatedTablePanel paginatedTablePanel;
	private BasePanel basePanel;
	private FdVO fdVO;
	private IFdService fdService;
	
	public EditFdActionListner(PaginatedTablePanel paginatedTablePanel) {
		this.paginatedTablePanel = paginatedTablePanel;
		this.fdService = (FdServiceImpl)applicationContext.getBean("fdServiceImpl");
	}

	public EditFdActionListner(BasePanel basePanel) {
		this.basePanel = basePanel;
		this.fdService = (FdServiceImpl)applicationContext.getBean("fdServiceImpl");
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance().showPanel(new FdMainPage(fdVO, ScreenMode.EDIT));
	}

	@Override
	public boolean performAction(ActionEvent e) {
		if (populateFdVO()) {
			FdResult result = fdService.fetchById(fdVO);
			if (result.isSuccess()) {
				fdVO = result.getFdVO();
				return true;
			} else {
				setErrorList(result.getErrorList());
				return false;
			}
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	private boolean populateFdVO() {
		if (null != paginatedTablePanel) {
			JTable table = paginatedTablePanel.getTable();
			int itemsPerPage = paginatedTablePanel.getItemsPerPage();
			int selectedIndexOfPage = paginatedTablePanel
					.getSelectedIndexOfPage();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			Vector<Object> rowDataVector = (Vector<Object>) model
					.getDataVector().elementAt(
							((selectedIndexOfPage - 1) * itemsPerPage)
									+ table.getSelectedRow());

			Integer fdId = (Integer) rowDataVector.get(0);

			fdVO = new FdVO();
			fdVO.setFdId(fdId);
			return true;
		} else {
			if (basePanel.validatePanelData()) {
				FdPanelBean fdPanelBean = (FdPanelBean) basePanel
						.getPanelDataOnSubmit();
				fdVO = fdPanelBean.getFdVO();
				return true;
			}
		}
		return false;
	}
}
