package com.dao;

import static com.util.JdbcUtil.*;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.dto.FaqDTO;

import jakarta.servlet.ServletContext;

public class FaqDAO {

	private Properties props = new Properties();

	public FaqDAO(ServletContext context) {
		try {
			InputStream input = context.getResourceAsStream("/WEB-INF/config/faqMapper.xml");
			props.loadFromXML(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<FaqDTO> selectFaqList(String category) {

		List<FaqDTO> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = props.getProperty("faqlist");

			if (category != null && !category.isEmpty()) {
				sql += " AND category = ? ";
			}

			sql += " ORDER BY sort_order, faq_no";

			ps = conn.prepareStatement(sql);

			if (category != null && !category.isEmpty()) {
				ps.setString(1, category);
			}

			rs = ps.executeQuery();

			while (rs.next()) {
				FaqDTO dto = new FaqDTO();
				dto.setFaqNo(rs.getInt("faq_no"));
				dto.setCategory(rs.getString("category"));
				dto.setQuestion(rs.getString("question"));
				dto.setAnswer(rs.getString("answer"));
				dto.setSortOrder(rs.getInt("sort_order"));
				dto.setStatus(rs.getString("status"));
				dto.setRegDate(rs.getTimestamp("reg_date"));

				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
			close(conn);
		}

		return list;
	}
}