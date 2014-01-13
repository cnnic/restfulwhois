package com.cnnic.whois.solr.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.FacetOptions;
import org.springframework.data.solr.core.query.FacetQuery;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleFacetQuery;
import org.springframework.data.solr.core.query.SimpleField;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.repository.mysupport.MySimpleSolrRepository;
import org.springframework.stereotype.Repository;

import com.cnnic.whois.solr.bean.Product;

@Repository
public class SolrProductRepository extends MySimpleSolrRepository<Product, String> implements ProductRepository {
	
	@Override
	public Page<Product> findByPopularity(Integer popularity) {
		Query query = new SimpleQuery(new Criteria(SolrSearchableFields.POPULARITY).is(popularity));
		return getSolrOperations().queryForPage(query, Product.class);
	}

	@Override
	public FacetPage<Product> findByNameStartingWithAndFacetOnAvailable(String namePrefix) {
		FacetQuery query = new SimpleFacetQuery(new Criteria(SolrSearchableFields.NAME).startsWith(namePrefix));
		query.setFacetOptions(new FacetOptions(SolrSearchableFields.AVAILABLE));
		return getSolrOperations().queryForFacetPage(query, Product.class);
	}

	@Override
	public Page<Product> findByAvailableTrue() {
		Query query = new SimpleQuery(new Criteria(new SimpleField(Criteria.WILDCARD)).expression(Criteria.WILDCARD));
		query.addFilterQuery(new SimpleQuery(new Criteria(SolrSearchableFields.AVAILABLE).is(true)));
		
		return getSolrOperations().queryForPage(query, Product.class);
	}
}
