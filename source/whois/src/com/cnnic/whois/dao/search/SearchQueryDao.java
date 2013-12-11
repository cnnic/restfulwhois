package com.cnnic.whois.dao.search;

import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.bean.index.Index;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.service.index.SearchResult;

public interface SearchQueryDao{
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

	SearchResult<? extends Index> search(QueryParam param)
			throws QueryException;
}