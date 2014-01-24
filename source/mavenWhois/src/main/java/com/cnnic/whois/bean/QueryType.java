package com.cnnic.whois.bean;

public enum QueryType {
	NONE("none"), DOMAIN("domain"), ENTITY("entity"), NAMESERVER("nameServer"), AUTNUM(
			"autnum"), DSDATA("dsData"), EVENTS("events"), HELP("help"), IP(
			"ip"), KEYDATA("keyData"), LINKS("links"), NOTICES("notices"), PHONES(
			"phones"), POSTALADDRESS("postalAddress"), REMARKS("remarks"), SECUREDNS(
			"secureDNS"), VARIANTS("variants"), DNRDOMAIN("dnrDomain"), RIRDOMAIN(
			"rirDomain"), DNRENTITY("dnrEntity"), RIRENTITY("rirEntity"), ENTITYSEARCH(
			"entitySearch"), DELETATIONKEY("delegationKey"), ERRORMSG(
			"errorMsg"), SEARCHDOMAIN("fuzzyDomain"), SEARCHENTITY(
			"fuzzyEntity"), SEARCHNS("fuzzyNs"), IPREDIRECTION("ipRedirection"), REDIRECTION(
			"redirection"),PUBLICIDS("publicIds");
	private String name;

	public static QueryType getQueryType(String name) {
		QueryType[] queryTypes = QueryType.values();
		for (QueryType joinType : queryTypes) {
			if (joinType.getName().equals(name)) {
				return joinType;
			}
		}
		return null;
	}
	private QueryType(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
