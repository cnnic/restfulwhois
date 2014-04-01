package com.cnnic.whois.bean;

public enum QueryJoinType {
	ENTITIES("entities"), NAMESERVER("nameServer"), DSDATA("dsData"), EVENTS(
			"events"), KEYDATA("keyData"), DELEGATIONKEYS("delegationKeys"), PUBLICIDS(
			"publicIds"), LINKS("links"), NOTICES("notices"), PHONES("phones"), POSTALADDRESS(
			"postalAddress"), REMARKS("remarks"), SECUREDNS("secureDNS"), VARIANTS(
			"variants");
	private String name;

	public static QueryJoinType getQueryJoinType(String name) {
		QueryJoinType[] queryJoinTypes = QueryJoinType.values();
		for (QueryJoinType joinType : queryJoinTypes) {
			if (joinType.getName().equals(name)) {
				return joinType;
			}
		}
		return null;
	}

	private QueryJoinType(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
