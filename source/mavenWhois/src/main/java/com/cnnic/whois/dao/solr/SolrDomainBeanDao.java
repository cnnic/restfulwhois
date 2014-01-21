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

import com.cnnic.whois.bean.solr.DomainBean;
import com.cnnic.whois.bean.solr.DomainBeanMapper;

@Repository
public class SolrDomainBeanDao extends MySimpleSolrRepository<DomainBean, String> {
	
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<DomainBean> queryDomainBean(){
		String sql = "select trim(concat('dnrDomain-',handle)) id,'dnrDomain' docType,handle,trim(ldh_name) ldh_name,status,port43,lang,trim(unicode_name) unicode_name from dnrdomain " 
					+ " union " 
					+ " select trim(concat('rirDomain-',handle)) id,'rirDomain' docType,handle,trim(ldh_name) ldh_name, '' status,'' port43,'' lang,'' unicode_name from rirdomain " 
					+ " limit 5";
		return this.getJdbcTemplate().query(sql,  new DomainBeanMapper());
	}
	
	public Page<DomainBean> findByNameStartingWithAndFacetOnAvailable(String namePrefix) {
		FacetQuery query = new SimpleFacetQuery(new Criteria(SolrSearchableFields.NAME).expression(namePrefix));
		query.setFacetOptions(new FacetOptions(SolrSearchableFields.AVAILABLE));
		return getSolrOperations().queryForFacetPage(query, DomainBean.class);
	}
	
}
