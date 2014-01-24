package com.cnnic.whois.dao.solr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.FacetOptions;
import org.springframework.data.solr.core.query.FacetQuery;
import org.springframework.data.solr.core.query.SimpleFacetQuery;
import org.springframework.data.solr.repository.mysupport.MySimpleSolrRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cnnic.whois.bean.solr.NameServerBean;
import com.cnnic.whois.bean.solr.NameServerBeanMapper;

@Repository
public class SolrNameServerBeanDao extends MySimpleSolrRepository<NameServerBean, String> {
	
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<NameServerBean> queryNameServerBean(){
		String sql = "select 'rirDomain' docType,handle,ldh_name,IPV4_Addresses,status,port43,lang,unicode_name,IPV6_Addresses from nameserver " 
					+ " limit 5";
		return this.getJdbcTemplate().query(sql,  new NameServerBeanMapper());
	}
	
	public Page<NameServerBean> findByNameExpression(String namePrefix) {
		FacetQuery query = new SimpleFacetQuery(new Criteria(SolrSearchableFields.NAME).expression(namePrefix));
		query.setFacetOptions(new FacetOptions(SolrSearchableFields.AVAILABLE));
		return getSolrOperations().queryForFacetPage(query, NameServerBean.class);
	}
	
}
