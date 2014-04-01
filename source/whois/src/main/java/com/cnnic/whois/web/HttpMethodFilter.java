package com.cnnic.whois.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.controller.BaseController;
import com.cnnic.whois.util.AuthenticationHolder;
import com.cnnic.whois.view.ViewResolver;

public class HttpMethodFilter implements Filter {

	@Override
	public void destroy() {
		AuthenticationHolder.remove();
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String m = request.getMethod();
		List<String> allowMethods = new ArrayList<String>();
		allowMethods.add("GET");
		if (!allowMethods.contains(m)) {
			ViewResolver viewResolver = ViewResolver.getResolver();
			QueryParam queryParam = BaseController.praseQueryParams(request);
			viewResolver.displayError400(request, response, queryParam);
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
