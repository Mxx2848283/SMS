package com.sms.utils;

public class StringUtils {

	/**
	 * �ж��ַ����Ƿ�Ϊ��
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		if(str == null) return true;
		return str.trim().equals("");
	}
	
}
