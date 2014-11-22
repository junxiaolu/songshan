/*
 * Copyright 2005-2015 Laoyasoft. All Rights Reserved.
 */
package com.laoyasoft.eas.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * @author ganmingzhu
 *
 */
public class SpringBeanFactoryUtil implements BeanFactoryAware {

	private static BeanFactory beanFactory;
	
	/**
	 * {@inheritDoc}.
	 */
	@SuppressWarnings("static-access")
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
	
	public static Object getBean(String beanName) {
		return beanFactory.getBean(beanName);
	}
}
