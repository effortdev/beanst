package com.dao;

import static com.util.JdbcUtil.close;
import static com.util.JdbcUtil.getConnection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import com.vo.UserVO;

import jakarta.servlet.ServletContext;

public class LoginDAO {
	private Properties props = new Properties();

	public LoginDAO(ServletContext context) {
		try {
			InputStream input = context.getResourceAsStream("/WEB-INF/config/loginMapper.xml");
			props.loadFromXML(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UserVO login(String userId, String password) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = props.getProperty("Login");
			ps = conn.prepareStatement(sql);

			ps.setString(1, userId);
			ps.setString(2, password);

			rs = ps.executeQuery();

			if (rs.next()) {
				UserVO dto = new UserVO();
				dto.setUserId(rs.getString("user_id"));
				dto.setName(rs.getString("name"));
				return dto;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
			close(conn);
		}

		return null;
	}
}