package com.cnnic.whois.dao.query.search;

import com.cnnic.whois.bean.EntityQueryParam;
import com.cnnic.whois.bean.PageBean;
import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.bean.index.EntityIndex;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.service.QueryService;
import com.cnnic.whois.service.index.SearchResult;
public class EntityQueryDao extends AbstractSearchQueryDao<EntityIndex> {
	private static final String ARRAY_SPLITER = "'~'";
	private static final String FUZZY_MARK = "*";
	private static final String Q_OR = " OR ";

	public EntityQueryDao(String url) {
		super(url);
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.SEARCHENTITY;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return getQueryType().equals(queryType);
	}

	@Override
	public SearchResult<EntityIndex> search(QueryParam param)
			throws QueryException {
		EntityQueryParam entityQueryParam = (EntityQueryParam) param;
		SearchResult<EntityIndex> result = fuzzyQueryEntitiesByHandleAndName(
				entityQueryParam.getFuzzyQueryParamName(),
				entityQueryParam.getQ(), param.getPage());
		return result;
	}

	public SearchResult<EntityIndex> preciseQueryEntitiesByHandleOrName(
			QueryParam param) {
		String handleOrName = param.getQ();
		handleOrName = super.escapeSolrChar(handleOrName);
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
		handleOrName = super.escapeSolrChar(handleOrName);
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
	
	public static String geneNsQByPreciseIpv4(String param){
		String ipV4 = "ipV4Address:*" + param + " OR ipV4Address:" + param + "* OR ipV4Address:*" + param + "*";
		return ipV4;
	}
	
	public static String geneNsQByPreciseIpv6(String param){
		param = param.replace(":","\\:");
		String ipV6 = "ipV6Address:*" + param + " OR ipV6Address:" + param + "* OR ipV6Address:*" + param + "*";
		return ipV6;
	}
}
