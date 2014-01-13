package com.cnnic.whois.solr.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.solr.core.query.result.FacetPage;

import com.cnnic.whois.solr.bean.Product;

public interface ProductRepository extends CrudRepository<Product, String> {
	
	Page<Product> findByPopularity(Integer popularity);

	FacetPage<Product> findByNameStartingWithAndFacetOnAvailable(String namePrefix);

	Page<Product> findByAvailableTrue();

}
