package com.admin.dashboard;

import static com.util.JdbcUtil.close;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import jakarta.servlet.ServletContext;

public class AdminDashboardDAO {

	private Properties props = new Properties();

	public AdminDashboardDAO(ServletContext context) {

		try {

			InputStream input = context.getResourceAsStream("/WEB-INF/config/adminMapper.xml");

			props.loadFromXML(input);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getCount(Connection conn, String key) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String sql = props.getProperty(key);

			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}

		return 0;
	}
}
