package com.cnnic.whois.util.validate;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cnnic.whois.util.IpUtil;

/***
 * Utils for domain and IP validation 
 *
 */
public class ValidateUtils {

	/**
	 * Is QueryString a punycode string
	 * @param String q : query string
	 * @return boolean
	 */
	public static boolean isPunyPartSearch(String q){
		if (StringUtils.isBlank(q)){
			return false;
		}
		return q.contains("xn--*");
	}
	/**
	 * Is a validated domain name
	 * @param domainName 
	 * @return boolean
	 */
	public static boolean validateDomainName(String domainName){
		if (!isCommonInvalidStr(domainName)){
			return false;
		}
		if(domainName.length() > 255){
			return false;
		}
		if(!domainName.startsWith("xn--") && !verifyNameServer(domainName)){
			return false;
		}
		return true;
	}
	
	/**
	 * Verifying the String parameters
	 * 
	 * @param String parm
	 * @return The correct param returns true, failure to return false
	 */
	public static boolean isCommonInvalidStr(String parm) {
		String strReg = "^[\u0391-\uFFE5a-zA-Z\\d\\*]{1}([\u0391-\uFFE5\\w\\-\\.\\_\\*]*)$";
		if (StringUtils.isBlank(parm)){
			return false;
		}
		return parm.matches(strReg);
	}
	
	/**
	 * Contain Punctuation in a str
	 * @param String str
	 * @return boolean
	 */
	public static boolean containPunctuation(String str) {
		String strReg = "[`~!@#%^&\\*()+=|\\\\;',.\\/?]";
		if (StringUtils.isBlank(strReg)){
			return false;
		}
		return str.matches(strReg);
	}
	
	/**
	 * Verifying the NameServer parameters
	 * 
	 * @param String queryPara
	 * @return The correct queryPara returns true, failure to return false
	 */
	public static boolean verifyNameServer(String queryPara) {
		if(StringUtils.isBlank(queryPara)){
			return false;
		}
//		if (!isCommonInvalidStr(queryPara)){
//			return false;
//		}
		if(queryPara.length() > 255){
			return false;
		}
		return true;
//		String fuzzyReg = "^(?!-.)(?!.*?-$)((\\*)?[\u0391-\uFFE50-9a-zA-Z-]{0,62}(\\*)?)+(\\.[\u0391-\uFFE50-9a-zA-Z-]{0,62}\\*?)*$";
//		if (queryPara.matches(fuzzyReg))
//			return true;
//		return false;
	}
	
	/**
	 * verify fuzzy domain
	 * @param String queryPara
	 * @return boolean
	 */
	public static boolean verifyFuzzyDomain(String queryPara) {
		if(StringUtils.isBlank(queryPara)){
			return false;
		}
//		if (!isCommonInvalidStr(queryPara)){
//			return false;
//		}
		if(queryPara.length() > 255){
			return false;
		}
		String[] labels = queryPara.split(".");
		for(String label:labels){
			if(label.length()>62){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Verifying the IP parameters
	 * 
	 * @param String ipStr
	 * @param String ipLengthStr
	 * @return The correct ipstr returns true, failure to return false
	 */
	public static boolean verifyIP(String ipStr, String ipLengthStr) {
		boolean isIpV4 = isIpv4(ipStr);
		boolean isIpV6 = isIPv6(ipStr);
		if(!isIpV4 && ! isIpV6){
			return false;
		}
		if (!ipLengthStr.matches("^[0-9]*$")){
			return false;
		}
		if(Integer.parseInt(ipLengthStr) < 0){
			return false;
		}
		if(isIpV4){
			return Integer.parseInt(ipLengthStr) <= 32;
		}else{
			return Integer.parseInt(ipLengthStr) <= 128;
		}
	}
	
	/**
	 * Verifying the IPv4 parameters
	 * @param String address
	 * @return The correct ipv4 returns true, failure to return false
	 */
	public static boolean isIpv4(String address) {
		String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\." 
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." 
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." 
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$"; 
		if (address.matches(regex))
			return true;
		return false;
	}
	
	/**
	 * Verifying the IPv6 parameters
	 * 
	 * @param String address
	 * @return The correct ipv6 returns true, failure to return false
	 */
	public static boolean isIPv6(String address) {
		boolean result = false;
		String regHex = "(\\p{XDigit}{1,4})";

		String regIPv6Full = "^(" + regHex + ":){7}" + regHex + "$";

		String regIPv6AbWithColon = "^(" + regHex + "(:|::)){0,6}" + regHex
				+ "$";
		String regIPv6AbStartWithDoubleColon = "^(" + "::(" + regHex
				+ ":){0,5}" + regHex + ")$";
		String regIPv6 = "^(" + regIPv6Full + ")|("
				+ regIPv6AbStartWithDoubleColon + ")|(" + regIPv6AbWithColon
				+ ")$";

		if (address.indexOf(":") != -1) {
			if (address.length() <= 39) {
				String addressTemp = address;
				int doubleColon = 0;
				if (address.equals("::"))
					return true;
				while (addressTemp.indexOf("::") != -1) {
					addressTemp = addressTemp
							.substring(addressTemp.indexOf("::") + 2,
									addressTemp.length());
					doubleColon++;
				}
				if (doubleColon <= 1) {
					result = address.matches(regIPv6);
				}
			}
		}
		return result;
	}
	
	/**
	 * transform map of Long to IP address
	 * @param map
	 * @return map of IP range
	 */
	public static Map<String, Object> longToIP(Map<String, Object> map) {
		Object ipversion = map.get("IP Version");

		String startHightAddress = map.get("StartHighAddress").toString();
		String startLowAddress = map.get("StartLowAddress").toString();
		String endHighAddress = map.get("EndHighAddress").toString();
		String endLowAddress = map.get("EndLowAddress").toString();

		map.remove("StartHighAddress");
		map.remove("StartLowAddress");
		map.remove("EndHighAddress");
		map.remove("EndLowAddress");
		String startAddress = "";
		String endAddress = "";
		if (ipversion != null) {
			if (ipversion.toString().indexOf("v6") != -1) {
				startAddress = IpUtil.ipV6ToString(
						Long.parseLong(startHightAddress),
						Long.parseLong(startLowAddress));
				endAddress = IpUtil.ipV6ToString(
						Long.parseLong(endHighAddress),
						Long.parseLong(endLowAddress));
			} else {
				startAddress = IpUtil.longtoipV4(Long
						.parseLong(startLowAddress));
				endAddress = IpUtil
						.longtoipV4(Long.parseLong(endLowAddress));
			}
			map.put("Start Address", startAddress);
			map.put("End Address", endAddress);
		}
		return map;
	}
	
	/**
	 * remove the last . in paraStr
	 * @param paramStr
	 * @return 
	 */
	public static String deleteLastPoint(String paramStr) {
		if(StringUtils.isBlank(paramStr)){
			return paramStr;
		}
		if(paramStr.length()<=1 || ! paramStr.endsWith(".")){
			return paramStr;
		}
		return paramStr.substring(0, paramStr.length() - 1);
	}
}