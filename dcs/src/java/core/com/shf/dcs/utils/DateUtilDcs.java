package com.shf.dcs.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.poi.ss.usermodel.Cell;

public class DateUtilDcs {
	public static final DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(
			"dd/MM/yyyy");
	public static final DateFormat DEFAULT_DATE_FORMAT_FULL = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:sss");
	public static final DateFormat DEFAULT_DATE_FORMAT_FULL_VI = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm");
	public static final DateFormat DEFAULT_DATE_FORMAT_ESB_FULL_VI = new SimpleDateFormat(
			"dd-MMM-yyyy");
	public static final long MILISEC_PERDAY = 86400000;
	// FIXME: Service check Tet holidays?
	public static Date[] HOLIDAYS = { new Date(0, 1, 1), new Date(0, 9, 2),
			new Date(0, 5, 1) };

	public static Date convertStringToDate(String startDateString) {
		Date date = null;
		try {
			date = DEFAULT_DATE_FORMAT.parse(startDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date getDateCellValue(Cell cell) {
		Date date = null;
		try {
			date = cell.getDateCellValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String convertDateToString(Date date) {
		String dateString = "";
		if (date == null) {
			return "";
		}
		dateString = DEFAULT_DATE_FORMAT.format(date);
		return dateString;
	}
	
	public static String convertDateEsbToString(Date date) {
		String dateString = "";
		if (date == null) {
			return "";
		}
		dateString = DEFAULT_DATE_FORMAT_ESB_FULL_VI.format(date);
		return dateString;
	}

	public static Date getDateInWorking(Date startDate, int numOfDays) {
		if (startDate != null) {
			Calendar cal = new GregorianCalendar();
			cal.setTime(startDate);
			for (int i = 0; i < numOfDays; i++)
				do {
					cal.add(Calendar.DAY_OF_MONTH, 1);
				} while (!isWorkingDay(cal));
			return cal.getTime();
		} else {
			return new Date();
		}
	}

	private static boolean isWorkingDay(Calendar cal) {
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY)
			return false;
		return true;
	}

	public static boolean isAfter(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return false;
		return date1.after(date2);
	}

	public static Date convertStringToDateFull(String dateString) {
		Date date = null;
		try {
			date = DEFAULT_DATE_FORMAT_FULL.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

//	public static void main(String[] args) {
//		Date convertedDate = convertStringToDate("12/11/2015");
//		System.out.println(" " + convertedDate.toGMTString());
//	}

	public static String convertDateToStringFull(Date date) {
		String dateString = "";
		if (date == null) {
			return "";
		}
		dateString = DEFAULT_DATE_FORMAT_FULL_VI.format(date);
		return dateString;
	}
}
