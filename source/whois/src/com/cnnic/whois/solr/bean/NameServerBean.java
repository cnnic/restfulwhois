package com.cnnic.whois.solr.bean;

import org.apache.solr.client.solrj.beans.Field;

public class NameServerBean {

	@Field("id")
	private String id;
	
	@Field("docType")
	private String docType;
	
	@Field("handle")
	private String handle;
	
	@Field("ldhName")
	private String ldhName;
	
	@Field("ipV4Address")
	private String ipV4Address;
	
	@Field("status")
	private String status;
	
	@Field("port43")
	private String port43;
	
	@Field("lang")
	private String lang;
	
	@Field("unicodeName")
	private String unicodeName;
	
	@Field("ipV6Address")
	private String ipV6Address;
	
	
	public String getDocType() {
		return docType;
	}
	
	public void setDocType(String docType) {
		this.docType = docType;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getHandle() {
		return handle;
	}
	
	public void setHandle(String handle) {
		this.handle = handle;
	}
	
	public String getLdhName() {
		return ldhName;
	}
	
	public void setLdhName(String ldhName) {
		this.ldhName = ldhName;
	}
	
	public String getIpV4Address() {
		return ipV4Address;
	}
	
	public void setIpV4Address(String ipV4Address) {
		this.ipV4Address = ipV4Address;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getPort43() {
		return port43;
	}
	
	public void setPort43(String port43) {
		this.port43 = port43;
	}
	
	public String getLang() {
		return lang;
	}
	
	public void setLang(String lang) {
		this.lang = lang;
	}
	
	public String getUnicodeName() {
		return unicodeName;
	}
	
	public void setUnicodeName(String unicodeName) {
		this.unicodeName = unicodeName;
	}
	
	public String getIpV6Address() {
		return ipV6Address;
	}
	
	public void setIpV6Address(String ipV6Address) {
		this.ipV6Address = ipV6Address;
	}
	
}