package com.cnnic.whois.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.cnnic.whois.dao.base.BaseJdbcDao;
import com.cnnic.whois.execption.ManagementException;
import com.cnnic.whois.util.IpUtil;
import com.cnnic.whois.util.JdbcUtils;
import com.cnnic.whois.util.WhoisUtil;

@Repository
public class RedirectionDAO extends BaseJdbcDao {

	@Autowired
	private JdbcUtils jdbcUtils;
	/**
	 * Add domainRedirection information
	 * 
	 * @param domainName
	 * @param redirectUrl
	 * @throws ManagementException
	 */
	public void addDomainRedirection(final String domainName, final String redirectUrl)
			throws ManagementException {
		
		Connection connection = jdbcUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet resultsIsNull = null;
		try {
			String sql = WhoisUtil.ISNULL_DOMAIN_REDIRECT;
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, domainName);
			stmt.setString(2, redirectUrl);
			resultsIsNull = stmt.executeQuery();

//			TODO : Whatever return result is true or false, still execute next code
			if (resultsIsNull.next())
				throw new ManagementException("Already exists in the data ");

			sql = WhoisUtil.INSERT_DOMAIN_REDIRECTION + "'" + domainName + "'"
					+ "," + "'" + redirectUrl + "')";
			
			stmt = connection.prepareStatement(sql);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ManagementException(e);
		} finally {
			JdbcUtils.free(null, null, connection);
		}
	}

	/**
	 * Add ipRedirection information
	 * 
	 * @param startHighAddr
	 * @param endHighAddr
	 * @param startLowAddr
	 * @param endLowAddr
	 * @param redirectUrl
	 * @throws ManagementException
	 */
	public void addIPRedirection(long startHighAddr, long endHighAddr,
			long startLowAddr, long endLowAddr, String redirectUrl)
			throws ManagementException {
		Connection connection = jdbcUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet resultsIsNull = null;
		try {
			String sql = WhoisUtil.ISNULL_IP_REDIRECT;
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, startHighAddr);
			stmt.setLong(2, endHighAddr);
			stmt.setLong(3, startLowAddr);
			stmt.setLong(4, endLowAddr);
			stmt.setString(5, redirectUrl);

			resultsIsNull = stmt.executeQuery();
			if (resultsIsNull.next())
				throw new ManagementException("Already exists in the data ");

			sql = WhoisUtil.INSERT_IP_REDIRECTION + startHighAddr + ","
					+ endHighAddr + "," + startLowAddr + "," + endLowAddr
					+ ",'" + redirectUrl + "')";

			stmt = connection.prepareStatement(sql);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ManagementException(e);
		} finally {
			JdbcUtils.free(null, null, connection);
		}
	}

	/**
	 * Add autunmRedirection information
	 * 
	 * @param startNumber
	 * @param endNumber
	 * @param redirectUrl
	 * @throws ManagementException
	 */
	public void addAutnumRedirection(String startNumber, String endNumber,
			String redirectUrl) throws ManagementException {
		Connection connection = jdbcUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet resultsIsNull = null;
		try {
			String sql = WhoisUtil.ISNULL_AUTNUM_REDIRECT;
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(startNumber));
			stmt.setInt(2, Integer.parseInt(endNumber));
			stmt.setString(3, redirectUrl);
			resultsIsNull = stmt.executeQuery();

			if (resultsIsNull.next()) {
				throw new ManagementException("Already exists in the data ");
			}

			sql = WhoisUtil.INSERT_AUTNUM_REDIRECTION
					+ Integer.parseInt(startNumber) + ","
					+ Integer.parseInt(endNumber) + "," + "'" + redirectUrl
					+ "')";
			
			stmt = connection.prepareStatement(sql);
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ManagementException(e);
		} finally {
			JdbcUtils.free(null, null, connection);
		}
	}

	/**
	 * List redirection information
	 * 
	 * @param tableName
	 * @return map collection
	 * @throws ManagementException
	 */
	public Map<Integer, List<String>> listRedirect(final String tableName)
			throws ManagementException {
		String sql = WhoisUtil.SELECT_REDIRECT + tableName + "_redirect";
		return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Map<Integer, List<String>>>() {
			@Override
			public Map<Integer, List<String>> extractData(ResultSet results) throws SQLException, DataAccessException {
				Map<Integer, List<String>> redirectInfoList = new HashMap<Integer, List<String>>();
				while (results.next()) {
					List<String> list = new ArrayList<String>();
					if (tableName.equals(WhoisUtil.AUTNUM)) {
						list.add(results.getString("startNumber"));
						list.add(results.getString("endNumber"));

					} else if (tableName.equals(WhoisUtil.IP)) {
						//The long type data into ip
						long startHightAddress = results
								.getLong("StartHighAddress");
						long startLowAddress = results.getLong("StartLowAddress");
						long endHighAddress = results.getLong("EndHighAddress");
						long endLowAddress = results.getLong("EndLowAddress");

						String startNumber = "";
						String endNumber = "";

						if (startHightAddress != 0 && endHighAddress != 0) {
							startNumber = IpUtil.ipV6ToString(startHightAddress,
									startLowAddress);
							endNumber = IpUtil.ipV6ToString(endHighAddress,
									endLowAddress);
						} else {
							startNumber = IpUtil.longtoipV4(startLowAddress);
							endNumber = IpUtil.longtoipV4(endLowAddress);
						}

						list.add(startNumber);
						list.add(endNumber);

					} else {
						list.add(results.getString("redirectType"));
					}
					list.add(results.getString("redirectURL"));
					redirectInfoList.put(results.getInt("id"), list);
				}
				return redirectInfoList;
			}
		});
	}

	/**
	 * Update domainRedirection information
	 * 
	 * @param domainName
	 * @param id
	 * @param redirectUrl
	 * @throws ManagementException
	 */
	public void updateDomainRedirection(String domainName, int id,
			String redirectUrl) throws ManagementException {
		Connection connection = jdbcUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet resultsIsNull = null;
		try {
			String sql = WhoisUtil.ISNULL_DOMAIN_REDIRECT;
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, domainName);
			stmt.setString(2, redirectUrl);
			resultsIsNull = stmt.executeQuery();

			if (resultsIsNull.next())
				throw new ManagementException("Already exists in the data ");

			sql = WhoisUtil.UPDATE_DOMAIN_REDIRECTION  + "'" + domainName + "'"
					+ WhoisUtil.UPDATE_REDIRECTION1 + "'" + redirectUrl + "'"
					+ WhoisUtil.UPDATE_REDIRECTION2 + id;

			stmt = connection.prepareStatement(sql);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ManagementException(e);
		} finally {
			JdbcUtils.free(null, null, connection);
		}
	}

	/**
	 * Update autnumRedirection information
	 * 
	 * @param startNumber
	 * @param endNumber
	 * @param id
	 * @param redirectUrl
	 * @throws ManagementException
	 */
	public void updateAutnumRedirection(String startNumber, String endNumber,
			int id, String redirectUrl) throws ManagementException {
		Connection connection = jdbcUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet resultsIsNull = null;
		try {
			String sql = WhoisUtil.ISNULL_AUTNUM_REDIRECT;
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(startNumber));
			stmt.setInt(2, Integer.parseInt(endNumber));
			stmt.setString(3, redirectUrl);

			resultsIsNull = stmt.executeQuery();
			if (resultsIsNull.next())
				throw new ManagementException("Already exists in the data ");

			sql = WhoisUtil.UPDATE_AUTNUM_REDIRECTION1
					+ Integer.parseInt(startNumber)
					+ WhoisUtil.UPDATE_AUTNUM_REDIRECTION2
					+ Integer.parseInt(endNumber)
					+ WhoisUtil.UPDATE_REDIRECTION1 + "'" + redirectUrl + "'"
					+ WhoisUtil.UPDATE_REDIRECTION2 + id;

			stmt = connection.prepareStatement(sql);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ManagementException(e);
		} finally {
			JdbcUtils.free(null, null, connection);
		}
	}

	/**
	 * Update ipRedirection information
	 * 
	 * @param startHighAddr
	 * @param endHighAddr
	 * @param startLowAddr
	 * @param endLowAddr
	 * @param id
	 * @param redirectUrl
	 * @throws ManagementException
	 */
	public void updateIPRedirection(long startHighAddr, long endHighAddr,
			long startLowAddr, long endLowAddr, int id, String redirectUrl)
			throws ManagementException {
		Connection connection = jdbcUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet resultsIsNull = null;
		try {
			String sql = WhoisUtil.ISNULL_IP_REDIRECT;
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, startHighAddr);
			stmt.setLong(2, endHighAddr);
			stmt.setLong(3, startLowAddr);
			stmt.setLong(4, endLowAddr);
			stmt.setString(5, redirectUrl);

			resultsIsNull = stmt.executeQuery();
			if (resultsIsNull.next())
				throw new ManagementException("Already exists in the data ");

			sql = WhoisUtil.UPDATE_IP_REDIRECTION1 + startHighAddr
					+ WhoisUtil.UPDATE_IP_REDIRECTION2 + endHighAddr
					+ WhoisUtil.UPDATE_IP_REDIRECTION3 + startLowAddr
					+ WhoisUtil.UPDATE_IP_REDIRECTION4 + endLowAddr
					+ WhoisUtil.UPDATE_REDIRECTION1 + "'" + redirectUrl + "'"
					+ WhoisUtil.UPDATE_REDIRECTION2 + id;

			stmt = connection.prepareStatement(sql);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ManagementException(e);
		} finally {
			JdbcUtils.free(null, null, connection);
		}
	}

	/**
	 * Delete redirection information
	 * 
	 * @param id
	 * @param tableName
	 * @throws ManagementException
	 */
	public void deleteRedirect(int id, String tableName)
			throws ManagementException {
		String sql = WhoisUtil.DELETE_REDIRECT1 + tableName + "_redirect"
				+ WhoisUtil.DELETE_REDIRECT2;
		Object[] param = new Object[]{id };
		this.getJdbcTemplate().update(sql, param);
	}

}
