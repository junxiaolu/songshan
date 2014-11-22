/*
 * Copyright 2005-2015 Laoyasoft. All Rights Reserved.
 */
package com.laoyasoft.eas.exception;

/**
 * EAS应用异常基类.<br>
 * 
 * @author gmz <br>
 * @version 1.0.0 May 19, 2008<br>
 * @see
 * @since JDK 5.0
 */
@SuppressWarnings("serial")
public class EasException extends RuntimeException {

	/**
	 * 
	 */
	public EasException() {
	}

	/**
	 * @param message
	 *            异常信息
	 */
	public EasException(final String message) {
		super(message);
	}

	/**
	 * @param cause
	 *            异常
	 */
	public EasException(final Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 *            异常信息
	 * @param cause
	 *            异常
	 */
	public EasException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
