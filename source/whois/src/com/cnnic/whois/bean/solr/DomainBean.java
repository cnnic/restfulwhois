package com.cnnic.whois.bean.solr;

import org.apache.solr.client.solrj.beans.Field;

public class DomainBean {

	@Field("id")
	private String id;
	@Field("docType")
	private String docType;
	@Field("handle")
	private String handle;
	@Field("ldhName")
	private String ldhName;
	@Field("status")
	private String status;
	@Field("port43")
	private String port43;
	@Field("lang")
	private String lang;
	@Field("unicodeName")
	private String unicodeName;

	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}
}