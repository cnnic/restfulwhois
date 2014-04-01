package com.cnnic.whois.util;
/**
 * 
 * Transform Utils
 *
 */
public class TransformUtils {

	/**
	 * is Network mask
	 * @param ipInfo
	 * @return String[]
	 */
	public static String[] isIPMaskNetWork(String ipInfo) {
		String[] infoArray = new String[2];
		if (ipInfo.indexOf(WhoisUtil.PRX) >= 0) {
			infoArray = ipInfo.split(WhoisUtil.PRX);
		} else {
			infoArray[0] = ipInfo;
			infoArray[1] = "0";
		}
		return infoArray;
	}
	
}
