package com.sms.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

	private final static String JDBC_URL = "jdbc:mysql://localhost:3306/sms?characterEncoding=utf8";
	private final static String JDBC_USER = "root";
	private final static String JDBC_PASSWORD = "123456";
	private final static String JDBC_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	
	static {
		//1.加载驱动
		try {
			Class.forName(JDBC_DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			System.err.println("无法加载驱动类: " + e.getMessage());
		}
	}
	
	public static Connection getConnection(String url, String user, String password) throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	}
	
	public static void quietClose(Connection conn, Statement stmt, ResultSet rs) {
		quietClose(rs);
		quietClose(conn, stmt);
	}
	
	public static void quietClose(Connection conn, Statement stmt) {
		quietClose(stmt);
		quietClose(conn);
	}
	
	public static void quietClose(Statement stmt, ResultSet rs) {
		quietClose(rs);
		quietClose(stmt);
	}
	
	public static void quietClose(Connection conn) {
		if(conn == null) return;
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void quietClose(Statement stmt) {
		if(stmt == null) return;
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void quietClose(ResultSet rs) {
		if(rs == null) return;
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			System.out.println(conn);
		} catch (SQLException e) {
			System.err.println("获取数据库连接失败: " + e);
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
