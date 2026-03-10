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

	// 회원 가입
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

	// 유저 한 사람 가져오기
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
				java.util.Date date = formatter.parse(dateStr); // Date로 변환
				java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
				vo.setCreatedAt(timestamp);
//				vo.setCreatedAt(rs.getTimestamp("created_at"));

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

	// 사용자 존재 여부 확인 (비밀번호 찾기용)
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

	// 비밀번호 확인
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

	// 비밀번호 업데이트
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

	// 연락처 수정
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

	// 회원 상태 변경 (탈퇴 포함)
	public boolean updateStatus(String userId, String status) {
		boolean isSuccess = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = JdbcUtil.getConnection();
			String sql = props.getProperty("updateStatus");

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, status); // String status
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

//  이메일 중복검사 -- 0310 추가 

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