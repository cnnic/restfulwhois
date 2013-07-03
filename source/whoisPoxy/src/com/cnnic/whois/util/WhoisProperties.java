package com.cnnic.whois.util;

import java.util.Properties;
import java.io.FileInputStream;

public class WhoisProperties {
	
	private static Properties resource;
	
	private static final String REQUESURL = "requesUrl";
	private static final String PORT = "PORT";
	private static final String COREPOOLSIZE = "corePoolSize";
	private static final String MAXIMUMPOOLSIZE = "maximumPoolSize";
	private static final String KEEPALIVETIME = "keepAliveTime";
	private static final String MANAGEMENTPORT = "managementPort";
	private static final String PORXYIP = "whoisPoxy43.properties";
	private static final String SHUTDOWN = "shutDownCmd";
	private static final String EXPIRETIME = "minAccessInterval";
	private static final String MAXWAITTIME = "maxAccessInterval";
	//private static  String classUrl = "com/cnnic/whois/util/"+PORXYIP;
	  private static String classUrl;
//	/**
//	 * Load the resource file
//	 */
//    static{
//    	resource = new Properties();
//    	try{
//    		resource.load(WhoisProxyServer.class.getClassLoader().getResourceAsStream(classUrl));
//    	}catch(Exception e){
//    		e.printStackTrace();
//    	}
//    }
    /**
	 * Get RequesUrl
	 * 
	 * @return URLString
	 */
    public static String getRequesUrl(){
    	return resource.getProperty(REQUESURL);
    }
    /**
	 * Get Port
	 * 
	 * @return PortInt
	 */
    public static int getPort(){
    	return Integer.parseInt(resource.getProperty(PORT));
    }
    /**
	 * Get CorePoolSize
	 * 
	 * @return CorePoolSizeInt
	 */
    public static int getCorePoolSize(){
    	return  Integer.parseInt(resource.getProperty(COREPOOLSIZE));
    }
    /**
	 * Get MaximumPoolSize
	 * 
	 * @return MaximumPoolSizeInt
	 */
    public static int getMaximumPoolSize(){
    	return  Integer.parseInt(resource.getProperty(MAXIMUMPOOLSIZE));
    }
    /**
	 * Get KeepAliveTime
	 * 
	 * @return KeepAliveTimeLong
	 */
    public static long getKeepAliveTime(){
    	return Long.parseLong(resource.getProperty(KEEPALIVETIME));
    }
    /**
	 * Get ManagementPort
	 * 
	 * @return ManagementPortInt
	 */
	public static int getManagementPort() {
		return   Integer.parseInt(resource.getProperty(MANAGEMENTPORT));
	}
	/**
	 * Get PorxyIp
	 * 
	 * @return PorxyIpString
	 */
	public static String getPorxyIp() {
		return resource.getProperty(PORXYIP);
	}
	/**
	 * Get ShutDown
	 * 
	 * @return ShutDownString
	 */
	public static String getShutDown() {
		return resource.getProperty(SHUTDOWN);
	}
	/**
	 * Get ExpireTime
	 * 
	 * @return ExpireTimeLong
	 */
	public static long getExpireTime(){
    	return Long.parseLong(resource.getProperty(EXPIRETIME));
    }
	/**
	 * Get MaxWaitTime
	 * 
	 * @return MaxWaitTimeLong
	 */
	public static long getMaxWaitTime(){
    	return Long.parseLong(resource.getProperty(MAXWAITTIME));
    }
	public static String getClassesurl() {
		return classUrl;
	}
	
	public static void setClassesurl(String url) {
	    classUrl = url + "/whoisPoxy43.properties";
	    resource = new Properties();
	    try
	    {
	      resource.load(new FileInputStream(classUrl));
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
	
}
