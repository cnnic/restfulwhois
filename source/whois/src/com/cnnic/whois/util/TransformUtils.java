package com.cnnic.whois.util;

public class TransformUtils {

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
