package com.cnnic.whois.bean.solr;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DomainBeanMapper implements RowMapper<DomainBean> {

	public DomainBean mapRow(ResultSet rs, int row) throws SQLException {
		DomainBean domainBean = new DomainBean();
		domainBean.setId(rs.getString("id"));
		domainBean.setDocType(rs.getString("docType"));
		domainBean.setHandle(rs.getString("handle"));
		domainBean.setLdhName(rs.getString("ldh_name"));
		
		domainBean.setStatus(rs.getString("status"));
		domainBean.setPort43(rs.getString("port43"));
		domainBean.setLang(rs.getString("lang"));
		domainBean.setUnicodeName(rs.getString("unicode_name"));
		
		return domainBean;
	}
}