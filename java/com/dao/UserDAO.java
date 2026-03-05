package com.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
}