package com.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import jakarta.servlet.ServletContext;
import com.util.JdbcUtil;
import com.vo.UserVO;

public class UserDAO {
	private Properties props = new Properties();

	public UserDAO(ServletContext context) {
		try {
			InputStream input = context.getResourceAsStream("/WEB-INF/config/memberMapper.xml");
			props.loadFromXML(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean insertUser(UserVO vo) {
		boolean isSuccess = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = JdbcUtil.getConnection();
			String sql = props.getProperty("insertUser");

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getUserId());
			pstmt.setString(2, vo.getPassword()); // 실무에서는 여기서 비밀번호 암호화(Hash)를 거칩니다
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getName());
			pstmt.setString(5, vo.getPhone());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				isSuccess = true;
				JdbcUtil.commit(con);
			} else {
				JdbcUtil.rollback(con);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (con != null)
				JdbcUtil.rollback(con);
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		return isSuccess;
	}

	// 아이디 찾기
	public String findId(String name, String email) {
		String userId = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(props.getProperty("findId"));
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			if (rs.next())
				userId = rs.getString("user_id");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		return userId;
	}

	// 사용자 존재 여부 확인
	public boolean checkUserForPw(String userId, String name, String email) {
		boolean isExist = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(props.getProperty("checkUserForPw"));
			pstmt.setString(1, userId);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			rs = pstmt.executeQuery();
			if (rs.next() && rs.getInt("cnt") > 0)
				isExist = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		return isExist;
	}

	// 비밀번호 업데이트
	public boolean updatePassword(String userId, String newPw) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(props.getProperty("updatePassword"));
			pstmt.setString(1, newPw);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
			if (result > 0)
				JdbcUtil.commit(con);
		} catch (Exception e) {
			e.printStackTrace();
			JdbcUtil.rollback(con);
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		return result > 0;
	}
}