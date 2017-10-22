package com.jackzhang.mall.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 访问数据库帮助类
 * @author jackzhang
 *
 */
public class DBManager {
	private final static String DRIVE = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://127.0.0.1:3306/mall";
	private final static String USER = "root";
	private final static String PASSWORD = "root";
	private Connection conn = null;
	private PreparedStatement ps = null;

	public Connection getConnection() {
		try {
			Class.forName(DRIVE);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public PreparedStatement getPreparedStatement(Connection conn, String sql) {
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
