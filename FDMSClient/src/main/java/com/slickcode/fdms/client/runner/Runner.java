package com.slickcode.fdms.client.runner;

import com.slickcode.fdms.client.page.MainPage;

public class Runner {

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				MainPage.getInstance();
			}
		});
	}
}
