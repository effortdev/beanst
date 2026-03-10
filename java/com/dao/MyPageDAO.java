package com.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import com.util.JdbcUtil;
import com.vo.UserVO;

import jakarta.servlet.ServletContext;

public class MyPageDAO {

	private Properties props = new Properties();

	public MyPageDAO(ServletContext context) {
		try {
			InputStream input = context.getResourceAsStream("/WEB-INF/config/myPageMapper.xml");
			props.loadFromXML(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UserVO selectMyInfo(Connection con, String userId) {
		UserVO vo = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(props.getProperty("selectMyInfo"));
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new UserVO();
				vo.setUserId(rs.getString("user_id"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setPhone(rs.getString("phone"));
				vo.setStatus(rs.getString("status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return vo;
	}

	public boolean checkPassword(Connection con, String userId, String password) {
		boolean isMatch = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(props.getProperty("checkMyPassword"));
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next() && rs.getInt(1) > 0)
				isMatch = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return isMatch;
	}

	public boolean checkDuplicate(Connection con, String sqlKey, String value, String userId) {
		boolean isDup = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(props.getProperty(sqlKey));
			pstmt.setString(1, value);
			pstmt.setString(2, userId);
			rs = pstmt.executeQuery();
			if (rs.next() && rs.getInt(1) > 0)
				isDup = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return isDup;
	}

	public int updateMyInfo(Connection con, UserVO vo) {
		int result = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(props.getProperty("updateMyInfo"));
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getUserId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}

	public int updateSingleField(Connection con, String sqlKey, String value, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(props.getProperty(sqlKey));
			pstmt.setString(1, value);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}
}