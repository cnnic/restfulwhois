package com.cnnic.whois.bean.index;

import java.util.HashMap;
import java.util.Map;
import org.apache.solr.client.solrj.beans.Field;

public class EntityIndex implements Index {
	private static String DNRENTITY_TYPE = "dnrEntity";
	private static String RIRENTITY_TYPE = "rirEntity";

	@Field("docType")
	private String docType;

	@Field("handle")
	private String handle;

	@Field("entityNames")
	private String entityNames;

	@Field("status")
	private String status;

	@Field("port43")
	private String port43;

	@Field("roles")
	private String roles;

	@Field("emails")
	private String emails;

	@Field("lang")
	private String lang;

	@Field("bday")
	private String bday;

	@Field("anniversary")
	private String anniversary;

	@Field("gender")
	private String gender;

	@Field("kind")
	private String kind;

	@Field("languageTag1")
	private String languageTag1;

	@Field("languageTag2")
	private String languageTag2;

	@Field("pref1")
	private String pref1;

	@Field("pref2")
	private String pref2;

	@Field("org")
	private String org;

	@Field("title")
	private String title;

	@Field("role")
	private String role;

	@Field("geo")
	private String geo;

	@Field("key")
	private String key;

	@Field("tz")
	private String tz;

	@Field("url")
	private String url;
	private Map<String, String> propValueMap = new HashMap();

	public boolean isDnrEntity() {
		return DNRENTITY_TYPE.equals(this.docType);
	}

	public String getPropValue(String key) {
		return (String) this.propValueMap.get(key);
	}

	public void initPropValueMap() {
		this.propValueMap.put("Handle", this.handle);
		this.propValueMap.put("Entity_Names", this.entityNames);
		this.propValueMap.put("Status", this.status);
		this.propValueMap.put("Emails", this.emails);
		this.propValueMap.put("Port43", this.port43);
		this.propValueMap.put("Roles", this.roles);
		this.propValueMap.put("Lang", this.lang);
		this.propValueMap.put("Bday", this.bday);
		this.propValueMap.put("Anniversary", this.anniversary);
		this.propValueMap.put("Gender", this.gender);
		this.propValueMap.put("Kind", this.kind);
		this.propValueMap.put("Language_Tag_1", this.languageTag1);
		this.propValueMap.put("Language_Tag_2", this.languageTag2);
		this.propValueMap.put("Pref1", this.pref1);
		this.propValueMap.put("Pref2", this.pref2);
		this.propValueMap.put("Org", this.org);
		this.propValueMap.put("Title", this.title);
		this.propValueMap.put("Role", this.role);
		this.propValueMap.put("Geo", this.geo);
		this.propValueMap.put("Key", this.key);
		this.propValueMap.put("Tz", this.tz);
		this.propValueMap.put("Url", this.url);
	}

	public String getHandle() {
		return this.handle;
	}

	public String getDocType() {
		return this.docType;
	}
}