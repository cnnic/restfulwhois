package com.cnnic.whois.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnnic.whois.bean.QueryType;
@Service
public class ViewResolver {
	private static ViewResolver resolver = new ViewResolver();

	public static ViewResolver getResolver() {
		return resolver;
	}

	@Autowired
	private List<ResponseWriter> responseWriters  = new ArrayList<ResponseWriter>();
	
	public ViewResolver() {
		super();
		init();
	}

	private void init() {
		responseWriters.add(new JsonResponseWriter());
		responseWriters.add(new HtmlResponseWriter());
		responseWriters.add(new XmlResponseWriter());
		responseWriters.add(new TextResponseWriter());
	}
	
	public Map<String, Object> format(Map<String, Object> map,
			FormatType formatType) {
		for (ResponseWriter writer : responseWriters) {
			if (writer.support(formatType)) {
				return writer.format(map);
			}
		}
		return map;
	}

	public void writeResponse(FormatType formatType, QueryType queryType,
			HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> map) throws IOException,
			ServletException {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		for (ResponseWriter writer : responseWriters) {
			if (writer.support(formatType)) {
				if (formatType.isJsonFormat() || formatType.isXmlFormat()) {
					result = writer.getMapField(queryType, map);
				    writer.writeResponse(request, response, result);
				} else {
					writer.writeResponse(request, response, map);
				}
			}
		}
	}

	public void displayOverTimeMessage(HttpServletRequest request,
			HttpServletResponse response, FormatType formatType, String role)
			throws IOException, ServletException {
		for (ResponseWriter writer : responseWriters) {
			if (writer.support(formatType)) {
				writer.displayOverTimeMessage(request, response, role);
			}
		}
	}

	public List<ResponseWriter> getResponseWriters() {
		return responseWriters;
	}

	public void setResponseWriters(List<ResponseWriter> responseWriters) {
		this.responseWriters = responseWriters;
	}
}
