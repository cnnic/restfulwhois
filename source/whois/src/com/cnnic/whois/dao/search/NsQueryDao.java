package com.cnnic.whois.dao.search;

import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.bean.index.NameServerIndex;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.service.index.SearchResult;
public class NsQueryDao extends AbstractSearchQueryDao<NameServerIndex> {

	public NsQueryDao(String url) {
		super(url);
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.SEARCHNS;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return getQueryType().equals(queryType);
	}

	@Override
	public SearchResult<NameServerIndex> search(QueryParam param)
			throws QueryException {
		SearchResult<NameServerIndex> result = query(param.getQ(),
				param.getPage());
		return result;
	}
}
