/*********************************************************************************************************
 * File Name    : SMSUtil.java
 * Description  : Common utility method
 * Author       : Dung.NTR
 * Date	        : 17/06/2013
 * Note         : 
 * 
 * Name            Date             Description
 * Dung.NTR        17/06/2013       First version
 *********************************************************************************************************/
package com.shf.dcs.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author User
 * 
 */
public class SMSUtil {

	private static final Logger logger = Logger.getLogger(SMSUtil.class);

	/**
	 * Convert object to string value.
	 * 
	 * @param obj
	 *            The object input
	 * @return
	 */
	public static String toStringValue(Object obj) {
		return obj == null ? "" : obj.toString();
	}

	/**
	 * Get total page of records.
	 */
	public static int getTotalPage(long totalRecord, int pageItem) {
		int totalPage = 1;
		while (totalPage * pageItem < totalRecord) {
			totalPage++;
		}
		return totalPage;
	}

	/**
	 * Check the string input is mobile no number or not.
	 * 
	 * @param mobileNo
	 *            The input string
	 * @return true if input string have mobile phone format
	 */
	public static boolean isMobilePhone(String mobileNo) {
		return StringUtils.isNotEmpty(mobileNo) && mobileNo.matches("([0-9]{10}|[0-9]{12})");
	}
	
	public static void main(String[] args) {
		String regex = "(^\\d{10}(?:[-\\s]\\d{3})?$)";
		String taxcode = "0123456789";
		System.out.println(taxcode.matches(regex));
	}
	
	public static boolean isMobilePhoneVN(String mobileNo) {
		String mobileExp = "^(0)((3([2-9]))|(5(2|6|8|9))|(7(0|6|7|8|9))|(8([0-9]))|(9([0-9])))\\d{7}";
		if (StringUtils.isEmpty(mobileNo) || !mobileNo.matches(mobileExp)) {
			return false;
		}
		return true;
	}

	/**
	 * Check the string input is mobile no number or not.
	 * 
	 * @param mobileNo
	 *            The input string
	 * @return true if input string have mobile phone format
	 */
	public static boolean isHomePhone(String homePhone) {
		return StringUtils.isNotEmpty(homePhone) && homePhone.matches("[0-9]{9,10}");
	}

	/**
	 * Check the string input is mobile no number or not.
	 * 
	 * @param mobileNo
	 *            The input string
	 * @return true if input string have mobile phone format
	 */
	public static boolean isNotMobilePhone(String mobileNo) {
		return !SMSUtil.isMobilePhone(mobileNo);
	}

	/**
	 * Get mobile phone format by country code.
	 * 
	 * @param countryCode
	 * @param mobileNo
	 * @return
	 */
	public static String getMobileFormat(String countryCode, String mobileNo) {
		// return countryCode + mobileNo.substring(countryCode.length());
		return countryCode + mobileNo;
	}

	/**
	 * Get full schema name
	 * 
	 * @param schemaName
	 * @return
	 */
	public static String getFullSchemaName(String schemaName) {
		if (!schemaName.endsWith(".")) {
			return schemaName + ".";
		}
		return schemaName;
	}

	/**
	 * Get full schema name
	 * 
	 * @param schemaName
	 * @return
	 */
	public static String getDBLinkName(String dbLinkName) {
		if (!dbLinkName.startsWith("@")) {
			return "@" + dbLinkName;
		}
		return dbLinkName;
	}

	/**
	 * Ham lay gio hien tai
	 * 
	 * @return
	 */
	public static int getCurrentHour() {
		Date date = new Date();
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * Ham lay ngay hien tai
	 * 
	 * @return
	 */
	public static int getCurrentDay() {
		Date date = new Date();
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 
	 * @param narrative
	 *            noi dung chuyen khoan quan mobile banking Ham nay tuong duong voi
	 *            procedure generate_content_mobile trong sms core
	 * @return noi dung da dc custom cho phu hop voi noi dung sms
	 */
	public static String getContentSMSFromMobileTransNarrative(String narrative) {
		if (StringUtils.isEmpty(narrative)) {
			return "";
		}

		String search = "ly do";
		int indexInit = narrative.indexOf(",");
		int indexMobile = narrative.indexOf("0");
		String mobile = narrative.substring(indexInit, indexMobile);
		String content1 = "GD duoc khoi tao tu mobile " + mobile + "r\n";

		int indexDesc = narrative.indexOf(search);
		indexDesc += search.length() + 1;
		String desc = narrative.substring(indexDesc);
		desc = StringUtils.replace(desc, "\r\n", " ");
		desc = StringUtils.replace(desc, "\r", " ");
		desc = StringUtils.replace(desc, "\n", " ");
		String content2 = "ND:" + desc;

		String content = content1 + content2;
		content = SMSUtil.ConvertVIToEN(content);

		// tam thoi chua viet do chua nam ro cau truc
		return content;
	}

	/**
	 * Ham convert tieng viet co dau sang ko dau
	 * 
	 * @param source
	 *            : tieng viet co dau
	 * @return tieng viet ko dau
	 */
	public static String ConvertVIToEN(String source) {
		// Declare list character in VN
		String[] ARR_COMPARE = { "à", "á", "ạ", "ả", "ã", "â", "ầ", "ấ", "ậ", "ẩ", "ẫ", "ă", "ằ", "ắ", "ặ", "ẳ", "ẵ",
				"è", "é", "ẹ", "ẻ", "ẽ", "ê", "ề", "ế", "ệ", "ể", "ễ", "ì", "í", "ị", "ỉ", "ĩ", "ò", "ó", "ọ", "ỏ", "õ",
				"ô", "ồ", "ố", "ộ", "ổ", "ỗ", "ơ", "ờ", "ớ", "ợ", "ở", "ỡ", "ù", "ú", "ụ", "ủ", "ũ", "ư", "ừ", "ứ", "ự",
				"ử", "ữ", "ỳ", "ý", "ỵ", "ỷ", "ỹ", "đ", "À", "Á", "Ạ", "Ả", "Ã", "Â", "Ầ", "Ấ", "Ậ", "Ẩ", "Ẫ", "Ă", "Ằ",
				"Ắ", "Ặ", "Ẳ", "Ẵ", "È", "É", "Ẹ", "Ẻ", "Ẽ", "Ê", "Ề", "Ế", "Ệ", "Ể", "Ễ", "Ì", "Í", "Ị", "Ỉ", "Ĩ", "Ò",
				"Ó", "Ọ", "Ỏ", "Õ", "Ô", "Ồ", "Ố", "Ộ", "Ổ", "Ỗ", "Ơ", "Ờ", "Ớ", "Ợ", "Ở", "Ỡ", "Ù", "Ú", "Ụ", "Ủ", "Ũ",
				"Ư", "Ừ", "Ứ", "Ự", "Ử", "Ữ", "Ỳ", "Ý", "Ỵ", "Ỷ", "Ỹ", "Đ", "ê", "ù", "à" };

		// Declare list character in EN
		String[] ARR_REPLACE = { "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a",
				"e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "i", "i", "i", "i", "i", "o", "o", "o", "o", "o",
				"o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "u", "u", "u", "u", "u", "u", "u", "u", "u",
				"u", "u", "y", "y", "y", "y", "y", "d", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A",
				"A", "A", "A", "A", "E", "E", "E", "E", "E", "E", "E", "E", "E", "E", "E", "I", "I", "I", "I", "I", "O",
				"O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "U", "U", "U", "U", "U",
				"U", "U", "U", "U", "U", "U", "Y", "Y", "Y", "Y", "Y", "D", "e", "u", "a" };

		for (int i = 0; i < ARR_COMPARE.length; i++) {
			source = source.replaceAll(ARR_COMPARE[i], ARR_REPLACE[i]);
		}
		return source;
	}

	/**
	 * 
	 * @param obj
	 *            object can convert
	 * @return
	 */
	public static boolean isNumberic(Object obj) {
		if (obj == null) {
			return false;
		}
		try {
			Integer.parseInt(obj.toString());
		} catch (NumberFormatException ex) {
			return false;
		}

		return true;
	}

	/**
	 * Convert income message to array of String command.
	 * 
	 * @param incomeMessage
	 *            : Income message ( DK OTP )
	 * @return the list of command DK,OTP
	 */
	public static String[] parseIncomeMessage(String incomeMessage) {
		if (StringUtils.isEmpty(incomeMessage)) {
			return null;
		}
		String resultMessage = StringUtils.replace(incomeMessage, "  ", " ");
		return resultMessage.split(" ");
	}

	/**
	 * Replace message content
	 * 
	 * @param messageContent
	 * @return
	 */
	public static String replaceMessageContent(String messageContent) {
		String message = messageContent;
		message = StringUtils.replace(message, "\\\\r\\\\n", "\\\r\\\n");
		message = StringUtils.replace(message, "\\\\r", "\\\r");
		message = StringUtils.replace(message, "\\\\n", "\\\n");
		return message;
	}

	public static String dumpObject(Object object) {
		Field[] fields = object.getClass().getDeclaredFields();
		StringBuilder sb = new StringBuilder();
		sb.append(object.getClass().getSimpleName()).append('{');

		boolean firstRound = true;

		for (Field field : fields) {
			if (!firstRound) {
				sb.append(", ");
			}
			firstRound = false;
			field.setAccessible(true);
			try {
				final Object fieldObj = field.get(object);
				final String value;
				if (null == fieldObj) {
					value = "null";
				} else {
					value = fieldObj.toString();
				}
				sb.append(field.getName()).append('=').append('\'').append(value).append('\'');
			} catch (IllegalAccessException ignore) {
				// this should never happen
			}

		}

		sb.append('}');
		return sb.toString();
	}

	public static boolean stringOnlyDigits(String input) {
		if (input == null)
			return false;
		String regex = "[0-9]+";
		return input.matches(regex);
	}

	public static int getDayFromStrDate(String date) {
		String strDay = date.split("/")[0];
		return Integer.parseInt(strDay);
	}

	public static int getMonthFromStrDate(String date) {
		String strMonth = date.split("/")[1];
		return Integer.parseInt(strMonth);
	}

	public static int ramdomJobId(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
    }
	
	public static int ramdomJobId(String range) {
		String[] arr = range.split("-");
		int min = Integer.parseInt(arr[0]);
		int max = Integer.parseInt(arr[1]);
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
    }
	
	public static String[] getArrayString(String input, String split) {
		String []arrString = null;
		if(StringUtils.isNotEmpty(input)) {
			arrString = input.split(split);
		}
		return arrString;
	}
}
