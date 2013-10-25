package com.cnnic.whois.bean.index;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.solr.client.solrj.beans.Field;

import com.cnnic.whois.bean.Domain;

public class DomainIndex implements Index {
	private static String DNRDOMAIN_TYPE = "dnrDomain";
	private static String RIRDOMAIN_TYPE = "rirDomain";

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
	private Map<String, String> propValueMap = new HashMap();
	private Domain domain;

	public boolean isDnrDomain() {
		return DNRDOMAIN_TYPE.equals(this.docType);
	}

	public String getPropValue(String key) {
		return (String) this.propValueMap.get(key);
	}

	public DomainIndex(Domain domain) {
		this.domain = domain;
		try {
			BeanUtils.copyProperties(this, domain);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public DomainIndex() {
	}

	public void initPropValueMap() {
		this.propValueMap.put("Handle", this.handle);
		this.propValueMap.put("Ldh_Name", this.ldhName);
		this.propValueMap.put("Unicode_Name", this.unicodeName);
		this.propValueMap.put("Lang", this.lang);
		this.propValueMap.put("Port43", this.port43);
		this.propValueMap.put("Status", this.status);
	}

	public Domain getDomainBean() {
		if (this.domain == null) {
			this.domain = new Domain();
			this.domain.setId(this.id);
			this.domain.setDocType(this.docType);
			this.domain.setHandle(this.handle);
			this.domain.setLang(this.lang);
			this.domain.setLdhName(this.ldhName);
			this.domain.setPort43(this.port43);
			this.domain.setStatus(this.status);
			this.domain.setUnicodeName(this.unicodeName);
			this.propValueMap.put("Handle", this.handle);
			this.propValueMap.put("Ldh_Name", this.ldhName);
			this.propValueMap.put("Unicode_Name", this.unicodeName);
			this.propValueMap.put("Lang", this.lang);
			this.propValueMap.put("Port43", this.port43);
		}
		return this.domain;
	}

	public String getHandle() {
		return this.handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public String getLdhName() {
		return this.ldhName;
	}

	public void setLdhName(String ldhName) {
		this.ldhName = ldhName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPort43() {
		return this.port43;
	}

	public void setPort43(String port43) {
		this.port43 = port43;
	}

	public String getLang() {
		return this.lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getUnicodeName() {
		return this.unicodeName;
	}

	public void setUnicodeName(String unicodeName) {
		this.unicodeName = unicodeName;
	}

	public Domain getDomain() {
		return this.domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDocType() {
		return this.docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}
}