package com.cnnic.whois.oauth.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
 
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONHelper {

	/**
	     * 将JSONObjec对象转换成Map-List集合
	     * @see JSONHelper#reflect(JSONArray)
	     * @param json
	     * @return
	     */
	    public static Map<String, Object> reflect(JSONObject json){
	    	Map<String, Object> map = new HashMap<String, Object>();
	        Set<?> keys = json.keySet();
	        for(Object key : keys){
	            Object o = json.get(key);
	            if(o instanceof JSONArray)
	                map.put((String) key, reflect((JSONArray) o));
	            else if(o instanceof JSONObject)
	                map.put((String) key, reflect((JSONObject) o));
	            else
	                map.put((String) key, o);
	        }
	        return map;
	    }
	 
	    /**
	     * 将JSONArray对象转换成Map-List集合
	     * @see JSONHelper#reflect(JSONObject)
	     * @param json
	     * @return
	     */
	    public static Object reflect(JSONArray json){
	        List<Object> list = new ArrayList<Object>();
	        for(Object o : json){
	            if(o instanceof JSONArray)
	                list.add(reflect((JSONArray) o));
	            else if(o instanceof JSONObject)
	                list.add(reflect((JSONObject) o));
	            else
	                list.add(o);
	        }
	        return list;
	    }
	
}
