package com.cnnic.whois.bean.index;

public interface Index {
	public String getPropValue(String key);

	public void initPropValueMap();

	public String getHandle();

	public String getDocType();
}