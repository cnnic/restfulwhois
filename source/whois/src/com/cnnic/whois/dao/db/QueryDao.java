package com.cnnic.whois.dao.db;

import java.util.Map;

import com.cnnic.whois.bean.PageBean;
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
	 * @param role
	 * @param format
	 * @param page
	 * @return
	 * @throws QueryException
	 * @throws RedirectExecption
	 */
	// TODO:remove format and role
	Map<String, Object> query(QueryParam param, PageBean... page)
			throws QueryException, RedirectExecption;

	Map<String, Object> getAll(QueryParam param) throws QueryException;
}
