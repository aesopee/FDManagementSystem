package com.slickcode.fdms.client.page;

import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.slickcode.baseframework.utils.FontUtils;
import com.slickcode.baseframework.utils.MenuUtils;
import com.slickcode.fdms.client.bank.listner.InitiateAddBankActionListner;
import com.slickcode.fdms.client.bank.listner.InitiateSearchBankActionListner;
import com.slickcode.fdms.client.bank.listner.InitiateShowAllBankActionListner;
import com.slickcode.fdms.client.constants.CommonConstants;
import com.slickcode.fdms.client.fd.listner.InitiateAddFdActionListner;
import com.slickcode.fdms.client.fd.listner.InitiateMaturedFDsActionListner;
import com.slickcode.fdms.client.fd.listner.InitiateNextWeekMaturingFDsActionListner;
import com.slickcode.fdms.client.fd.listner.InitiateSearchFdActionListner;
import com.slickcode.fdms.client.fd.listner.InitiateShowAllFdActionListner;
import com.slickcode.fdms.client.login.listner.LogoutButtonHeaderMenuActionListner;
import com.slickcode.fdms.client.person.listner.InitiateAddPersonActionListner;
import com.slickcode.fdms.client.person.listner.InitiateSearchPersonActionListner;
import com.slickcode.fdms.client.person.listner.InitiateShowAllPersonActionListner;
import com.slickcode.fdms.common.vo.PersonVO;

public class FdmsMenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static FdmsMenuBar fdmsMenuBar;

	private FdmsMenuBar() {
		setFont(FontUtils.MENU_BAR_FONT);
	}

	public static final FdmsMenuBar getInstance() {
		if (null == fdmsMenuBar) {
			fdmsMenuBar = new FdmsMenuBar();
		}
		return fdmsMenuBar;
	}

	public void createMenu(boolean isLoggedIn, PersonVO personVO) {
		removeAll();
		populateFileMenu();
		if (isLoggedIn) {
			populateFDMenu();
			populateBankMenu();
			populatePersonMenu();
		}
		populateHelpMenu();

		if (isLoggedIn) {
			populateUserDetails(personVO);
		}
	}

	private void populateFileMenu() {
		JMenu fileMenu = MenuUtils.populateJMenu(CommonConstants.MENU_FILE,
				KeyEvent.VK_F);

		JMenuItem exit = MenuUtils.populateJMenuItem(CommonConstants.MENU_EXIT,
				null, KeyEvent.VK_E, KeyEvent.VK_E);

		exit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.exit(0);
			}
		});
		fileMenu.add(exit);

		add(fileMenu);
	}

	private void populateBankMenu() {
		JMenu menu = MenuUtils.populateJMenu(CommonConstants.MENU_BANK_HOME,
				KeyEvent.VK_B);

		JMenuItem add = MenuUtils.populateJMenuItem(CommonConstants.MENU_ADD,
				null, KeyEvent.VK_B, KeyEvent.VK_A);
		add.addActionListener(new InitiateAddBankActionListner());
		menu.add(add);

		JMenuItem search = MenuUtils.populateJMenuItem(
				CommonConstants.MENU_SEARCH, null, null, KeyEvent.VK_S);
		search.addActionListener(new InitiateSearchBankActionListner());
		menu.add(search);

		JMenuItem showAll = MenuUtils.populateJMenuItem(
				CommonConstants.MENU_SHOW_ALL, null, null, KeyEvent.VK_O);
		showAll.addActionListener(new InitiateShowAllBankActionListner());
		menu.add(showAll);

		add(menu);
	}

	private void populatePersonMenu() {
		JMenu menu = MenuUtils.populateJMenu(CommonConstants.MENU_PERSON_HOME,
				KeyEvent.VK_P);

		JMenuItem add = MenuUtils.populateJMenuItem(CommonConstants.MENU_ADD,
				null, KeyEvent.VK_P, KeyEvent.VK_A);
		add.addActionListener(new InitiateAddPersonActionListner());
		menu.add(add);

		JMenuItem search = MenuUtils.populateJMenuItem(
				CommonConstants.MENU_SEARCH, null, null, KeyEvent.VK_S);
		search.addActionListener(new InitiateSearchPersonActionListner());
		menu.add(search);

		JMenuItem showAll = MenuUtils.populateJMenuItem(
				CommonConstants.MENU_SHOW_ALL, null, null, KeyEvent.VK_O);
		showAll.addActionListener(new InitiateShowAllPersonActionListner());
		menu.add(showAll);

		add(menu);
	}

	private void populateFDMenu() {
		JMenu menu = MenuUtils.populateJMenu(CommonConstants.MENU_FD_HOME,
				KeyEvent.VK_D);

		JMenuItem add = MenuUtils.populateJMenuItem(CommonConstants.MENU_ADD,
				null, KeyEvent.VK_N, KeyEvent.VK_A);
		add.addActionListener(new InitiateAddFdActionListner());
		menu.add(add);

		JMenuItem search = MenuUtils
				.populateJMenuItem(CommonConstants.MENU_SEARCH, null,
						KeyEvent.VK_F, KeyEvent.VK_S);
		search.addActionListener(new InitiateSearchFdActionListner());
		menu.add(search);

		JMenuItem showAll = MenuUtils.populateJMenuItem(
				CommonConstants.MENU_SHOW_ALL, null, KeyEvent.VK_A,
				KeyEvent.VK_O);
		showAll.addActionListener(new InitiateShowAllFdActionListner());
		menu.add(showAll);

		JMenuItem maturedFds = MenuUtils.populateJMenuItem(
				CommonConstants.MENU_MATURED_FDS, null, KeyEvent.VK_M,
				KeyEvent.VK_M);
		maturedFds.addActionListener(new InitiateMaturedFDsActionListner());
		menu.add(maturedFds);

		JMenuItem FdsInNextWeek = MenuUtils.populateJMenuItem(
				CommonConstants.MENU_FDS_IN_NEXT_WEEK, null, KeyEvent.VK_W,
				KeyEvent.VK_W);
		FdsInNextWeek
				.addActionListener(new InitiateNextWeekMaturingFDsActionListner());
		menu.add(FdsInNextWeek);

		add(menu);
	}

	private void populateHelpMenu() {
		JMenu help = MenuUtils.populateJMenu(CommonConstants.MENU_HELP,
				KeyEvent.VK_H);

		JMenuItem about = MenuUtils.populateJMenuItem(
				CommonConstants.MENU_ABOUT, null, null, KeyEvent.VK_A);
		about.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

			}
		});
		help.add(about);

		add(help);
	}

	private void populateUserDetails(PersonVO personVO) {
		JMenu userDetails = MenuUtils.populateJMenu(
				"Welcome " + personVO.getFirstName() + " "
						+ personVO.getLastName(), KeyEvent.VK_W);

		JMenuItem logout = MenuUtils
				.populateJMenuItem(CommonConstants.MENU_LOGOUT, null,
						KeyEvent.VK_L, KeyEvent.VK_L);
		logout.addActionListener(new LogoutButtonHeaderMenuActionListner());
		userDetails.add(logout);

		add(Box.createHorizontalGlue());
		add(userDetails);
	}
}
