/*
 * Copyright 2005-2015 Laoyasoft. All Rights Reserved.
 */
package com.laoyasoft.eas.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Node;

import com.laoyasoft.eas.exception.EasException;

/**
 * 通用方法类<br>
 * 
 * @author gmz <br>
 * @version 2012-12-16 <br>
 */
public class CommonUtils {
	
	/**
	 * 获取应用程序根目录物理路径.
	 */
	public static String getAppRootDir() {
		String sRet = null;
		sRet = System.getProperty("user.dir");		
		return sRet;
	}
	
	/**
	 * 截取指定长度字符串
	 * @param str
	 * @param len
	 * @return
	 */
	public static String subString(final String str, final int len) {
		String sRet = str;
		if (null != str && str.length() > len) {
			sRet = sRet.substring(0, len - 1);
		}
		return sRet;
	}

	/**
	 * 获取指定时间的间隔日期.
	 * 
	 * @return yesterDay
	 */
	public static Date getTarGetRQ(final Date sdate, final int diff) {
		final Calendar date = new GregorianCalendar();
		date.setTime(sdate);
		date.add(Calendar.DAY_OF_YEAR, diff);
		final Date targetDay = date.getTime();
		return targetDay;
	}

	public static Date addDayOfMonth(final Date date, final int addDay) {
		final Calendar thedate = Calendar.getInstance();
		thedate.setTime(date);
		thedate.add(Calendar.DATE, addDay);
		return thedate.getTime();
	}

	/**
	 * 获取日期间隔.
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @return int abs(sDate - eDate)
	 */
	public static int getDiffDays(final int sDate, final int eDate) {
		int diffDays = 0;
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			final Date startDate = sdf.parse(String.valueOf(sDate));
			final Date endDate = sdf.parse(String.valueOf(eDate));
			diffDays = (int) ((startDate.getTime() - endDate.getTime()) / (3600 * 24 * 1000));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Math.abs(diffDays);
	}

	/**
	 * 获取当前日期是星期几
	 */

	public static int getDateOfWeek(Date dt) {
		try {
			int[] weekDays = { 7, 1, 2, 3, 4, 5, 6 };
			Calendar cal = Calendar.getInstance();
			cal.setTime(dt);
			int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if (w < 0)
				w = 0;
			return weekDays[w];
		} catch (Exception e) {

			return 7;
		}
	}

	/**
	 * 获取时间差
	 */

	public static int getshiftTime(final Date date1, final Date date2) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			// 获取时间的小时和分钟
			String sDate1 = sdf.format(date1);
			String sDate2 = sdf.format(date2);
			// 把时间转换成 小时和分钟的时间格式
			Date tDate1 = sdf.parse(sDate1);
			Date tDate2 = sdf.parse(sDate2);

			int shiftTime = (int) ((tDate1.getTime() - tDate2.getTime()) / (60 * 1000));
			return shiftTime;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 获取节点方法.
	 * 
	 * @param node
	 *            节点
	 * @param xpath
	 *            路径
	 * @return 值
	 */
	public static String getSingleNodeValue(final Node node, final String xpath) {
		final Node n = node.selectSingleNode(xpath);
		if (null != n) {
			return n.getText();
		}
		return "";
	}

	/**
	 * 设置节点方法.
	 * 
	 * @param node
	 *            节点
	 * @param xpath
	 *            路径
	 * @param value
	 *            值
	 */
	public static void setSingleNodeValue(final Node node, final String xpath,
			final String value) {
		node.selectSingleNode(xpath).setText(value);
	}

	/**
	 * 转换异常堆栈信息为字符串
	 */
	public static String getExceptionText(Exception e) {
		List<StackTraceElement> trace = Arrays.asList(e.getStackTrace());
		final StringBuffer traceText = new StringBuffer(200);
		traceText.append("\r\n异常日志: \r\n");
		traceText.append(e.getClass().getName() + ": " + e.getMessage());
		for (StackTraceElement elem : trace) {
			traceText.append("\r\n\t");
			traceText.append("at " + elem);
		}
		if (e.getCause() != null) {
			traceText.append("\r\nCaused by : " + e.getCause().getMessage());
			if (e.getCause().getStackTrace() != null) {
				trace = Arrays.asList(e.getCause().getStackTrace());
				for (StackTraceElement elem : trace) {
					traceText.append("\r\n\t");
					traceText.append("at " + elem);
				}
			}
		}
		return traceText.toString();
	}

	/**
	 * 转换updateTime/更新时间格式为Date.
	 * 
	 * @param strDate
	 *            更新时间
	 * @param formatStr
	 *            格式
	 * @return Date
	 */
	public static Date formatDateFormatDay(final String strDate,
			final String formatStr) {
		Date date = null;
		try {
			date = new SimpleDateFormat(formatStr).parse(strDate);

		} catch (ParseException e) {
			throw new EasException(e);
		}
		return date;
	}

	/**
	 * 获取WebRoot路径.
	 * 
	 * @return WebRoot路径
	 */
	public static String getWebRoot() {
		final String def = CommonUtils.class.getProtectionDomain()
				.getCodeSource().getLocation().getPath();
		final int pos = def.indexOf("WEB-INF");
		String sRet = "";
		if (def.length() > 0 && pos > -1) {
			sRet = def.substring(1, pos);
		} else if (def.length() > 0) {
			sRet = def.substring(1, def.length());
		}
		return sRet;
	}

	/**
	 * 解析Double字符串.<br>
	 * null,空字符串和非数值字符串会返回null
	 * 
	 * @param str
	 *            可以解析为数值的String
	 * @return Double型
	 */
	public static Double parseDouble(final String str) {
		if (StringUtils.isNotEmpty(str)
				&& StringUtils.isNumeric(str.replace("-", "").replace(".", "")
						.trim())) {
			return Double.parseDouble(str.trim());
		}
		return null;
	}
}
