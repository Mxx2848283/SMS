package com.sms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sms.bean.Message;
import com.sms.bean.Pagnation;
import com.sms.dao.MessageDao;
import com.sms.utils.DBUtils;

public class MessageDaoImpl implements MessageDao {

	@Override
	public Message findMessageById(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from message where id = ?";
		Message message = null;
		try {
			conn = DBUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				message = new Message();
				message.setId(rs.getInt("id"));
				message.setFrom_id(rs.getInt("from_id"));
				message.setTo_id(rs.getInt("to_id"));
				message.setSubject(rs.getString("subject"));
				message.setContent(rs.getString("content"));
				message.setCreate_time(rs.getDate("createtime"));
				message.setStatus(rs.getInt("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.quietClose(conn, pstmt, rs);
		}
		return message;
	}

	@Override
	public int deleteMsgById(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "delete from message where id = ?";
		try {
			conn = DBUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.quietClose(conn, pstmt, rs);
		}
		return -1;
	}

	@Override
	public int save(Message message) {
		String sql = "insert into message(from_id, to_id, subject, content, status, createtime) values (?,?,?,?,?,sysdate())";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, message.getFrom_id());
			pstmt.setInt(2, message.getTo_id());
			pstmt.setString(3, message.getSubject());
			pstmt.setString(4, message.getContent());
			pstmt.setInt(5, message.getStatus());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.quietClose(conn, pstmt, rs);
		}
		return -1;
	}

	@Override
	public int updateMessageStatus(Message message) {
		String sql = "update message set status = ? where id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, message.getStatus());
			pstmt.setInt(2, message.getId());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.quietClose(conn, pstmt, rs);
		}
		return -1;
	}

	@Override
	public Pagnation<Message> findMessageByReceiveId(Pagnation<Message> pagnation, int receiveUserId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String countSql = "select count(*) from message where to_id = ?";
		String sql = "select * from message where to_id = ? limit ?,?";
		try {
			conn = DBUtils.getConnection();
			
			// 查询总记录条数
			int totalCount = 0;
			pstmt = conn.prepareStatement(countSql);
			pstmt.setInt(1, receiveUserId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalCount = rs.getInt(1);
			}
			pagnation.setTotalCount(totalCount);
			DBUtils.quietClose(pstmt, rs);
			
			// 查询当前页数据
			List<Message> messageList = new ArrayList<>();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, receiveUserId);
			int offsetIdx = (pagnation.getCurrentPage() - 1) * pagnation.getPageSize();
			pstmt.setInt(2, offsetIdx);
			pstmt.setInt(3, pagnation.getPageSize());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Message message = new Message();
				message.setId(rs.getInt("id"));
				message.setFrom_id(rs.getInt("from_id"));
				message.setTo_id(rs.getInt("to_id"));
				message.setSubject(rs.getString("subject"));
				message.setContent(rs.getString("content"));
				message.setCreate_time(rs.getDate("createtime"));
				message.setStatus(rs.getInt("status"));
				messageList.add(message);
			}
			pagnation.setData(messageList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.quietClose(conn, pstmt, rs);
		}
		return pagnation;
	}
}
