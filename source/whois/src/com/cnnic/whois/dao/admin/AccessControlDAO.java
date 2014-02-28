package com.cnnic.whois.dao.admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.cnnic.whois.dao.base.BaseJdbcDao;
import com.cnnic.whois.execption.ManagementException;
import com.cnnic.whois.util.WhoisUtil;

@Repository
public class AccessControlDAO extends BaseJdbcDao {
	
	/**
	 * List permission information
	 * 
	 * @param tableName
	 * @return map
	 * @throws ManagementException
	 */
	public Map<String, Object> listPermissionCoulumn(String tableName)
			throws ManagementException {
		return this.getJdbcTemplate().query(WhoisUtil.SELECT_COLUMNINFOERMISSION, new Object[] { tableName },
				new ResultSetExtractor<Map<String, Object>>() {
					@Override
					public Map<String, Object> extractData(ResultSet rs) throws SQLException, DataAccessException {
						Map<String, Object> permissionList = new HashMap<String, Object>();
						while (rs.next()) {
							String columnName = rs.getString(WhoisUtil.COLUMNNAME);
							Map<String, String> userList = new HashMap<String, String>();
							userList.put(WhoisUtil.ANONYMOUS, rs.getString(WhoisUtil.ANONYMOUS));
							userList.put(WhoisUtil.AUTHENTICATED, rs.getString(WhoisUtil.AUTHENTICATED));
							userList.put(WhoisUtil.ROOT, rs.getString(WhoisUtil.ROOT));
							permissionList.put(columnName, userList);
						}
						return permissionList;
					}
				});
	}

	/**
	 * Update permission information
	 * 
	 * @param tableName
	 * @param permissionList
	 * @throws ManagementException
	 */
	public void updatePermission(final String tableName,
			final Map<String, List<String>> permissionList)
			throws ManagementException {
		final Set<String> columnkeySet = permissionList.keySet();
		
		this.getJdbcTemplate().update(WhoisUtil.UPDATE_PERMISSION,  
                new PreparedStatementSetter() {  
                    public void setValues(PreparedStatement stmt) throws SQLException {  
                    	for (String key : columnkeySet) {
            				List<String> permissionValueList = (List<String>) permissionList.get(key);
            				stmt.setString(1, permissionValueList.get(0));
            				stmt.setString(2, permissionValueList.get(1));
            				stmt.setString(3, permissionValueList.get(2));
            				stmt.setString(4, tableName);
            				stmt.setString(5, key);
            				stmt.execute();
            			}
                    }  
                });  

//		Connection connection = JdbcUtils.getConnection();
//		PreparedStatement stmt = null;
//		try {
//			stmt = connection.prepareStatement(WhoisUtil.UPDATE_PERMISSION);
//			for (String key : columnkeySet) {
//				List<String> permissionValueList = (List<String>) permissionList.get(key);
//				stmt.setString(1, permissionValueList.get(0));
//				stmt.setString(2, permissionValueList.get(1));
//				stmt.setString(3, permissionValueList.get(2));
//				stmt.setString(4, tableName);
//				stmt.setString(5, key);
//				stmt.execute();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new ManagementException(e);
//		} finally {
//			JdbcUtils.free(null, null, connection);
//		}
	}
}
