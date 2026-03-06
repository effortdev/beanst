package com.admin.qna;

import static com.util.JdbcUtil.close;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.dto.QnaDTO;

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

	// QNA 목록
	public List<QnaDTO> getQnaList(Connection conn) {

		List<QnaDTO> list = new ArrayList<>();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String sql = props.getProperty("adminQnaList");

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {

				QnaDTO dto = new QnaDTO();

				dto.setQnaNo(rs.getInt("qna_no"));
				dto.setUserId(rs.getString("user_id"));
				dto.setTitle(rs.getString("title"));
				dto.setStatus(rs.getString("status"));
				dto.setRegDate(rs.getTimestamp("reg_date"));

				list.add(dto);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			close(rs);
			close(ps);

		}

		return list;
	}

	public QnaDTO selectQnaDetail(Connection conn, int qnaNo) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		QnaDTO dto = null;

		try {

			String sql = props.getProperty("adminQnaDetail");

			ps = conn.prepareStatement(sql);
			ps.setInt(1, qnaNo);

			rs = ps.executeQuery();

			if (rs.next()) {

				dto = new QnaDTO();

				dto.setQnaNo(rs.getInt("qna_no"));
				dto.setUserId(rs.getString("user_id"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setAnswer(rs.getString("answer"));
				dto.setStatus(rs.getString("status"));
				dto.setRegDate(rs.getTimestamp("reg_date"));
				dto.setAnswerDate(rs.getTimestamp("answer_date"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}

		return dto;
	}
}