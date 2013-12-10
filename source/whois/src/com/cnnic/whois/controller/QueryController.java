package com.cnnic.whois.controller;

import java.io.IOException;
import java.net.IDN;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.execption.RedirectExecption;
import com.cnnic.whois.service.QueryService;
import com.cnnic.whois.util.WhoisUtil;
import com.cnnic.whois.util.validate.ValidateUtils;
import com.cnnic.whois.view.ViewResolver;

@Controller
@RequestMapping("/well-known/rdap")
public class QueryController extends BaseController {
	@Autowired
	private QueryService queryService;
	@Autowired
	private ViewResolver viewResolver;

	@RequestMapping(value = "/domain/{domainName}", method = RequestMethod.GET)
	@ResponseBody
	public void queryDomain(@PathVariable String domainName,
			HttpServletRequest request, HttpServletResponse response)
			throws QueryException, RedirectExecption, IOException,
			ServletException {
		domainName = StringUtils.trim(domainName);
		String queryParaPuny = IDN.toASCII(domainName);
		Map<String, Object> resultMap = null;
		QueryParam queryParam = super.praseQueryParams(request);
		if (!ValidateUtils.validateDomainName(queryParaPuny)) {
			resultMap = WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE);
		}else{
			queryParam.setQ(domainName);
			resultMap = queryService.queryDomain(queryParam);
			System.err.println(resultMap);
		}	
		viewResolver.writeResponse(queryParam.getFormat(), request, response,
				resultMap, 0);
	}

	@RequestMapping(value = "/entity/{entityName}", method = RequestMethod.GET)
	@ResponseBody
	public String queryEntity(@PathVariable String domainName,
			HttpServletRequest request) {
		return null;
	}

	@RequestMapping(value = "/nameserver/{nsName}", method = RequestMethod.GET)
	@ResponseBody
	public String queryNs(@PathVariable String domainName,
			HttpServletRequest request) {
		return null;
	}

	@RequestMapping(value = "/autnum/{autnum}", method = RequestMethod.GET)
	@ResponseBody
	public String queryAs(@PathVariable String domainName,
			HttpServletRequest request) {
		return null;
	}

	@RequestMapping(value = "/dsData/{dsData}", method = RequestMethod.GET)
	@ResponseBody
	public String queryDsData(@PathVariable String domainName,
			HttpServletRequest request) {
		return null;
	}

	@RequestMapping(value = "/events/{events}", method = RequestMethod.GET)
	@ResponseBody
	public String queryEvents(@PathVariable String domainName,
			HttpServletRequest request) {
		return null;
	}
}
