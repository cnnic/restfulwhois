package com.cnnic.whois.dao.query.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.cnnic.whois.bean.IpQueryParam;
import com.cnnic.whois.bean.QueryJoinType;
import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.execption.RedirectExecption;
import com.cnnic.whois.util.WhoisUtil;
@Repository
public class IpRedirectionQueryDao extends AbstractDbQueryDao {

//	TODO : not sure this part is correct
	@Override
	public Map<String, Object> query(final QueryParam param) throws QueryException, RedirectExecption {
		IpQueryParam ipParam = (IpQueryParam) param;
		long startHighAddr = ipParam.getStartHighAddr();
		long startLowAddr = ipParam.getStartLowAddr();
//		Connection connection = null;
//		PreparedStatement stmt = null;
//		ResultSet results = null;

		String selectSql = "";
		if (startHighAddr == 0) {
			selectSql = (WhoisUtil.SELECT_URL_IPV4_REDIRECTION1 + startLowAddr
					+ WhoisUtil.SELECT_URL_IPV4_REDIRECTION2 + startLowAddr + WhoisUtil.SELECT_URL_IPV4_REDIRECTION3);
		} else {

			selectSql = WhoisUtil.SELECT_URL_IPV6_REDIRECTION1 + startHighAddr
					+ WhoisUtil.SELECT_URL_IPV6_REDIRECTION2 + startHighAddr
					+ WhoisUtil.SELECT_URL_IPV6_REDIRECTION3 + startLowAddr
					+ WhoisUtil.SELECT_URL_IPV6_REDIRECTION4 + startHighAddr
					+ WhoisUtil.SELECT_URL_IPV6_REDIRECTION5 + startHighAddr
					+ WhoisUtil.SELECT_URL_IPV6_REDIRECTION6 + startLowAddr
					+ WhoisUtil.SELECT_URL_IPV6_REDIRECTION7;
		}
		
		this.getJdbcTemplate().query(selectSql, new RowCallbackHandler() {
		    @Override
		    public void processRow(ResultSet results) throws SQLException {
		try {
//			connection = ds.getConnection();
//			stmt = connection.prepareStatement(selectSql);
//			results = stmt.executeQuery();
			if (results.next()) {
				String redirectUrl = results.getString("redirectURL");
				if (!(redirectUrl.endsWith("/"))) {
					redirectUrl += "/";
				}
				try {
					throw new RedirectExecption(redirectUrl+param.getQ());
				} catch (RedirectExecption e) {
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				throw new QueryException(e);
			} catch (QueryException e1) {
				e1.printStackTrace();
			}
		} 
//		finally {
//			if (connection != null) {
//				try {
//					connection.close();
//				} catch (SQLException se) {
//				}
			}
		});
		return null;
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.IPREDIRECTION;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.IPREDIRECTION.equals(queryType);
	}

	@Override
	protected boolean supportJoinType(QueryType queryType,
			QueryJoinType queryJoinType) {
		return false;
	}

	@Override
	public Object querySpecificJoinTable(String key, String handle) throws SQLException {
		throw new UnsupportedOperationException();
	}
}
