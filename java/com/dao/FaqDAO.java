package com.dao;

import static com.util.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dto.FaqDTO;

public class FaqDAO {

	public List<FaqDTO> selectFaqList(String category) {

		List<FaqDTO> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT faq_no, category, question, answer, sort_order, status, reg_date ");
			sql.append("FROM faq ");
			sql.append("WHERE status = 'ACTIVE' ");

			if (category != null && !category.isEmpty()) {
				sql.append("AND category = ? ");
			}

			sql.append("ORDER BY sort_order, faq_no");

			ps = conn.prepareStatement(sql.toString());

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