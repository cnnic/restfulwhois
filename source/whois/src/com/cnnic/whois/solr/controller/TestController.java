package com.cnnic.whois.solr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnnic.whois.bean.index.DomainIndex;
import com.cnnic.whois.solr.bean.DomainBean;
import com.cnnic.whois.solr.bean.Product;
import com.cnnic.whois.solr.dao.SolrDomainBeanDao;
import com.cnnic.whois.solr.dao.SolrProductRepository;

@Controller
public class TestController {

	@Autowired
	private SolrProductRepository solrProductRepository;
	@Autowired
	private SolrDomainBeanDao solrDomainBeanDao;
	
	@RequestMapping(value = "/index/test")
	public String test() throws Exception {

//		Iterable<Product> pd = solrProductRepository.findAll();
//		for (Product p : pd) {
//			System.out.println(p.getName());
//		}
		
		List<DomainBean> domainBeanList = solrDomainBeanDao.queryDomainBean();
//		for(DomainBean domainBean : domainBeanList){
//			System.out.println(domainBean.getLdhName());
//		}
		solrDomainBeanDao.save(domainBeanList);
		
//		Iterable<DomainBean> domainBeanList = solrDomainBeanDao.findAll();
//		for (DomainBean p : domainBeanList) {
//			System.out.println(p.getLdhName());
//		}
		
//		FacetPage<Product> list =  solrProductRepository.findByNameStartingWithAndFacetOnAvailable("T");
		
//		FacetPage<Product> list =  solrProductRepository.findByNameFuzzyAndFacetOnAvailable("*T*");
//		for(Product pr : list){
//			System.out.println(pr.getName());
//		}
		
//		Page<DomainIndex> pIndex = solrDomainIndexRepository.findByNameStartingWithAndFacetOnAvailable("b*");
//		for(DomainIndex ps : pIndex){
//			System.out.println(ps.getHandle());
//		}
		
		return "test_1";
	}
}
