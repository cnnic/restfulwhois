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

import com.cnnic.whois.bean.solr.EntityBean;
import com.cnnic.whois.bean.solr.EntityBeanMapper;

@Repository
public class SolrEntityBeanDao extends MySimpleSolrRepository<EntityBean, String> {
	
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<EntityBean> queryEntityBean(){
		String sql = "select trim(concat('dnrentity-',handle)) id,'dnrEntity' docType,t.*  from dnrentity t "
					+ " union "
					+ " select trim(concat('rirentity-',handle)) id,'rirEntity' docType,t.*,'' status,'' port43  from rirentity t " 
					+ " limit 5";
		return this.getJdbcTemplate().query(sql,  new EntityBeanMapper());
	}
	
	public Page<EntityBean> findByNameExpression(String namePrefix) {
		FacetQuery query = new SimpleFacetQuery(new Criteria(SolrSearchableFields.NAME).expression(namePrefix));
		query.setFacetOptions(new FacetOptions(SolrSearchableFields.AVAILABLE));
		return getSolrOperations().queryForFacetPage(query, EntityBean.class);
	}
	
}
