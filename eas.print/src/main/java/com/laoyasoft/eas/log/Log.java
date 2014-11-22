/*
 * Copyright 2005-2015 Laoyasoft. All Rights Reserved.
 */
package com.laoyasoft.eas.log;

/**
 * LOG.<br>
 * 
 * @author gmz <br>
 * @version 1.0.0 May 2, 2008<br>
 * @see
 * @since JDK 5.0
 */
class Log implements org.apache.commons.logging.Log {

	/**
	 * .
	 */
	private org.apache.commons.logging.Log log;

	/**
	 * .
	 * 
	 * @param newLog
	 *            Log
	 */
	Log(final org.apache.commons.logging.Log newLog) {
		this.log = newLog;
	}

	/**
	 * {@inheritDoc}
	 */
	public void debug(final Object o) {
		if (isDebugEnabled()) {
			log.debug(o);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void debug(final Object o, final Throwable t) {
		if (isDebugEnabled()) {
			log.debug(o, t);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void error(final Object o) {
		if (isErrorEnabled()) {
			log.error(o);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void error(final Object o, final Throwable t) {
		if (isErrorEnabled()) {
			log.error(o, t);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void fatal(final Object o) {
		if (isFatalEnabled()) {
			log.fatal(o);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void fatal(final Object o, final Throwable t) {
		if (isFatalEnabled()) {
			log.fatal(o, t);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void info(final Object o) {
		if (isInfoEnabled()) {
			log.info(o);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void info(final Object o, final Throwable t) {
		if (isInfoEnabled()) {
			log.info(o, t);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isDebugEnabled() {
		return log.isDebugEnabled();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isErrorEnabled() {
		return log.isErrorEnabled();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isFatalEnabled() {
		return log.isFatalEnabled();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isInfoEnabled() {
		return log.isInfoEnabled();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isTraceEnabled() {
		return log.isTraceEnabled();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isWarnEnabled() {
		return log.isWarnEnabled();
	}

	/**
	 * {@inheritDoc}
	 */
	public void trace(final Object o) {
		if (isTraceEnabled()) {
			log.trace(o);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void trace(final Object o, final Throwable t) {
		if (isTraceEnabled()) {
			log.trace(o, t);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void warn(final Object o) {
		if (isWarnEnabled()) {
			log.warn(o);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void warn(final Object o, final Throwable t) {
		if (isWarnEnabled()) {
			log.warn(o, t);
		}
	}

	public void saveLog(final boolean isSave, final String logFilePath,
			final String saveStr) {
		
	}
}
