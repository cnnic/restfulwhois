package com.cnnic.whois.solr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnnic.whois.solr.bean.Product;
import com.cnnic.whois.solr.dao.SolrProductRepository;

@Controller
public class TestController {

	@Autowired
	private SolrProductRepository solrProductRepository;

	@RequestMapping(value = "/index/test")
	public String test() throws Exception {

		Iterable<Product> pd = solrProductRepository.findAll();
		for (Product p : pd) {
			System.out.println(p.getName());
		}

		return "test_1";
	}
}
