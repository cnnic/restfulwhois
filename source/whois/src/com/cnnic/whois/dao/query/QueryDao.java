package com.cnnic.whois.dao.query;

import java.util.Map;

import com.cnnic.whois.bean.PageBean;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.execption.QueryException;

public interface QueryDao {
	/**
	 * get query join type
	 * 
	 * @return
	 */
	QueryType getQueryType();

	/**
	 * suppot query type
	 * 
	 * @param queryType
	 * @return
	 */
	boolean supportType(QueryType queryType);

	/**
	 * query
	 * 
	 * @param q
	 * @param role
	 * @param format
	 * @return
	 * @throws QueryException
	 */
	// TODO:remove format and role
	Map<String, Object> query(String q, String role, String format,
			PageBean... page) throws QueryException;
}
