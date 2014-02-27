package com.cnnic.whois.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class JdbcUtils {

	private JdbcUtils() { }

	private static DataSource dataSource = null;
	static {
		try {
			// Properties props = new Properties();
			// props.load(JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties"));
			// dataSource = BasicDataSourceFactory.createDataSource(props);

			ApplicationContext context = new ClassPathXmlApplicationContext(
					new String[] { "spring/applicationContext.xml" });
			BeanFactory factory = (BeanFactory) context;
			dataSource = (DataSource) factory.getBean("dataSource");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("Datasource initial fail " + e);
		}
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Get datasource fail ", e);
		}
		return conn;
	}

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
}
