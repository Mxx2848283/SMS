package com.sms.utils;

public class StringUtils {

	/**
	 * ÅÐ¶Ï×Ö·û´®ÊÇ·ñÎª¿Õ
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		if(str == null) return true;
		return str.trim().equals("");
	}
	
}
