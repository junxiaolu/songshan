/*
 * Copyright 2005-2015 Laoyasoft. All Rights Reserved.
 */
package com.laoyasoft.eas.log;

import org.apache.commons.logging.Log;

/**
 * EAS日志工厂.<br>
 * 
 * @author gmz <br>
 * @version 1.0.0 May 2, 2008<br>
 * @see
 * @since JDK 5.0
 */
public final class LogFactory {

	/**
	 * .
	 */
	private LogFactory() {

	}

	/**
	 * .
	 * 
	 * @param c
	 *            类Class实例
	 * @return Log
	 */
	public static Log getLog(final Class< ? extends Object> c) {
		return new com.laoyasoft.eas.log.Log(org.apache.commons.logging.LogFactory.getLog(c));
	}
}
