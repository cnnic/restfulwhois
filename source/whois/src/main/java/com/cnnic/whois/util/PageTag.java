package com.cnnic.whois.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.cnnic.whois.bean.PageBean;

public class PageTag extends TagSupport {
	private static final long serialVersionUID = -2230112545469172831L;
	private static final String PAGINATION_INFO = "total<b> %s </b>page,<b> %s </b>records";
	private String id;
	private String pageBeanName;
	private String scope;
	private String onClick = "";
	private int maxShowPage = 10;
	private static int PAGE_MAX_SHOW_RECORDS = 5;
	private String href;
	private String pageName;
	private boolean viewAll;
	private boolean maxRecordConfigurable;
	private String onChange = "";
	private boolean dashboardShow;
	private boolean maxShow;
	private int[] listRecordConfigureNums = { 15, 30, 50, 100 };
	private int[] dashboardRecordConfigureNums = { 5, 20, 50 };
	private int[] maxDashboardRecordConfigureNums = { 20, 80, 200 };

	public int doStartTag() throws JspException {
		PageBean pageBean = null;

		if ("page".equals(this.scope))
			pageBean = (PageBean) this.pageContext
					.getAttribute(this.pageBeanName);
		else if ("request".equals(this.scope))
			pageBean = (PageBean) this.pageContext.getAttribute(
					this.pageBeanName, 2);
		else if ("session".equals(this.scope))
			pageBean = (PageBean) this.pageContext.getAttribute(
					this.pageBeanName, 3);
		else {
			pageBean = (PageBean) this.pageContext
					.findAttribute(this.pageBeanName);
		}

		if (pageBean == null) {
			throw new JspException("Cann't find attribute " + this.pageBeanName
					+ " in any scope!");
		}

		HttpServletRequest request = (HttpServletRequest) this.pageContext
				.getRequest();
		Map<String, Object> paramMap = new HashMap<String, Object>(request.getParameterMap());
		paramMap.remove(getPageName());
		if (!this.maxRecordConfigurable) {
			paramMap.remove(getViewAll());
		}
		pageBean.setParameterMap(paramMap);
		try {
			int total = pageBean.getRecordsCount();

			JspWriter out = this.pageContext.getOut();

			if (total == 0) {
				out.print("<div class=\"pagination txt-right\">no records</div>");

				return 6;
			}

			int totalPage = (int) Math.ceil(total
					* 1.0D
					/ (pageBean.getMaxRecords() == null ? PAGE_MAX_SHOW_RECORDS
							: pageBean.getMaxRecords().intValue()));

			if (this.href.indexOf("?") == -1) {
				this.href += "?";
			} else if (!this.href.endsWith("&")) {
				this.href += "&";
			}

			String urlPatten = getParamtersUrlPatten(paramMap, this.href);

			this.href += ("".equals(urlPatten) ? "" : new StringBuilder(
					String.valueOf(urlPatten)).append("&").toString());

			int currentPage = pageBean.getCurrentPage();

			int start = currentPage - (this.maxShowPage - 1) / 2;
			int end = currentPage
					+ (this.maxShowPage - (this.maxShowPage - 1) / 2);
			if (start < 1) {
				end = end - start + 1;
				start = 1;
			}

			if (currentPage > totalPage) {
				totalPage = currentPage;
			}

			if (totalPage < end) {
				start = totalPage >= this.maxShowPage ? totalPage
						- this.maxShowPage + 1 : 1;
			}
			out.print("<div class=\"pagination txt-right\">");

			if (this.maxRecordConfigurable) {
				int[] recordConfigureNums = this.listRecordConfigureNums;
				if (this.dashboardShow) {
					if (this.maxShow)
						recordConfigureNums = this.maxDashboardRecordConfigureNums;
					else {
						recordConfigureNums = this.dashboardRecordConfigureNums;
					}
				}
				out.print("<select onchange=\"" + this.onChange
						+ "\" id=\"max-record-field" + getId()
						+ "\" class=\"max-record-select\">");

				for (int i = 0; i < recordConfigureNums.length; i++) {
					out.print("<option class=\"max-record-option\" value=\""
							+ recordConfigureNums[i] + "\">"
							+ recordConfigureNums[i] + "</option>");
				}
				out.print("</select>");
				if (StringUtils.isEmpty(this.onChange)) {
					printJs(out);
				}
				out.print("<script type=\"text/javascript\">");
				out.print("$().ready(function(){");
				out.print("$('#max-record-field" + getId() + "').val("
						+ pageBean.getMaxRecords() + ");");
				out.print("});");
				out.print("</script>");
			}

			out.println("<ul>");

			if (start > 1) {
				out.print("<li><a currentPage='1' href=\"" + this.href
						+ getPageName() + "=1" + "\" onclick=\"" + this.onClick
						+ "\">" + "&lt;&lt;</a></li>");
			}

			if (currentPage < 1) {
				currentPage = 1;
			}

			if (currentPage > 1) {
				out.print("<li><a currentPage='" + (currentPage - 1)
						+ "' href=\"" + this.href + getPageName() + "="
						+ (currentPage - 1) + "\" onclick=\"" + this.onClick
						+ "\">" + "&lt;</a></li>");
			}

			if ((start > currentPage) && (currentPage > 0)) {
				out.print("<li class=\"active\"><a>" + currentPage
						+ "</a></li>");
			}

			for (int i = start; (i <= totalPage) && (i < end); i++) {
				if (i == currentPage) {
					out.print("<li class=\"active\"><a>" + i + "</a></li>");
				} else {
					out.print("<li><a currentPage='" + i + "' href=\""
							+ this.href + getPageName() + "=" + i
							+ "\" onclick=\"" + this.onClick + "\">" + i
							+ "</a></li>");
				}
			}
			if (currentPage < totalPage) {
				out.print("<li><a currentPage='"
						+ (pageBean.getCurrentPage() + 1) + "' href=\""
						+ this.href + getPageName() + "="
						+ (pageBean.getCurrentPage() + 1) + "\" onclick=\""
						+ this.onClick + "\">" + "&gt;</a></li>");
			}

			if (totalPage > 0) {
				if (totalPage > currentPage) {
					out.print("<li><a currentPage='" + totalPage + "'  href=\""
							+ this.href + getPageName() + "=" + totalPage
							+ "\" onclick=\"" + this.onClick + "\">"
							+ "&gt;&gt;</a></li>");
				}
				out.print("</ul>");
				out.print("<p class='pagep'>");
				String.format(
						PAGINATION_INFO,
						new Object[] { Integer.valueOf(totalPage),
								Integer.valueOf(pageBean.getRecordsCount()) });
				String disyinfo = String.format(
						PAGINATION_INFO,
						new Object[] { Integer.valueOf(totalPage),
								Integer.valueOf(pageBean.getRecordsCount()) });

				out.print(disyinfo);
				if (this.viewAll) {
					String viewAllParam = request.getParameter(getViewAll());
					String viewAllInfo = "";
					int index = this.href.indexOf("&" + getViewAll() + "=");
					if (index > 0) {
						int nextIndex = this.href.indexOf("&", index + 1);
						if (nextIndex > 0)
							this.href = (this.href.substring(0, index) + this.href
									.substring(nextIndex));
						else {
							this.href = this.href.substring(0, index);
						}
					}
					if ((viewAllParam == null)
							|| (needViewAllShow(viewAllParam,
									Integer.valueOf(pageBean.getRecordsCount())))) {
						if (totalPage > 1) {
							viewAllInfo = "<a  href=\"" + this.href
									+ getPageName() + "=1&" + getViewAll()
									+ "=" + pageBean.getRecordsCount()
									+ "\" onclick=\"" + this.onClick + "\">"
									+ "viewall" + "</a>";
						}
					} else
						viewAllInfo = "<a id=\"pagination-split-link" + getId()
								+ "\" href=\"" + this.href + getPageName()
								+ "=1" + "\" onclick=\"" + this.onClick + "\">"
								+ "Paging" + "</a>";

					out.print(viewAllInfo);
				}
				out.print("</p></div>");
			}
		} catch (IOException localIOException) {
		}

		return 0;
	}

	private boolean needViewAllShow(String maxRecords, Integer recordCount) {
		return (maxRecords != null)
				&& (Integer.parseInt(maxRecords) < recordCount.intValue());
	}

	private String getViewAll() {
		return "maxRecords";
	}

	private void printJs(JspWriter out) throws IOException {
		out.print("<script type=\"text/javascript\">");
		out.print("$().ready(function(){");
		out.print("$('#max-record-field" + getId() + "').change(function(){");
		out.print("var maxRecords = $(this).children('option:selected').text();");
		out.print("var url = window.location.pathname + window.location.search;");
		out.print("url = replaceQueryFromUrl(url, \"currentPage\", 1);");
		out.print("url = replaceQueryFromUrl(url, \"maxRecords\", maxRecords);");
		out.print("window.location.href = url + window.location.hash;");
		out.print("});");
		out.print("if($('#pagination-split-link" + getId() + "').length> 0){");
		out.print("$('#max-record-field" + getId() + "').hide();");
		out.print("}");
		out.print("});");
		out.print("</script>");
	}

	public void release() {
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

	public void setMaxShowPage(int maxShow) {
		this.maxShowPage = maxShow;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public void setPageBeanName(String pageBeanName) {
		this.pageBeanName = pageBeanName;
	}

	public String getOnClick() {
		return this.onClick;
	}

	public int getMaxShowPage() {
		return this.maxShowPage;
	}

	public String getHref() {
		return this.href;
	}

	public String getScope() {
		return this.scope;
	}

	public String getPageBeanName() {
		return this.pageBeanName;
	}

	public String getPageName() {
		if (this.pageName == null) {
			this.pageName = "currentPage";
		}

		return this.pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public boolean isViewAll() {
		return this.viewAll;
	}

	public void setViewAll(boolean viewAll) {
		this.viewAll = viewAll;
	}

	public boolean isMaxRecordConfigurable() {
		return this.maxRecordConfigurable;
	}

	public void setMaxRecordConfigurable(boolean recordCountConfigurable) {
		this.maxRecordConfigurable = recordCountConfigurable;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOnChange() {
		return this.onChange;
	}

	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}

	public boolean isDashboardShow() {
		return this.dashboardShow;
	}

	public void setDashboardShow(boolean dashboardShow) {
		this.dashboardShow = dashboardShow;
	}

	public boolean isMaxShow() {
		return this.maxShow;
	}

	public void setMaxShow(boolean maxShow) {
		this.maxShow = maxShow;
	}

	public static String getParamtersUrlPatten(Map parameterMap, String href) {
		if (parameterMap == null) {
			return "";
		}

		List myParams = new ArrayList();
		int n = -1;

		if ((n = href.indexOf('?')) > 0) {
			String p = href.substring(n + 1);
			myParams.addAll(Arrays.asList(p.split("&")));
		}

		StringBuilder ret = new StringBuilder();
		Set set = parameterMap.entrySet();

		for (Iterator localIterator = set.iterator(); localIterator.hasNext();) {
			Object elem = localIterator.next();
			Map.Entry entry = (Map.Entry) elem;
			String key = entry.getKey().toString();
			Object value = entry.getValue();

			if ((value instanceof String[])) {
				String[] vs = (String[]) value;

				for (int i = 0; i < vs.length; i++)
					try {
						String p = key
								+ "="
								+ URLEncoder.encode(vs[i], "utf-8").replace(
										"+", "%20");
						if (!myParams.contains(p)) {
							ret.append(p);
							ret.append("&");
						}
					} catch (UnsupportedEncodingException localUnsupportedEncodingException1) {
					}
			} else {
				try {
					String p = key + "="
							+ URLEncoder.encode(String.valueOf(value), "utf-8");

					if (!myParams.contains(p)) {
						ret.append(p);
						ret.append("&");
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

			}

		}

		if (ret.length() > 0) {
			ret.deleteCharAt(ret.length() - 1);
		}

		return ret.toString();
	}
}