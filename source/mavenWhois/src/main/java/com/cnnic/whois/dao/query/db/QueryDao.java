package com.cnnic.whois.dao.query.db;

import java.util.Map;

import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.execption.RedirectExecption;

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
	 * 
	 * @param param
	 * @return
	 * @throws QueryException
	 * @throws RedirectExecption
	 */
	Map<String, Object> query(QueryParam param)
			throws QueryException, RedirectExecption;

	Map<String, Object> getAll() throws QueryException;
}
