package com.dao;

import static com.util.JdbcUtil.*;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import jakarta.servlet.ServletContext;
import com.dto.AdminDTO;

public class AdminDAO {

	private Properties props = new Properties();

	public AdminDAO(ServletContext context) {
		try {
			System.out.println("AdminDAO 생성자 실행");
			InputStream input = context.getResourceAsStream("/WEB-INF/config/adminMapper.xml");
			props.loadFromXML(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AdminDTO login(String userId, String password) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = props.getProperty("adminLogin");
			ps = conn.prepareStatement(sql);
			
			System.out.println("sql = " + sql);

			ps.setString(1, userId);
			ps.setString(2, password);

			rs = ps.executeQuery();

			if (rs.next()) {
				AdminDTO dto = new AdminDTO();
				dto.setUserId(rs.getString("user_id"));
				dto.setRole(rs.getString("role"));
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