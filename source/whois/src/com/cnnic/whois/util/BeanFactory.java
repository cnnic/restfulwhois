package com.cnnic.whois.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

/**
 * read beans-dao.properties
 *
 */
public class BeanFactory {
	
	private static final Map<String, Object> beans = new Hashtable<String, Object>();
	static {
		init("/beans-dao.properties");
	}

	static void init(String cfgFile) {
		// 加载配置文件 Properties props
		Properties props = loadProperties(cfgFile);

		// 循环props, 生成配置的bean的实例
		for (Object obj : props.keySet()) {
			String key = (String) obj;
			String className = props.getProperty(key);
			try {
				Object bean = Class.forName(className).newInstance();
				beans.put(key, bean);
			} catch (Exception e) {
				throw new RuntimeException("Initialization of Bean【className=" + className + "】 failed ", e);
			}
		}
	}

	private static Properties loadProperties(String cfgFile) {
		Properties props = new Properties();
		InputStream in = null;
		try {
			in = BeanFactory.class.getResourceAsStream(cfgFile);
			props.load(in);
		} catch (IOException e) {
			throw new RuntimeException("loading properties file failed ：" + cfgFile, e);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return props;
	}

	/**
	 * get relevant Beans instance
	 * 
	 * @param key
	 * @return
	 */
	public static Object getBean(String key) {
		return beans.get(key);
	}

	public static void main(String[] args) {
		System.out.println(beans);
		// InputStream in = BeanFactory.class.getResourceAsStream("/beans.properties");
		// System.out.println(in);
	}
}
