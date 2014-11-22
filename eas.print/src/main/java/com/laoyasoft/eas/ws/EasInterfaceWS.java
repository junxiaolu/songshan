/*
 * Copyright 2005-2015 Laoyasoft. All Rights Reserved.
 */
package com.laoyasoft.eas.ws;


import javax.xml.rpc.ServiceException;

import org.springframework.remoting.jaxrpc.ServletEndpointSupport;


/**
 * @author gmz
 *
 */
@SuppressWarnings("deprecation")
public class EasInterfaceWS extends ServletEndpointSupport {
	
	/**
	 * {@inheritDoc}
	 */
	protected void onInit() throws ServiceException {

	}

	/**
	 * @param beanName
	 *            beanName
	 * @return Spring bean
	 */
	private Object getBean(final String beanName) {
		return getApplicationContext().getBean(beanName);
	}
}
