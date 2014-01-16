package com.cnnic.whois.solr.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class NameServerBeanMapper implements RowMapper<NameServerBean> {

	public NameServerBean mapRow(ResultSet rs, int row) throws SQLException {
		NameServerBean nameServerBean = new NameServerBean();
		nameServerBean.setId(rs.getString("id"));
		nameServerBean.setDocType(rs.getString("docType"));
		nameServerBean.setHandle(rs.getString("handle"));
		nameServerBean.setLdhName(rs.getString("ldh_name"));
		nameServerBean.setIpV4Address(rs.getString("IPV4_Addresses"));
		
		nameServerBean.setStatus(rs.getString("status"));
		nameServerBean.setPort43(rs.getString("port43"));
		nameServerBean.setLang(rs.getString("lang"));
		nameServerBean.setUnicodeName(rs.getString("unicode_name"));
		nameServerBean.setIpV6Address(rs.getString("IPV6_Addresses"));
		
		return nameServerBean;
	}
}