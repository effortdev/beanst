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
			System.out.println("FaqDAO 생성자 실행");
			// AdminDAO와 동일한 방식으로 Mapper XML을 로드
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

			// 기본 SELECT 문은 faqMapper.xml에 정의
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