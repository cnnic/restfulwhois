package com.cnnic.whois.web;

import java.io.IOException;

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
import com.cnnic.whois.util.WhoisProperties;
import com.cnnic.whois.util.WhoisUtil;
import com.cnnic.whois.view.FormatType;
import com.cnnic.whois.view.ViewResolver;

public class WhoisFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String role = WhoisUtil.getUserRole(request);
		long currentTime = new Long(System.currentTimeMillis());
		long accessTime = currentTime;
		boolean isQueryOverTime = queryControl(request.getRemoteAddr(),
				accessTime, role);
		FormatType format = BaseController.getFormatType(request);
		String queryInfo = "";
		String path = request.getRequestURI();
		if (!path.equals("")) {
			queryInfo = path.substring(request.getContextPath().length() + 1);
			if (queryInfo.length() > 0 && queryInfo.endsWith("/")) {
				queryInfo = queryInfo.substring(0, queryInfo.length() - 1);
			}
			if (queryInfo.equals(WhoisProperties.getRdapUrl())
					&& (BaseController.isWebBrowser(request))) {
				format = FormatType.HTML;
				WhoisUtil.clearFormatCookie(request, response);
			}
		}
		if (isQueryOverTime) {
			chain.doFilter(request, response);
		} else {
			QueryParam queryParam = BaseController.praseQueryParams(request);
			displayOverTimeMessage(request, response, format, role,queryParam);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	private void displayOverTimeMessage(HttpServletRequest request,
			HttpServletResponse response, FormatType formatType, String role,QueryParam queryParam)
			throws IOException, ServletException {
		ViewResolver viewResolver = ViewResolver.getResolver();
		viewResolver.displayOverTimeMessage(request, response, formatType, role,queryParam);
	}

	/**
	 * Control call this method when the number of queries in a certain period
	 * of time
	 * 
	 * @param accessTime
	 * @param role
	 * @param userIp
	 * @return If you meet certain requirements, return true, otherwise returns
	 *         false
	 */
	private boolean queryControl(String userIp, long accessTime, String role) {
		if (WhoisUtil.queryRemoteIPMap.get(userIp) == null) {
			WhoisUtil.queryRemoteIPMap.put(userIp, accessTime);
			return true;
		} else {
			long time = accessTime - WhoisUtil.queryRemoteIPMap.get(userIp);
			boolean isOverTime = true;
			if (role.equals(WhoisUtil.ANONYMOUS)) {
				if (time <= Long.parseLong(WhoisProperties.getAnonymousExpireTime()))
					isOverTime = false;
			} else if (role.equals(WhoisUtil.AUTHENTICATED)) {
				if (time <= Long.parseLong(WhoisProperties.getAuthenticatedExpireTime()))
					isOverTime = false;
			} else if (role.equals(WhoisUtil.ROOT)) {
				if (time <= Long.parseLong(WhoisProperties.getRootExpireTime()))
					isOverTime = false;
			}
			WhoisUtil.queryRemoteIPMap.put(userIp, accessTime);
			if (time >= Long.parseLong(WhoisProperties.getExpireTime()))
				WhoisUtil.queryRemoteIPMap.remove(userIp);
			return isOverTime;
		}
	}
}