package com.taokoo.util;

import java.text.DateFormat;
import java.util.Date;

/**
 * Token工具类
 * @author Taokoo
 *
 */
public class TokenUtil {
	/**
	 * 缓存默认保存7200s
	 */
	private static int EXPIRE_TIME = 60 * 60 * 24;

	public static String getxToken(int userid, String mobile) {
		DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return EncryptionHelp.MD5(userid + mobile + format1.format(new Date()));
	}

	public static String getxToken(String userid, String mobile) {
		DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return EncryptionHelp.MD5(userid + mobile + format1.format(new Date()));
	}

	public static int getEXPIRE_TIME() {
		return EXPIRE_TIME;
	}
}
