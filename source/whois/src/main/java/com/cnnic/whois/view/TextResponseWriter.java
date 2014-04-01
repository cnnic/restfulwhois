package com.cnnic.whois.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.util.DataFormat;
import com.cnnic.whois.util.WhoisUtil;

@Component("textResponseWriter")
public class TextResponseWriter extends AbstractResponseWriter {
	private static TextResponseWriter writer = new TextResponseWriter();

	public static ResponseWriter getWriter() {
		return writer;
	}

	@Override
	public String formatKey(String keyName) {
		return formatKeyToCamelCaseIfNotJoinKey(keyName);
	}

	@Override
	public void writeResponse(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> map)
			throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		PrintWriter out = response.getWriter();
		String errorCode = "200";

		request.setAttribute("queryFormat", FormatType.TEXTPLAIN.getName());
		response.setHeader("Access-Control-Allow-Origin", "*");

		errorCode = writeResponseCode(response, map, errorCode);

		// multi-responses
		Iterator<String> iterr = map.keySet().iterator();
		String multiKey = null;
		while (iterr.hasNext()) {
			String key = iterr.next();
			if (key.startsWith(WhoisUtil.MULTIPRX)) {
				multiKey = key;
			}
		}
		if (multiKey != null) {
			Object jsonObj = map.get(multiKey);
			map.remove(multiKey);
			map.put(multiKey.substring(WhoisUtil.MULTIPRX.length()), jsonObj);
		}

		response.setHeader("Content-Type", FormatType.TEXTPLAIN.getName());
		String result = getPresentationFromMap(map, 0);
		out.write(result);
	}

	public void displayOverTimeMessage(HttpServletRequest request,
			HttpServletResponse response, String role,QueryParam queryParam) throws IOException,
			ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		Map<String, Object> map = new LinkedHashMap<String, Object>();

		try {
			map = WhoisUtil.processError(WhoisUtil.RATELIMITECODE,queryParam);
		} catch (QueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		request.setAttribute("queryFormat", FormatType.TEXTPLAIN.getName());
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setStatus(429);
		response.setHeader("Content-Type", FormatType.TEXTPLAIN.getName());
		out.write(getPresentationFromMap(map, 0));
	}

	private String getPresentationFromMap(Map<String, Object> map, int iMode) {
		StringBuffer sb = new StringBuffer();

		if (map == null || map.size() == 0) {
			return "";
		}

		Iterator<String> iterr = map.keySet().iterator();

		while (iterr.hasNext()) {
			String key = (String) iterr.next();
			if (key.equals("rdapConformance")){
				continue;
			}
			Object o = map.get(key);
			if (o instanceof Map) {
				for (int i = 0; i < iMode; i++) {
					sb.append(WhoisUtil.BLANKSPACE);
				}
				sb.append(delTrim(key) + ":\n");
				sb.append(getPresentationFromMap(
						(Map<String, Object>) map.get(key), iMode + 1));

			} else if (o instanceof Object[] || o instanceof JSONArray) {
				for (int i = 0; i < iMode; i++) {
					sb.append(WhoisUtil.BLANKSPACE);
				}
				sb.append(delTrim(key) + ":\n");
				if (o instanceof JSONArray) {
					o = (JSONArray) o;
					o = ((JSONArray) o).toArray();
				}
				for (Object obj : (Object[]) o) {
					if (obj instanceof Map) {
						sb.append(getPresentationFromMap(
								(Map<String, Object>) obj, iMode + 1));
					} else {
						if (obj != null && !obj.toString().trim().equals("")) {
							for (int i = 0; i < iMode; i++) {
								sb.append(WhoisUtil.BLANKSPACE);
							}
							if (key.equals("vcardArray")) {
								if (!(obj instanceof ArrayList || obj instanceof JSONArray)) {
									sb.append(WhoisUtil.BLANKSPACE);
									sb.append(obj + "\n");
								} else {
									List<List<Object>> listVcard = new ArrayList<List<Object>>();
									if (obj instanceof JSONArray) {
										obj = convertJSONArrayToLists(obj);
									}
									listVcard = (ArrayList<List<Object>>) obj;
									for (int m = 0; m < listVcard.size(); m++) {
										List<Object> listElement;
										listElement = listVcard.get(m);
										if (listElement.get(0).equals("adr")) {
											List<List<Object>> listAdr = new ArrayList<List<Object>>();
											Object eleObj = listElement.get(3);
											if (eleObj instanceof JSONArray) {
												eleObj = convertJSONArrayToLists(eleObj);
											}
											listAdr = (ArrayList<List<Object>>) eleObj;
											sb.append(WhoisUtil.BLANKSPACE
													+ WhoisUtil.BLANKSPACE);
											sb.append("adr:" + listAdr.get(0));
											for (int n = 1; n < listAdr.size(); n++) {
												sb.append("," + listAdr.get(n));
											}
											sb.append("\n");
											continue;
										}
										sb.append(WhoisUtil.BLANKSPACE
												+ WhoisUtil.BLANKSPACE);
										sb.append(listElement.get(0) + ":"
												+ listElement.get(3) + "\n");
									}
								}
							} else {
								sb.append(obj + "\n");
							}

						}
					}
				}
			} else {
				for (int i = 0; i < iMode; i++) {
					sb.append(WhoisUtil.BLANKSPACE);
				}
				sb.append(delTrim(key) + ":");
				sb.append(map.get(key) + "\n");
			}
		}
		return sb.toString();
	}
	
	private List<Object> convertJsonArrayToList(JSONArray jsonArray){
		Object[] objectArray =jsonArray.toArray();
		List<Object> result = new ArrayList<Object>();
		for(Object obj:objectArray){
			result.add(obj);
		}
		return result;
	}

	private Object convertJSONArrayToLists(Object obj) {
		Object[] objectArray = ((JSONArray) obj)
				.toArray();
		List<Object> objList = new ArrayList<Object>();
		for (Object object : objectArray) {
			if(object instanceof JSONArray){
				object = convertJsonArrayToList((JSONArray)object);
				objList.add((List<Object>) object);
			}else{
				objList.add(object);
			}
		}
		obj = objList;
		return obj;
	}

	@Override
	public boolean support(FormatType formatType) {
		return null != formatType && formatType.isTextFormat();
	}

	public Map<String, Object> getMultiMapKey(QueryType queryType,
			Map<String, Object> map) {
		return map;
	}

	@Override
	public void displayError400(HttpServletRequest request,
			HttpServletResponse response, QueryParam queryParam)
			throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		Map<String, Object> map = new LinkedHashMap<String, Object>();

		try {
			map = WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE,queryParam);
		} catch (QueryException e) {
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		request.setAttribute("queryFormat", FormatType.TEXTPLAIN.getName());
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setStatus(429);
		response.setHeader("Content-Type", FormatType.TEXTPLAIN.getName());
		out.write(getPresentationFromMap(map, 0));
	}
}
