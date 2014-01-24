package com.cnnic.whois.dao.query.cache;

import java.net.IDN;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.dao.query.db.AbstractDomainQueryDao;
@Repository("cacheRirDomainQueryDao")
public class RirDomainQueryDao extends AbstractCacheQueryDao {
	@Override
	protected List<String> getCacheKeySplits(QueryParam param) {
		List<String> keySplits = new ArrayList<String>();
		keySplits.add(QueryType.RIRDOMAIN.toString());
		keySplits.add("ldhName");
		keySplits.add(param.getQ());
		return keySplits;
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.RIRDOMAIN;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.RIRDOMAIN.equals(queryType);
	}

	@Override
	protected boolean needInitCache() {
		return true;
	}

	@Override
	protected void initCache() {
		super.initCacheWithOneKey(AbstractDomainQueryDao.QUERY_KEY, "Ldh_Name");
	}
	@Override
	protected String convertCacheKeyValue(String value) {
		try {
			value = IDN.toASCII(value);// long lable exception
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}