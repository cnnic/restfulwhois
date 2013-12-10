package com.cnnic.whois.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DataFormat {
	/**
	 * Map collection converted to JSON object
	 * 
	 * @param responMap
	 * @return JSONArray
	 */
	public static JSONObject getJsonObject(Map<String, Object> responMap) {
		return JSONObject.fromObject(responMap);
	}
	public static JSONArray getJsonArray(Object responMap) {
		return JSONArray.fromObject(responMap);
	}
	public static JSONObject fromObject(String json) {
		return JSONObject.fromObject(json);
	}

	/**
	 * XML converted to JSON object
	 * 
	 * @param responMap
	 * @return XmlString
	 */
//	public static String getXmlString(Map<String, Object> responMap) {
//		return getXMLFromMap(responMap, 0);
//	}

//	private static Map<String, Object> getRdapMessage(Map<String, Object> responMap) {
//		Set<String> key = responMap.keySet();
//		Iterator<String> iter = key.iterator();
//		
//		//the first most top conformance object
//		Object[] conform = new Object[1];
//		conform[0] = "rdap_level_0";
//		
//		while (iter.hasNext()) {
//			String iterkey = iter.next();
//			if (!iterkey.startsWith("$mul$")) {
//				responMap.put("rdapConformance", conform);
//				break;
//			} else {
//				Object[] mayArray = (Object[]) responMap.get(iterkey);
//				List<Map> list = new ArrayList<Map>();
//				for (Object map : mayArray) {
//					Map m = (Map) map;
//					m.put("rdapConformance", conform);
//					list.add(m);
//				}
//				responMap = new LinkedHashMap<String, Object>();
//				responMap.put(iterkey, list.toArray());
//				break;
//			}
//		}
//		return responMap;
//	}

	/**
	 * Map to convert a collection of key: value string of the form
	 * 
	 * @param responMap
	 * @return String
	 */
//	public static String getPresentation(Map<String, Object> responMap) {
//		return getPresentationFromMap(responMap, 0);
//	}

	/**
	 * Get XmlString
	 * 
	 * @param map
	 * @param iMode
	 * @return xmlString
	 */
//	@SuppressWarnings("unchecked")
//	private static String getXMLFromMap(Map<String, Object> map, int iMode) {
//		StringBuffer sb = new StringBuffer();
//
//		if (map == null || map.size() == 0) {
//			return "";
//		}
//		if (0 == iMode) {
//			sb.append("<root>\n");
//		}
//
//		Iterator<String> iterr = map.keySet().iterator();
//
//		while (iterr.hasNext()) {
//			String key = (String) iterr.next();
//			if (key.equals("vcardArray")){
//				sb.append(getXMLFromVcard(map.get(key)));
//				continue;
//			}
//
//			if (map.get(key) instanceof Map) {
//				sb.append("<" + delTrim(key) + ">\n");
//				sb.append(getXMLFromMap((Map<String, Object>) map.get(key),
//						iMode + 1));
//				sb.append("</" + delTrim(key) + ">\n");
//			} else if (map.get(key) instanceof Object[]) {
//				sb.append("<" + delTrim(key) + ">\n");
//				for (Object obj : (Object[]) map.get(key)) {
//					int count = ((Object[]) map.get(key)).length;
//					
//					if (obj instanceof Map) {
//						sb.append(getXMLFromMap((Map<String, Object>) obj,
//								iMode + 2));
//					} else if (obj instanceof List) {
//						//sb.append(toVCardXml((List<String>) obj));
//					} else {
//						if(obj != null && !obj.toString().trim().equals("")){
//							sb.append(obj);
//						}
//						if(((Object[]) map.get(key))[count - 1] != obj) {
//							sb.append("</" + delTrim(key) + ">\n");
//							sb.append("<" + delTrim(key) + ">\n");
//						}
//					}
//				}
//				sb.append("</" + delTrim(key) + ">\n");
//			} else if (map.get(key) instanceof List) {
//				String[] values = ((List<String>) map.get(key)).get(0).split(",");
//				for (String value : values) {
//					sb.append("<" + delTrim(key) + ">\n");
//					sb.append(value);
//					sb.append("</" + delTrim(key) + ">\n");
//				}
//
//			} else {
//				sb.append("<" + delTrim(key) + ">\n");
//				sb.append(map.get(key));
//				sb.append("</" + delTrim(key) + ">\n");
//			}
//		}
//		if (0 == iMode)
//			sb.append("</root>\n");
//		return sb.toString();
//	}
	
	/**
	 * Get XmlString
	 * 
	 * @param map
	 * @param iMode
	 * @return xmlString
	 */
//	@SuppressWarnings("unchecked")
//	private static String getXMLFromVcard(Object VcardData) {
//		StringBuffer sb = new StringBuffer();
//		Object [] VcardValueArray = (Object [])VcardData;
//		List<Object> VcardValueList = (List<Object>)VcardValueArray[1];
//
//		sb.append("<" + delTrim("vcardArray") + ">\n");
//		sb.append("<" + "vcard" + ">\n");
//
//		for (int i = 0; i < VcardValueList.size(); i++){
//			sb.append(toVCardXml((List<String>)VcardValueList.get(i)));
//		}
//		sb.append("</" + "vcard" + ">\n");
//		sb.append("</" + delTrim("vcardArray") + ">\n");
//		return sb.toString();
//	}
//
//	/**
//	 * Remove the spaces in the data
//	 * 
//	 * @param data
//	 * @return String
//	 */
//	private static String delTrim(String data) {
//		if (data.startsWith("$mul$"))
//			return data.substring("$mul$".length());
//		return data.replaceAll(" ", "");
//	}

	/**
	 * Get PresentationFrom
	 * 
	 * @param map
	 * @param iMode
	 * @return PresentationString
	 */
//	@SuppressWarnings("unchecked")
//	private static String getPresentationFromMap(Map<String, Object> map, int iMode) {
//		StringBuffer sb = new StringBuffer();
//
//		if (map == null || map.size() == 0) {
//			return "";
//		}
//
//		Iterator<String> iterr = map.keySet().iterator();
//
//		while (iterr.hasNext()) {
//			String key = (String) iterr.next();
//
//			if (map.get(key) instanceof Map) {
//				for(int i = 0; i < iMode; i++){
//					sb.append(WhoisUtil.BLANKSPACE);
//				}
//				sb.append(delTrim(key) + ":\n");
//				sb.append(getPresentationFromMap(
//						(Map<String, Object>) map.get(key), iMode + 1));
//				
//			} else if (map.get(key) instanceof Object[]) {
//				for(int i = 0; i < iMode; i++){
//					sb.append(WhoisUtil.BLANKSPACE);
//				}
//				sb.append(delTrim(key) + ":\n");
//				for (Object obj : (Object[]) map.get(key)) {
//					if (obj instanceof Map) {
//						sb.append(getPresentationFromMap(
//								(Map<String, Object>) obj, iMode + 1));
//					}else{
//						if(obj != null && !obj.toString().trim().equals("")){
//							for(int i = 0; i < iMode; i++){
//								sb.append(WhoisUtil.BLANKSPACE);
//							}
//							if (key.equals("vcardArray")){
//								if (!(obj instanceof ArrayList)){
//									sb.append(WhoisUtil.BLANKSPACE);
//									sb.append(obj + "\n");
//								}else{									
//									List<List<Object>> listVcard = new ArrayList<List<Object>>();
//									listVcard = (ArrayList<List<Object>>)obj;
//									for (int m = 0; m < listVcard.size(); m++){
//										List<Object> listElement;
//										listElement = listVcard.get(m);
//										if (listElement.get(0).equals("adr")){
//											List<List<Object>> listAdr = new ArrayList<List<Object>>();
//											listAdr = (ArrayList<List<Object>>)listElement.get(3);
//											sb.append(WhoisUtil.BLANKSPACE + WhoisUtil.BLANKSPACE);
//											sb.append("adr:"+listAdr.get(0));
//											for (int n = 1; n < listAdr.size(); n++){
//												sb.append("," + listAdr.get(n));
//											}
//											sb.append("\n");
//											continue;
//										}
//										sb.append(WhoisUtil.BLANKSPACE + WhoisUtil.BLANKSPACE);
//										sb.append(listElement.get(0) + ":" + listElement.get(3) +"\n");
//									}
//								}								
//							} else {
//								sb.append(obj + "\n");
//							}
//							
//						}
//					}
//				}
//			} else {
//				for(int i = 0; i < iMode; i++){
//					sb.append(WhoisUtil.BLANKSPACE);
//				}
//				sb.append(delTrim(key) + ":");
//				sb.append(map.get(key) + "\n");
//			}
//		}
//		return sb.toString();
//	}

	/**
	 * Get VCardXmlString
	 * 
	 * @param vcard
	 * @return stringBuffer
	 */
//	private static StringBuffer toVCardXml(List<String> vcard) {
//		StringBuffer sb = new StringBuffer();		
//		for (int i = 0; i < vcard.size(); i++) {
//			String keyName = "";
//			if (vcard.get(i).equals("version")) {
//				keyName = "Version";
//			} else if (vcard.get(i).equals("fn")) {
//				keyName = "Fn";
//			} else if (vcard.get(i).equals("bday")) {
//				keyName = "Bday";
//			} else if (vcard.get(i).equals("anniversary")) {
//				keyName = "Anniversary";
//			} else if (vcard.get(i).equals("gender")) {
//				keyName = "Gender";
//			} else if (vcard.get(i).equals("kind")) {
//				keyName = "Kind";
//			} else if (vcard.get(i).equals("lang")) {
//				keyName = "Lang";
//				String keyNameFront = "";
//				String Attribute = vcard.get(1);
//				String [] AttributeList;
//				Attribute = Attribute.replace("{", "");
//				Attribute = Attribute.replace("}", "");
//				AttributeList = Attribute.split(":");
//				keyNameFront = keyName + " " + AttributeList[0].replace("\"", "") + "=" + AttributeList[1];
//				i = i + 3;
//				sb.append("<" + keyNameFront + ">\n");
//				sb.append("<" + vcard.get(2)+ ">\n");
//				sb.append(vcard.get(i));
//				sb.append("</" + vcard.get(2)+ ">\n");
//				sb.append("</" + keyName + ">\n");
//				continue;		
//			} else if (vcard.get(i).equals("org")) {
//				keyName = "Org";
//				String keyNameFront = "";
//				String Attribute = vcard.get(1);
//				String [] AttributeList;
//				Attribute = Attribute.replace("{", "");
//				Attribute = Attribute.replace("}", "");
//				AttributeList = Attribute.split(":");
//				keyNameFront = keyName + " " + AttributeList[0].replace("\"", "") + "=" + AttributeList[1];
//				i = i + 3;
//				sb.append("<" + keyNameFront + ">\n");
//				sb.append("<" + vcard.get(2)+ ">\n");
//				sb.append(vcard.get(i));
//				sb.append("</" + vcard.get(2)+ ">\n");
//				sb.append("</" + keyName + ">\n");
//				continue;
//			} else if (vcard.get(i).equals("title")) {
//				keyName = "Title";
//			} else if (vcard.get(i).equals("role")) {
//				keyName = "Role";
//			} else if (vcard.get(i).equals("adr")) {
//				keyName = "Adr";
//				String keyNameFront = "";
//				String Attribute = vcard.get(1);
//				String [] AttributeList;
//				Attribute = Attribute.replace("{", "");
//				Attribute = Attribute.replace("}", "");
//				AttributeList = Attribute.split(":");
//				keyNameFront = keyName + " " + AttributeList[0].replace("\"", "") + "=" + AttributeList[1];
//				i = i + 3;
//				
//				Object Value = vcard.get(3);
//				List<String> ValueList = (List<String>)Value; 
//				for (int k = 0; k < ValueList.size(); k++){
//					sb.append("<" + keyNameFront + ">\n");					
//					sb.append("<" + vcard.get(2)+ ">\n");
//					sb.append(ValueList.get(k));
//					sb.append("</" + vcard.get(2)+ ">\n");
//					sb.append("</" + keyName + ">\n");
//				}
//				continue;				
//			} else if (vcard.get(i).equals("geo")) {
//				keyName = "Geo";
//				String keyNameFront = "";
//				String Attribute = vcard.get(1);
//				String [] AttributeList;
//				Attribute = Attribute.replace("{", "");
//				Attribute = Attribute.replace("}", "");
//				AttributeList = Attribute.split(":");
//				keyNameFront = keyName + " " + AttributeList[0].replace("\"", "") + "=" + AttributeList[1];
//				i = i + 3;
//				sb.append("<" + keyNameFront + ">\n");
//				sb.append("<" + vcard.get(2)+ ">\n");
//				sb.append(vcard.get(i));
//				sb.append("</" + vcard.get(2)+ ">\n");
//				sb.append("</" + keyName + ">\n");
//				continue;
//			}  else if (vcard.get(i).equals("key")) {
//				keyName = "Key";
//				String keyNameFront = "";
//				String Attribute = vcard.get(1);
//				String [] AttributeList;
//				Attribute = Attribute.replace("{", "");
//				Attribute = Attribute.replace("}", "");
//				AttributeList = Attribute.split(":");
//				keyNameFront = keyName + " " + AttributeList[0].replace("\"", "") + "=" + AttributeList[1];
//				i = i + 3;
//				sb.append("<" + keyNameFront + ">\n");
//				sb.append("<" + vcard.get(2)+ ">\n");
//				sb.append(vcard.get(i));
//				sb.append("</" + vcard.get(2)+ ">\n");
//				sb.append("</" + keyName + ">\n");
//				continue;
//			} else if (vcard.get(i).equals("tz")) {
//				keyName = "Tz";
//			} else if (vcard.get(i).equals("url")) {
//				keyName = "Url";
//			} else if (vcard.get(i).equals("email")) {
//				keyName = "Email";
//			} else if (vcard.get(i).equals("tel")) {
//				if (vcard.get(i + 1).indexOf("work") != -1) {
//					keyName = "Office";
//				} else if (vcard.get(i + 1).indexOf("fax") != -1) {
//					keyName = "Fax";
//				} else if (vcard.get(i + 1).indexOf("cell") != -1) {
//					keyName = "Moblie";
//				} 
//				else {
//					keyName = "phonesId";
//				}
//			}
//			i = i + 3;
//			sb.append("<" + keyName + ">\n");
//			sb.append("<" + vcard.get(2)+ ">\n");
//			sb.append(vcard.get(i));
//			sb.append("</" + vcard.get(2)+ ">\n");
//			sb.append("</" + keyName + ">\n");
//		}
//		return sb;
//	}
}
