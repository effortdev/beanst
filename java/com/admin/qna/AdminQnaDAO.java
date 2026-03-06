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

	public AdminQnaDTO selectQnaDetail(Connection conn, int qnaNo) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		AdminQnaDTO qDto = null;

		try {

			String sql = props.getProperty("adminQnaDetail");

			ps = conn.prepareStatement(sql);
			ps.setInt(1, qnaNo);

			rs = ps.executeQuery();

			if (rs.next()) {

				qDto = new AdminQnaDTO();

				qDto.setQnaNo(rs.getInt("qna_no"));
				qDto.setUserId(rs.getString("user_id"));
				qDto.setTitle(rs.getString("title"));
				qDto.setContent(rs.getString("content"));
				qDto.setAnswer(rs.getString("answer"));
				qDto.setStatus(rs.getString("status"));
				qDto.setViewCount(rs.getInt("view_count"));
				qDto.setRegDate(rs.getTimestamp("reg_date"));
				qDto.setAnswerDate(rs.getTimestamp("answer_date"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}

		return qDto;
	}

	public int updateAnswer(Connection conn, String answer, int qnaNo) {

		PreparedStatement ps = null;
		int result = 0;

		try {

			String sql = props.getProperty("adminQnaAnswer");

			ps = conn.prepareStatement(sql);

			ps.setString(1, answer);
			ps.setInt(2, qnaNo);

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}

		return result;
	}

	public int deleteQna(Connection conn, int qnaNo) {

		PreparedStatement ps = null;
		int result = 0;

		try {

			String sql = props.getProperty("adminQnaDelete");

			ps = conn.prepareStatement(sql);

			ps.setInt(1, qnaNo);

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}

		return result;
	}

	public List<AdminQnaDTO> selectWaitingList(Connection conn) {

		List<AdminQnaDTO> list = new ArrayList<>();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String sql = props.getProperty("adminQnaWaitingList");

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