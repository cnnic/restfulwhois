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

import org.springframework.stereotype.Component;

import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.util.WhoisUtil;

@Component("textResponseWriter")
public class TextResponseWriter extends AbstractResponseWriter {
	private static TextResponseWriter writer = new TextResponseWriter();

	public static ResponseWriter getWriter() {
		return writer;
	}

	@Override
	public String formatKey(String keyName) {
		return keyName;
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
			
			//set response status
			if(map.containsKey("errorCode") || map.containsKey("Error Code")){
				if(map.containsKey("errorCode"))
					errorCode = map.get("errorCode").toString();
				if (map.containsKey("Error Code"))
					errorCode = map.get("Error Code").toString();
				if (errorCode.equals(WhoisUtil.ERRORCODE)){
					response.setStatus(404);
				}
				if (errorCode.equals(WhoisUtil.COMMENDRRORCODE)){
					response.setStatus(400);
				}
			}
			
			//multi-responses
			Iterator<String> iterr = map.keySet().iterator();
			String multiKey = null;
			while(iterr.hasNext()){
				String key =  iterr.next();
				if(key.startsWith(WhoisUtil.MULTIPRX)){
					multiKey = key;
				}
			}
			if(multiKey != null){
				Object jsonObj = map.get(multiKey);
				map.remove(multiKey);
				map.put(multiKey.substring(WhoisUtil.MULTIPRX.length()), jsonObj);
			}
			
			response.setHeader("Content-Type", FormatType.TEXTPLAIN.getName());
			out.write(getPresentationFromMap(map, 0));
	}
	
	public void displayOverTimeMessage(HttpServletRequest request, HttpServletResponse response, 
			String role) throws IOException, ServletException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		try {
			map = WhoisUtil.processError(WhoisUtil.RATELIMITECODE);
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
	
	@Override
	public boolean support(FormatType formatType) {
		return null != formatType && formatType.isTextFormat();
	}
	
	@SuppressWarnings("unchecked")
	protected String getPresentationFromMap(Map<String, Object> map, int iMode) {
		StringBuffer sb = new StringBuffer();

		if (map == null || map.size() == 0) {
			return "";
		}

		Iterator<String> iterr = map.keySet().iterator();

		while (iterr.hasNext()) {
			String key = (String) iterr.next();
			for(int i = 0; i < iMode; i++){
				sb.append(WhoisUtil.BLANKSPACE);
			}
			
			if (map.get(key) instanceof Map) {
				sb.append(delTrim(key) + ":\n");
				sb.append(getPresentationFromMap(
						(Map<String, Object>) map.get(key), iMode + 1));	
			}else if (map.get(key) instanceof Object[]) {
				sb.append(delTrim(key) + ":\n");
				for (Object obj : (Object[]) map.get(key)) {
					if (obj instanceof Map) {
						sb.append(getPresentationFromMap(
								(Map<String, Object>) obj, iMode + 1));
					}else{
						if(obj != null && !obj.toString().trim().equals("")){
							for(int i = 0; i < iMode; i++){
								sb.append(WhoisUtil.BLANKSPACE);
							}
							if (key.equals("vcardArray")){
								sb.append(getPresentationFromVcard(obj));								
							}else {
								sb.append(obj + "\n");
							}	
						}
					}
				}
			}else {
				sb.append(delTrim(key) + ":");
				sb.append(map.get(key) + "\n");
			}
		}
		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	protected String getPresentationFromVcard(Object VcardData) {
		StringBuffer sb = new StringBuffer();
		
		if (!(VcardData instanceof ArrayList)){
			sb.append(WhoisUtil.BLANKSPACE);
			sb.append(VcardData + "\n");
		}else{									
			List<List<Object>> listVcard = new ArrayList<List<Object>>();
			listVcard = (ArrayList<List<Object>>)VcardData;
			for (int m = 0; m < listVcard.size(); m++){
				List<Object> listElement;
				listElement = listVcard.get(m);
				if (listElement.get(0).equals("adr")){
					List<List<Object>> listAdr = new ArrayList<List<Object>>();
					listAdr = (ArrayList<List<Object>>)listElement.get(3);
					sb.append(WhoisUtil.BLANKSPACE + WhoisUtil.BLANKSPACE);
					sb.append("adr:"+listAdr.get(0));
					for (int n = 1; n < listAdr.size(); n++){
						sb.append("," + listAdr.get(n));
					}
					sb.append("\n");
					continue;
				}
				sb.append(WhoisUtil.BLANKSPACE + WhoisUtil.BLANKSPACE);
				sb.append(listElement.get(0) + ":" + listElement.get(3) +"\n");
			}
		}
		return sb.toString();
	}
}
