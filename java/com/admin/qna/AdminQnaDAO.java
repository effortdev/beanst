package com.admin.qna;

import static com.util.JdbcUtil.*;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import jakarta.servlet.ServletContext;

public class AdminQnaDAO {

	private Properties props = new Properties();

	public AdminQnaDAO(ServletContext context) {

		try {

			InputStream input = context.getResourceAsStream("/WEB-INF/config/adminMapper.xml");

			props.loadFromXML(input);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 관리자 QNA 목록
	public List<AdminQnaDTO> selectQnaList(Connection conn) {

		List<AdminQnaDTO> list = new ArrayList<>();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String sql = props.getProperty("adminQnaList");

			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {

				AdminQnaDTO q = new AdminQnaDTO();

				q.setQnaNo(rs.getInt("qna_no"));
				q.setUserId(rs.getString("user_id"));
				q.setTitle(rs.getString("title"));
				q.setStatus(rs.getString("status"));
				q.setViewCount(rs.getInt("view_count"));
				q.setRegDate(rs.getTimestamp("reg_date"));

				list.add(q);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}

		return list;
	}
}