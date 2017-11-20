package com.slickcode.fdms.client.login.listner;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.fdms.client.cache.FDMSCache;
import com.slickcode.fdms.client.constants.CommonConstants;
import com.slickcode.fdms.client.home.page.HomePage;
import com.slickcode.fdms.client.listner.FdmsActionListner;
import com.slickcode.fdms.client.page.MainPage;
import com.slickcode.fdms.common.bean.LoginPanelBean;
import com.slickcode.fdms.common.constant.FdStatusEnum;
import com.slickcode.fdms.common.vo.FdVO;
import com.slickcode.fdms.common.vo.PersonVO;
import com.slickcode.fdms.service.FdServiceImpl;
import com.slickcode.fdms.service.IFdService;
import com.slickcode.fdms.service.ILoginService;
import com.slickcode.fdms.service.LoginServiceImpl;
import com.slickcode.fdms.service.serviceobject.FdListResult;
import com.slickcode.fdms.service.serviceobject.LoginResult;

public class LoginButtonActionListner extends FdmsActionListner {
	private PersonVO personVO;
	private BasePanel basePanel;
	protected List<FdVO> currentFdVOList;
	protected List<FdVO> maturedFdVOList;
	protected List<FdVO> nextWeekFdVOList;

	private ILoginService loginService;
	private IFdService fdService;

	public LoginButtonActionListner(BasePanel basePanel) {
		this.basePanel = basePanel;
		this.loginService = (LoginServiceImpl) applicationContext
				.getBean("loginServiceImp");
		this.fdService = (FdServiceImpl) applicationContext
				.getBean("fdServiceImpl");
	}

	@Override
	public void onSuccess() {
		MainPage.getInstance().changeMenuBar(true, personVO);
		MainPage.getInstance().showPanel(
				new HomePage(currentFdVOList, maturedFdVOList,
						nextWeekFdVOList, CommonConstants.HEADER_ACTIVE_FDS,
						CommonConstants.HEADER_MATURED_FD,
						CommonConstants.HEADER_MATURED_IN_NEXT_WEEK_FD));
	}

	@Override
	public boolean performAction(ActionEvent e) {
		if (basePanel.validatePanelData()) {
			LoginPanelBean loginPanelBean = (LoginPanelBean) basePanel
					.getPanelDataOnSubmit();
			LoginResult result = loginService.login(loginPanelBean.getLoginVO()
					.getUserName(), loginPanelBean.getLoginVO().getPassword());

			if (result.isSuccess()) {
				personVO = result.getLoginVO().getPersonVO();
				fetchCurrentFdList();
				fetchMaturedFdList();
				fetchNextWeekFdList();
				return true;
			} else {
				setErrorList(result.getErrorList());
				return false;
			}
		} else {
			return false;
		}
	}

	protected void fetchCurrentFdList() {
		FdVO fdVO = new FdVO();
		fdVO.setStatusVO(FDMSCache.getInstance().getStatusVOByCode(
				FdStatusEnum.CURRENT.getCode()));
		FdListResult result = fdService.fetchByCriteria(fdVO);
		currentFdVOList = result.getFdVOList();
		if (null == currentFdVOList) {
			currentFdVOList = new ArrayList<FdVO>();
		}
	}

	protected void fetchMaturedFdList() {
		FdListResult result = fdService.fetchMaturedFds();
		maturedFdVOList = result.getFdVOList();
		if (null == maturedFdVOList) {
			maturedFdVOList = new ArrayList<FdVO>();
		}
	}

	protected void fetchNextWeekFdList() {
		FdListResult result = fdService.fetchFdsMaturingInNextWeek();
		nextWeekFdVOList = result.getFdVOList();
		if (null == nextWeekFdVOList) {
			nextWeekFdVOList = new ArrayList<FdVO>();
		}
	}
}