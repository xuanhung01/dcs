package com.shf.dcs.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import vn.co.dssfw.utils.DateUtil;

public class Utils {

	public static Double getDoubleisNull(Double number) {
		if (number == null)
			return new Double("0.0");
		else
			return number;
	}
	
	public static Double getDoubleisZero(Double number) {
		if (number == null || number == 0)
			return new Double("1.0");
		else
			return number;
	}
	
	public static Long getLongisNull(Long number) {
		if (number == null)
			return new Long("0");
		else
			return number;
	}
	
	public static Date getEndDayOfMonth (int releative) {
		Calendar cal = new GregorianCalendar(DateUtil.getYear(new Date()),DateUtil.getMonth(new Date()) + 1,0);
		Date date = cal.getTime();
		Date dateEndOfMonth = DateUtil.getRelativeDay(date, releative);
		return dateEndOfMonth;
	}
}
