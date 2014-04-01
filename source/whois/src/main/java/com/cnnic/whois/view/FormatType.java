package com.cnnic.whois.view;

public enum FormatType {
	NONE("none"), JSON("application/json"), RDAPANDJSON("application/rdap+json"), RDAPORJSON(
			"application/rdap+json;application/json"), XML("application/xml"), HTML(
			"application/html"), TEXTPLAIN("text/plain");
	private String name;
	
	public static FormatType getFormatType(String name) {
		FormatType[] queryJoinTypes = FormatType.values();
		for (FormatType type : queryJoinTypes) {
			if (type.getName().equals(name)) {
				return type;
			}
		}
		return null;
	}
	
	public boolean isJsonFormat() {
		return this.equals(JSON) || this.equals(RDAPANDJSON)
				|| this.equals(RDAPORJSON);
	}
	
	public boolean isXmlFormat() {
		return this.equals(XML);
	}

	public boolean isJsonOrXmlFormat() {
		return isJsonFormat() || isXmlFormat();
	}
	
	public boolean isHtmlFormat() {
		return this.equals(HTML);
	}
	
	public boolean isTextFormat() {
		return this.equals(TEXTPLAIN);
	}

	public boolean isNotNoneFormat() {
		return (isJsonOrXmlFormat() || isHtmlFormat() || isTextFormat());
	}
	
	private FormatType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
