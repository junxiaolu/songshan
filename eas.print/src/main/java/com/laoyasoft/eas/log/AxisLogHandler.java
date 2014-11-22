/*
 * Copyright 2005-2015 Laoyasoft. All Rights Reserved.
 */
package com.laoyasoft.eas.log;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.SOAPPart;
import org.apache.axis.handlers.BasicHandler;
import org.apache.commons.logging.Log;

/**
 * @author gmz
 *
 */
public class AxisLogHandler extends BasicHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3850016004910860295L;
	/**
	 * 日志.
	 */
	private static final Log LOG = LogFactory.getLog(AxisLogHandler.class);

	/**
	 * {@inheritDoc}
	 */
	public void invoke(final MessageContext msgContext) throws AxisFault {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onFault(final MessageContext msgContext) {
		final Object o = ((SOAPPart) (msgContext.getResponseMessage().getSOAPPart())).getCurrentMessage();
		if (o instanceof Exception) {
			final Exception e = (Exception) o;
			LOG.error("error", e.getCause());
		}
	}
}

