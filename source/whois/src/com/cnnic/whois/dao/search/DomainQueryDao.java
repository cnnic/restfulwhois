package com.cnnic.whois.dao.search;

import com.cnnic.whois.bean.DomainQueryParam;
import com.cnnic.whois.bean.PageBean;
import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.bean.index.DomainIndex;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.service.index.SearchResult;
public class DomainQueryDao extends AbstractSearchQueryDao<DomainIndex> {

	public DomainQueryDao(String url) {
		super(url);
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.SEARCHDOMAIN;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.SEARCHDOMAIN.equals(queryType);
	}

	@Override
	public SearchResult<DomainIndex> search(QueryParam param)
			throws QueryException {
		DomainQueryParam domainQueryParam = (DomainQueryParam) param;
		PageBean page = param.getPage();
		String queryStr = "ldhName:" + domainQueryParam.getDomainPuny()
				+ " OR unicodeName:" + domainQueryParam.getQ();
		SearchResult<DomainIndex> result = query(queryStr, page);
		page.setRecordsCount(Long.valueOf(result.getTotalResults()).intValue());
		return result;
	}
}
