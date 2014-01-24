package com.cnnic.whois.bean.solr;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EntityBeanMapper implements RowMapper<EntityBean> {

	public EntityBean mapRow(ResultSet rs, int row) throws SQLException {
		EntityBean entityBean = new EntityBean();
		entityBean.setId(rs.getString("id"));
		entityBean.setDocType(rs.getString("docType"));
		entityBean.setHandle(rs.getString("handle"));
		entityBean.setEntityNames(rs.getString("Entity_Names"));
		entityBean.setStatus(rs.getString("Status"));
		entityBean.setEmails(rs.getString("Emails"));
		entityBean.setPort43(rs.getString("Port43"));
		
		entityBean.setRoles(rs.getString("Roles"));
		entityBean.setLang(rs.getString("Lang"));
		entityBean.setBday(rs.getString("Bday"));
		entityBean.setAnniversary(rs.getString("Anniversary"));
		entityBean.setKind(rs.getString("Kind"));
		entityBean.setLanguageTag1(rs.getString("Language_Tag_1"));
		entityBean.setLanguageTag2(rs.getString("Language_Tag_2"));
		
		entityBean.setPref1(rs.getString("Pref1"));
		entityBean.setPref2(rs.getString("Pref2"));
		entityBean.setOrg(rs.getString("Org"));
		entityBean.setTitle(rs.getString("Title"));
		entityBean.setRole(rs.getString("Role"));
		entityBean.setGeo(rs.getString("Geo"));
		
		entityBean.setKey(rs.getString("Key"));
		entityBean.setTz(rs.getString("Tz"));
		entityBean.setUrl(rs.getString("Url"));
		
		return entityBean;
	}
}