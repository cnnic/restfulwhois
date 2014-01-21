package com.cnnic.whois.dao.query.search;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.bean.index.Index;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.service.index.SearchResult;
@Repository
public class SearchQueryExecutor {
	private static SearchQueryExecutor executor = new SearchQueryExecutor();

	public static SearchQueryExecutor getExecutor() {
		return executor;
	}
	@Resource(name = "searchQueryDaos")
	private List<SearchQueryDao> searchQueryDaos ;

	public SearchResult<? extends Index> query(QueryType queryType,
			QueryParam param) throws QueryException {
		for (SearchQueryDao queryDao : searchQueryDaos) {
			if (queryDao.supportType(queryType)) {
				return queryDao.search(param);
			}
		}
		return null;
	}
}