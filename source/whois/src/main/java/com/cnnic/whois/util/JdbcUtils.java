package com.cnnic.whois.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/***
 * 
 * JDBC utils
 *
 */
@Component
public class JdbcUtils implements ApplicationContextAware {

	private ApplicationContext applicationContext;
	
	private JdbcUtils() { }

	/**
	 * get a DB connection
	 * @return Connection
	 */
	public Connection getConnection() {
		Connection conn = null;
		BasicDataSource dataSource = null;
		try {
			dataSource = (BasicDataSource) applicationContext.getBean("dataSource");  
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Get datasource fail ", e);
		}
		return conn;
	}

	/**
	 * free a DB connection
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	public static void free(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * setApplicationContext
	 * @param applicationContext
	 */
	@Autowired
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * getApplicationContext
	 * @return ApplicationContext
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
}
