package com.cnnic.whois.controller;

import java.io.IOException;
import java.net.IDN;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnnic.whois.bean.DomainQueryParam;
import com.cnnic.whois.bean.IpQueryParam;
import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.dao.QueryEngine;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.execption.RedirectExecption;
import com.cnnic.whois.service.QueryService;
import com.cnnic.whois.util.WhoisUtil;
import com.cnnic.whois.util.validate.ValidateUtils;

@Controller
@RequestMapping("/{dot}well-known/rdap")
public class QueryController extends BaseController {
	@Autowired
	private QueryService queryService;
	@Autowired
	private QueryEngine queryEngine;

	@RequestMapping(value = "/domains", method = RequestMethod.GET)
	@ResponseBody
	public void fuzzyQueryDomain(@RequestParam(required = true) String name,
			HttpServletRequest request, HttpServletResponse response)
			throws QueryException, RedirectExecption, IOException,
			ServletException {
		name = StringUtils.trim(name);
		String punyDomainName = name;
		Map<String, Object> resultMap = null;
		DomainQueryParam domainQueryParam = super.praseDomainQueryParams(request);
		try{
			punyDomainName = IDN.toASCII(name);//long lable exception
		}catch(Exception e){//TODO:delete ,exception filter
			resultMap = WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE);
			renderResponse(request, response, resultMap, domainQueryParam);
			return;
		}
		if (!ValidateUtils.validateDomainName(punyDomainName)) {
			resultMap = WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE);
		} else {
			domainQueryParam.setQueryType(QueryType.SEARCHDOMAIN);
			domainQueryParam.setQ(name);
			domainQueryParam.setDomainPuny(punyDomainName);
			resultMap =  queryService.query(domainQueryParam);
			request.setAttribute("pageBean", domainQueryParam.getPage());
			request.setAttribute("queryPath", "domains");
			request.setAttribute("queryPara", IDN.toUnicode(punyDomainName));
		}
		renderResponse(request, response, resultMap, domainQueryParam);
	}
	
	@RequestMapping(value = "/domain/{domainName}", method = RequestMethod.GET)
	@ResponseBody
	public void queryDomain(@PathVariable String domainName,
			HttpServletRequest request, HttpServletResponse response)
			throws QueryException, RedirectExecption, IOException,
			ServletException {
		domainName = StringUtils.trim(domainName);
		String punyDomainName = domainName;
		Map<String, Object> resultMap = null;
		DomainQueryParam domainQueryParam = super.praseDomainQueryParams(request);
		try{
			punyDomainName = IDN.toASCII(domainName);//long lable exception
		}catch(Exception e){
			resultMap = WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE);
			renderResponse(request, response, resultMap, domainQueryParam);
			return;
		}
		if (!ValidateUtils.validateDomainName(punyDomainName)) {
			resultMap = WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE);
		} else {
			domainQueryParam.setQueryType(QueryType.DOMAIN);
			domainQueryParam.setQ(domainName);
			domainQueryParam.setDomainPuny(punyDomainName);
			resultMap = queryService.queryDomain(domainQueryParam);
			request.setAttribute("queryPara", IDN.toUnicode(punyDomainName));
		}
		renderResponse(request, response, resultMap, domainQueryParam);
	}

	@RequestMapping(value = "/entities", method = RequestMethod.GET)
	@ResponseBody
	public void fuzzyQueryEntity(@RequestParam(required = false) String fn,
			@RequestParam(required = false) String handle,
			HttpServletRequest request, HttpServletResponse response)
			throws QueryException, SQLException, IOException, ServletException {
		Map<String, Object> resultMap = null;
		QueryParam queryParam = super.praseQueryParams(request);
		if(StringUtils.isBlank(fn) && StringUtils.isBlank(handle)){
			resultMap = WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE);
			renderResponse(request, response, resultMap, queryParam);
			return;
		}
		String q = fn;
		if(StringUtils.isNotBlank(handle)){
			q = handle;
		}
		String decodeQ = WhoisUtil.toChineseUrl(q);
		String fuzzyQuerySolrPropName = "handle";
		if(StringUtils.isNotBlank(fn)){
			fuzzyQuerySolrPropName = "entityNames";
		}
		resultMap = queryService.fuzzyQueryEntity(fuzzyQuerySolrPropName,
				decodeQ);
		request.setAttribute("pageBean", queryParam.getPage());
		request.setAttribute("queryPath", "entities");
		renderResponse(request, response, resultMap, queryParam);
	}
	
	@RequestMapping(value = "/entity/{entityName}", method = RequestMethod.GET)
	@ResponseBody
	public void queryEntity(@PathVariable String entityName,
			HttpServletRequest request, HttpServletResponse response)
			throws QueryException, SQLException, IOException, ServletException {
		QueryParam queryParam = super.praseQueryParams(request);
		queryParam.setQ(entityName);
		Map<String, Object> resultMap = queryService.queryEntity(queryParam);
		renderResponse(request, response, resultMap, queryParam);
	}

	@RequestMapping(value = "/nameservers", method = RequestMethod.GET)
	@ResponseBody
	public void fuzzyQueryNs(@RequestParam(required = false) String q,
			HttpServletRequest request, HttpServletResponse response)
			throws QueryException, SQLException, IOException, ServletException,
			RedirectExecption {
		q = StringUtils.trim(q);
		String decodeQ = WhoisUtil.toChineseUrl(q);
		String punyQ = IDN.toASCII(decodeQ);
		Map<String, Object> resultMap = null;
		QueryParam queryParam = super.praseQueryParams(request);
		if (!ValidateUtils.verifyNameServer(punyQ)) {
			resultMap = WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE);
		} else {
			queryParam.setQ(punyQ);
			request.setAttribute("pageBean", queryParam.getPage());
			request.setAttribute("queryPath", "nameservers");
			resultMap = queryService.fuzzyQueryNameServer(queryParam);
		}
		renderResponse(request, response, resultMap, queryParam);
	}
	
	@RequestMapping(value = "/nameserver/{nsName}", method = RequestMethod.GET)
	@ResponseBody
	public void queryNs(@PathVariable String nsName,
			HttpServletRequest request, HttpServletResponse response)
			throws QueryException, SQLException, IOException, ServletException {
		nsName = StringUtils.trim(nsName);
		String punyNsName = IDN.toASCII(WhoisUtil.toChineseUrl(nsName));
		Map<String, Object> resultMap = null;
		QueryParam queryParam = super.praseQueryParams(request);
		if (!ValidateUtils.verifyNameServer(punyNsName)) {
			resultMap = WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE);
		} else {
			queryParam.setQ(nsName);
			queryParam.setQueryType(QueryType.NAMESERVER);
			resultMap = queryService.query(queryParam);
			request.setAttribute("queryPara", IDN.toUnicode(punyNsName));
		}
		renderResponse(request, response, resultMap, queryParam);
	}

	@RequestMapping(value = "/autnum/{autnum}", method = RequestMethod.GET)
	@ResponseBody
	public void queryAs(@PathVariable String autnum,
			HttpServletRequest request, HttpServletResponse response)
			throws QueryException, RedirectExecption, IOException,
			ServletException {
		QueryParam queryParam = super.praseQueryParams(request);
		queryParam.setQ(autnum);
		Map<String, Object> resultMap = queryService.queryAS(queryParam);
		renderResponse(request, response, resultMap, queryParam);
	}

	@RequestMapping(value = "/dsData/{q}", method = RequestMethod.GET)
	@ResponseBody
	public void queryDsData(@PathVariable String q, HttpServletRequest request,
			HttpServletResponse response) throws QueryException,
			RedirectExecption, IOException, ServletException {
		query(QueryType.DSDATA, q, request, response);
	}

	@RequestMapping(value = "/events/{q}", method = RequestMethod.GET)
	@ResponseBody
	public void queryEvents(@PathVariable String q, HttpServletRequest request,
			HttpServletResponse response) throws QueryException,
			RedirectExecption, IOException, ServletException {
		query(QueryType.EVENTS, q, request, response);
	}

	@RequestMapping(value = "/help/{q}", method = RequestMethod.GET)
	@ResponseBody
	public void queryHelp(@PathVariable String help,
			HttpServletRequest request, HttpServletResponse response)
			throws QueryException, RedirectExecption, IOException,
			ServletException {
		Map<String, Object> resultMap = null;
		QueryParam queryParam = super.praseQueryParams(request);
		if (StringUtils.isNotBlank(help)) {
			resultMap = WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE);
		} else {
			queryParam.setQ("helpID");
			resultMap = queryService.queryHelp(queryParam);
			request.setAttribute("queryPara", queryParam);
		}
		renderResponse(request, response, resultMap, queryParam);
	}

	@RequestMapping(value = "/ip/{ip}", method = RequestMethod.GET)
	@ResponseBody
	public void queryIp(@PathVariable String ip, HttpServletRequest request,
			HttpServletResponse response) throws QueryException,
			RedirectExecption, IOException, ServletException {
		ip = StringUtils.trim(ip);
		Map<String, Object> resultMap = null;
		IpQueryParam queryParam = super.praseIpQueryParams(request);
		String ipLength = "0";
		String strInfo = ip;
		if (ip.indexOf(WhoisUtil.PRX) >= 0) {
			String[] infoArray = ip.split(WhoisUtil.PRX);
			if(infoArray.length > 2){//1.1.1.1//32
				resultMap = WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE);
				viewResolver.writeResponse(queryParam.getFormat(), request, response,
						resultMap, 0);
				return;
			}
			if(infoArray.length > 1){
				strInfo = infoArray[0];
				ipLength = infoArray[1];
			}
		}
		if (!ValidateUtils.verifyIP(strInfo, ipLength)) {
			resultMap = WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE);
			viewResolver.writeResponse(queryParam.getFormat(), request, response,
					resultMap, 0);
			return;
		}
		queryParam.setQ(ip);
		queryParam.setIpInfo(strInfo);
		queryParam.setIpLength(Integer.parseInt(ipLength));
		resultMap = queryService.queryIP(queryParam);
		request.setAttribute("queryPara", queryParam);
		viewResolver.writeResponse(queryParam.getFormat(), request, response,
				resultMap, 0);
	}

	@RequestMapping(value = "/keyData/{q}", method = RequestMethod.GET)
	@ResponseBody
	public void queryKeyData(@PathVariable String q,
			HttpServletRequest request, HttpServletResponse response)
			throws QueryException, RedirectExecption, IOException,
			ServletException {
		query(QueryType.KEYDATA, q, request, response);
	}

	@RequestMapping(value = "/links/{q}", method = RequestMethod.GET)
	@ResponseBody
	public void queryLinks(@PathVariable String q, HttpServletRequest request,
			HttpServletResponse response) throws QueryException,
			RedirectExecption, IOException, ServletException {
		query(QueryType.LINKS, q, request, response);
	}

	@RequestMapping(value = "/notices/{q}", method = RequestMethod.GET)
	@ResponseBody
	public void queryNotices(@PathVariable String q,
			HttpServletRequest request, HttpServletResponse response)
			throws QueryException, RedirectExecption, IOException,
			ServletException {
		query(QueryType.NOTICES, q, request, response);
	}

	@RequestMapping(value = "/phones/{q}", method = RequestMethod.GET)
	@ResponseBody
	public void queryPhones(@PathVariable String q, HttpServletRequest request,
			HttpServletResponse response) throws QueryException,
			RedirectExecption, IOException, ServletException {
		query(QueryType.PHONES, q, request, response);
	}

	@RequestMapping(value = "/postalAddress/{q}", method = RequestMethod.GET)
	@ResponseBody
	public void queryPostalAddress(@PathVariable String q,
			HttpServletRequest request, HttpServletResponse response)
			throws QueryException, RedirectExecption, IOException,
			ServletException {
		query(QueryType.POSTALADDRESS, q, request, response);
	}

	@RequestMapping(value = "/secureDNS/{q}", method = RequestMethod.GET)
	@ResponseBody
	public void querySecureDNS(@PathVariable String q,
			HttpServletRequest request, HttpServletResponse response)
			throws QueryException, RedirectExecption, IOException,
			ServletException {
		query(QueryType.SECUREDNS, q, request, response);
	}

	@RequestMapping(value = "/remarks/{q}", method = RequestMethod.GET)
	@ResponseBody
	public void queryRemarks(@PathVariable String q,
			HttpServletRequest request, HttpServletResponse response)
			throws QueryException, RedirectExecption, IOException,
			ServletException {
		query(QueryType.REMARKS, q, request, response);
	}

	@RequestMapping(value = "/variants/{q}", method = RequestMethod.GET)
	@ResponseBody
	public void queryVariants(@PathVariable String q,
			HttpServletRequest request, HttpServletResponse response)
			throws QueryException, RedirectExecption, IOException,
			ServletException {
		query(QueryType.VARIANTS, q, request, response);
	}

	private void query(QueryType queryType, String q,
			HttpServletRequest request, HttpServletResponse response)
			throws QueryException, IOException, ServletException {
		Map<String, Object> resultMap = null;
		QueryParam queryParam = praseQueryParams(request);
		queryParam.setQueryType(queryType);
		queryParam.setQ(q);
		if (!ValidateUtils.isCommonInvalidStr(queryParam.getQ())) {
			resultMap = WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE);
		} else {
			resultMap = queryService.query(queryParam);
			request.setAttribute("queryPara", queryParam);
		}
		renderResponse(request, response, resultMap, queryParam);
	}
}