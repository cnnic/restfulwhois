package com.cnnic.whois.dao.search;

import org.springframework.stereotype.Repository;

import com.cnnic.whois.bean.EntityQueryParam;
import com.cnnic.whois.bean.PageBean;
import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.bean.index.EntityIndex;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.service.QueryService;
import com.cnnic.whois.service.index.SearchResult;

@Repository
public class EntityQueryDao extends AbstractSearchQueryDao<EntityIndex> {
	private static final String ARRAY_SPLITER = "'~'";
	private static final String FUZZY_MARK = "*";
	private static final String Q_OR = " OR ";

	public EntityQueryDao(String url) {
		super(url);
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.ENTITY;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.ENTITY.equals(queryType);
	}

	@Override
	public SearchResult<EntityIndex> query(QueryParam param)
			throws QueryException {
		EntityQueryParam entityQueryParam = (EntityQueryParam) param;
		SearchResult<EntityIndex> result = fuzzyQueryEntitiesByHandleAndName(
				entityQueryParam.getFuzzyQueryParamName(),
				entityQueryParam.getQ(), param.getPage());
		return result;
	}

	private SearchResult<EntityIndex> preciseQueryEntitiesByHandleOrName(
			String handleOrName) {
		handleOrName = handleOrName.replace(" ", "\\ ").replace(":", "\\:");
		String entityNamePrefix = "entityNames:";
		String entityNamesQ = entityNamePrefix + handleOrName;
		String entityNamesQP = entityNamePrefix + FUZZY_MARK + ARRAY_SPLITER
				+ handleOrName;
		String entityNamesQS = entityNamePrefix + handleOrName + ARRAY_SPLITER
				+ FUZZY_MARK;
		String entityNamesQPS = entityNamePrefix + FUZZY_MARK + ARRAY_SPLITER
				+ handleOrName + ARRAY_SPLITER + FUZZY_MARK;
		String entityNamesQFull = entityNamesQ + Q_OR + entityNamesQP + Q_OR
				+ entityNamesQS + Q_OR + entityNamesQPS;
		String queryStr = "(roles:registrar AND (" + entityNamesQFull + ")) "
				+ "OR (handle:" + handleOrName + ")";
		queryStr = queryStr.replace("~", "\\~");
		PageBean page = new PageBean();
		page.setRecordsCount(QueryService.MAX_SIZE_FUZZY_QUERY);
		return query(queryStr, page);
	}

	private SearchResult<EntityIndex> fuzzyQueryEntitiesByHandleAndName(
			String fuzzyQueryParamName, String handleOrName, PageBean page) {
		handleOrName = handleOrName.replace(" ", "\\ ").replace(":", "\\:");
		String kVSplit = ":";
		String entityNamesQ = fuzzyQueryParamName + kVSplit + handleOrName;
		String entityNamesQP = fuzzyQueryParamName + kVSplit + FUZZY_MARK
				+ ARRAY_SPLITER + handleOrName;
		String queryStr = entityNamesQ + Q_OR + entityNamesQP;
		queryStr = queryStr.replace("~", "\\~");
		SearchResult<EntityIndex> result = query(queryStr, page);
		page.setRecordsCount(Long.valueOf(result.getTotalResults()).intValue());
		return result;
	}
}