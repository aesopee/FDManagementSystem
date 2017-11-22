package com.slickcode.fdms.client.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextLoader {

	private static SpringContextLoader contextLoader;
	private ApplicationContext applicationContext;

	private SpringContextLoader() {
	}

	public static SpringContextLoader getInstance() {
		if (null == contextLoader) {
			contextLoader = new SpringContextLoader();
		}
		return contextLoader;
	}

	public final ApplicationContext loadContext() {
		if (null == applicationContext) {
			applicationContext = new ClassPathXmlApplicationContext("application-context-client.xml",
					"application-context-service.xml");
		}
		return applicationContext;
	}
}
