package com.sms.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sms.bean.User;
import com.sms.dao.UserDao;
import com.sms.utils.DBUtils;

public class UserDaoImpl implements UserDao{

	@Override
	public int saveUser(User user){
		//连接数据库
		Connection conn =  null;
		int num = 0;
		PreparedStatement stmt =  null;
		try{
			//1.加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2.建立连接
			String url = "jdbc:mysql://localhost:3306/sms";
			conn = DriverManager.getConnection(url, "root", "root");
			//3.创建预编译语句
			String sql = "insert into user(username,nickname,password," +
					"email,create_time) values (?,?,?,?,sysdate())";
			stmt = conn.prepareStatement(sql);
			//给参数占位符赋值
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getNickname());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getEmail());
			//4.执行sql
			num = stmt.executeUpdate();
			//5.处理结果集
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//6.关闭连接
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return num;
	}

	@Override
	public User findUserByUsername(String username) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from user where username = ?";
		User user = null;
		try {
			conn = DBUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setNickname(rs.getString("nickname"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setLast_login_time(rs.getDate("last_login_time"));
				user.setCreate_time(rs.getDate("create_time"));
				user.setEmail(rs.getString("email"));
				user.setStatus(rs.getInt("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.quietClose(conn, pstmt, rs);
		}
		return user;
	}

	@Override
	public List<User> findAllUser() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from user";
		List<User> userList = new ArrayList<>();
		try {
			conn = DBUtils.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setNickname(rs.getString("nickname"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setLast_login_time(rs.getDate("last_login_time"));
				user.setCreate_time(rs.getDate("create_time"));
				user.setEmail(rs.getString("email"));
				user.setStatus(rs.getInt("status"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.quietClose(conn, stmt, rs);
		}
		return userList;
	}

	@Override
	public List<User> findAllUserExcludeByUsername(String username) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from user where username != ?";
		List<User> userList = new ArrayList<>();
		try {
			conn = DBUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setNickname(rs.getString("nickname"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setLast_login_time(rs.getDate("last_login_time"));
				user.setCreate_time(rs.getDate("create_time"));
				user.setEmail(rs.getString("email"));
				user.setStatus(rs.getInt("status"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.quietClose(conn, pstmt, rs);
		}
		return userList;
	}

	@Override
	public User findUserById(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from user where id = ?";
		User user = null;
		try {
			conn = DBUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setNickname(rs.getString("nickname"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setLast_login_time(rs.getDate("last_login_time"));
				user.setCreate_time(rs.getDate("create_time"));
				user.setEmail(rs.getString("email"));
				user.setStatus(rs.getInt("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.quietClose(conn, pstmt, rs);
		}
		return user;
	}

}
