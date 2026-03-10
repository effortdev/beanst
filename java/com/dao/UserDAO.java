package com.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Properties;

import com.util.JdbcUtil;
import com.vo.UserVO;

import jakarta.servlet.ServletContext;

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
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getName());
			pstmt.setString(5, vo.getPhone());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				JdbcUtil.commit(con);
				isSuccess = true;
			} else {
				JdbcUtil.rollback(con);
			}

		} catch (Exception e) {
			e.printStackTrace();
			JdbcUtil.rollback(con);
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}

		return isSuccess;
	}

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

			if (rs.next()) {
				userId = rs.getString("user_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}

		return userId;
	}

	public UserVO selectUser(String id) {
		UserVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(props.getProperty("selectUser"));
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new UserVO();
				vo.setUserId(rs.getString("user_id"));
				vo.setEmail(rs.getString("email"));
				vo.setName(rs.getString("name"));
				vo.setPhone(rs.getString("phone"));
				vo.setRole(rs.getString("role"));
				vo.setStatus(rs.getString("status"));

				String dateStr = rs.getString("created_at");
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date = formatter.parse(dateStr);
				java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
				vo.setCreatedAt(timestamp);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}

		return vo;
	}

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

	public boolean checkPassword(String userId, String password) {
		boolean isMatch = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = JdbcUtil.getConnection();
			String sql = props.getProperty("checkPassword");
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			if (rs.next() && rs.getInt("cnt") > 0)
				isMatch = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}

		return isMatch;
	}

	public boolean updatePassword(String userId, String newPw) {
		boolean isSuccess = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = JdbcUtil.getConnection();
			String sql = props.getProperty("updatePassword");
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newPw);
			pstmt.setString(2, userId);

			int result = pstmt.executeUpdate();

			if (result > 0) {
				JdbcUtil.commit(con);
				isSuccess = true;
			} else {
				JdbcUtil.rollback(con);
			}

		} catch (Exception e) {
			e.printStackTrace();
			JdbcUtil.rollback(con);
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}

		return isSuccess;
	}

	public boolean updateContact(String userId, String email, String phone) {
		boolean isSuccess = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = JdbcUtil.getConnection();
			String sql = props.getProperty("updateContact");
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, phone);
			pstmt.setString(3, userId);

			int result = pstmt.executeUpdate();

			if (result > 0) {
				JdbcUtil.commit(con);
				isSuccess = true;
			} else {
				JdbcUtil.rollback(con);
			}

		} catch (Exception e) {
			e.printStackTrace();
			JdbcUtil.rollback(con);
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}

		return isSuccess;
	}

	public boolean updateStatus(String userId, String status) {
		boolean isSuccess = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = JdbcUtil.getConnection();
			String sql = props.getProperty("updateStatus");

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, status); 
			pstmt.setString(2, userId);

			int result = pstmt.executeUpdate();

			if (result > 0) {
				JdbcUtil.commit(con);
				isSuccess = true;
			} else {
				JdbcUtil.rollback(con);
			}

		} catch (Exception e) {
			e.printStackTrace();
			JdbcUtil.rollback(con);
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}

		return isSuccess;
	}


	public boolean isEmailDuplicate(String email, String userId) {

		boolean isDuplicate = false;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = JdbcUtil.getConnection();
			String sql = props.getProperty("checkEmailDuplicate");

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, userId);

			rs = pstmt.executeQuery();

			if (rs.next() && rs.getInt(1) > 0) {
				isDuplicate = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}

		return isDuplicate;
	}
}