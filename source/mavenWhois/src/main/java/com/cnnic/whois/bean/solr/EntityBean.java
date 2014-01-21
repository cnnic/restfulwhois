package com.cnnic.whois.bean.solr;

import org.apache.solr.client.solrj.beans.Field;

public class EntityBean {

	@Field("id")
	private String id;

	@Field("docType")
	private String docType;

	@Field("handle")
	private String handle;

	@Field("entityNames")
	private String entityNames;

	@Field("status")
	private String status;
	
	@Field("emails")
	private String emails;

	@Field("port43")
	private String port43;

	@Field("roles")
	private String roles;

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

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public String getEntityNames() {
		return entityNames;
	}

	public void setEntityNames(String entityNames) {
		this.entityNames = entityNames;
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

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getEmails() {
		return emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getBday() {
		return bday;
	}

	public void setBday(String bday) {
		this.bday = bday;
	}

	public String getAnniversary() {
		return anniversary;
	}

	public void setAnniversary(String anniversary) {
		this.anniversary = anniversary;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getLanguageTag1() {
		return languageTag1;
	}

	public void setLanguageTag1(String languageTag1) {
		this.languageTag1 = languageTag1;
	}

	public String getLanguageTag2() {
		return languageTag2;
	}

	public void setLanguageTag2(String languageTag2) {
		this.languageTag2 = languageTag2;
	}

	public String getPref1() {
		return pref1;
	}

	public void setPref1(String pref1) {
		this.pref1 = pref1;
	}

	public String getPref2() {
		return pref2;
	}

	public void setPref2(String pref2) {
		this.pref2 = pref2;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getGeo() {
		return geo;
	}

	public void setGeo(String geo) {
		this.geo = geo;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTz() {
		return tz;
	}

	public void setTz(String tz) {
		this.tz = tz;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}