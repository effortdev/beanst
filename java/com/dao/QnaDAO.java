package com.dao;

import static com.util.JdbcUtil.*;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.dto.QnaDTO;
import com.util.PageInfo;

import jakarta.servlet.ServletContext;

public class QnaDAO {

	private Properties props = new Properties();

	public QnaDAO(ServletContext context) {
		try {
			System.out.println("QnaDAO 생성자 실행");
			InputStream input = context.getResourceAsStream("/WEB-INF/config/qnaMapper.xml");
			props.loadFromXML(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getQnaCount(String keyword, String userId) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			StringBuilder sql = new StringBuilder(props.getProperty("qnaCountBase"));

			// 로그인한 사용자 글만 카운트
			sql.append(" AND q.user_id = ? ");

			if (keyword != null && !keyword.isEmpty()) {
				sql.append(" AND (q.title LIKE ? OR q.content LIKE ?) ");
			}

			ps = conn.prepareStatement(sql.toString());

			int idx = 1;
			ps.setString(idx++, userId);

			if (keyword != null && !keyword.isEmpty()) {
				String kw = "%" + keyword + "%";
				ps.setString(idx++, kw);
				ps.setString(idx, kw);
			}

			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
			close(conn);
		}

		return 0;
	}

	public List<QnaDTO> selectQnaList(PageInfo pi, String keyword, String userId) {

		List<QnaDTO> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			StringBuilder sql = new StringBuilder(props.getProperty("qnaListBase"));

			// 로그인한 사용자 글만 조회
			sql.append(" AND q.user_id = ? ");

			if (keyword != null && !keyword.isEmpty()) {
				sql.append(" AND (q.title LIKE ? OR q.content LIKE ?) ");
			}

			sql.append(" ORDER BY q.qna_no DESC ");
			sql.append(" LIMIT ?, ?");

			ps = conn.prepareStatement(sql.toString());

			int idx = 1;

			ps.setString(idx++, userId);

			if (keyword != null && !keyword.isEmpty()) {
				String kw = "%" + keyword + "%";
				ps.setString(idx++, kw);
				ps.setString(idx++, kw);
			}

			ps.setInt(idx++, pi.getStartRow());
			ps.setInt(idx, pi.getEndRow());

			rs = ps.executeQuery();

			while (rs.next()) {
				QnaDTO q = new QnaDTO();
				q.setQnaNo(rs.getInt("qna_no"));
				q.setTitle(rs.getString("title"));
				q.setContent(rs.getString("content"));
				q.setAnswer(rs.getString("answer"));
				q.setAnswerDate(rs.getTimestamp("answer_date"));
				q.setStatus(rs.getString("status"));
				q.setViewCount(rs.getInt("view_count"));
				q.setRegDate(rs.getTimestamp("reg_date"));
				try {
					q.setMemberId(rs.getString("member_id"));
					q.setMemberName(rs.getString("member_name"));
				} catch (Exception ignore) {
				}

				list.add(q);
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

	public QnaDTO selectQna(int no) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean viewUpdated = false;

		try {
			conn = getConnection();

			// 조회수 증가 쿼리도 XML에서 관리
			try {
				String updateSql = props.getProperty("qnaUpdateViewCount");
				ps = conn.prepareStatement(updateSql);
				ps.setInt(1, no);
				int updated = ps.executeUpdate();
				if (updated > 0) {
					viewUpdated = true;
				}
				close(ps);
				ps = null;
			} catch (Exception e) {
				e.printStackTrace();
			}

			String selectSql = props.getProperty("qnaSelect");

			ps = conn.prepareStatement(selectSql);
			ps.setInt(1, no);

			rs = ps.executeQuery();

			if (rs.next()) {
				QnaDTO q = new QnaDTO();
				q.setQnaNo(rs.getInt("qna_no"));
				q.setTitle(rs.getString("title"));
				q.setContent(rs.getString("content"));
				q.setAnswer(rs.getString("answer"));
				q.setAnswerDate(rs.getTimestamp("answer_date"));
				q.setStatus(rs.getString("status"));
				q.setViewCount(rs.getInt("view_count"));
				q.setRegDate(rs.getTimestamp("reg_date"));
				try {
					q.setMemberId(rs.getString("member_id"));
					q.setMemberName(rs.getString("member_name"));
				} catch (Exception ignore) {
				}

				if (viewUpdated) {
					commit(conn);
				} else {
					rollback(conn);
				}
				return q;
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (conn != null) {
				rollback(conn);
			}
		} finally {
			close(rs);
			close(ps);
			close(conn);
		}

		return null;
	}

	public int insertQna(String userId, String title, String content) {

		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;

		try {
			conn = getConnection();

			String insertSql = props.getProperty("qnaInsert");

			ps = conn.prepareStatement(insertSql);
			ps.setString(1, userId);
			ps.setString(2, title);
			ps.setString(3, content);

			result = ps.executeUpdate();

			if (result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			if (conn != null) {
				rollback(conn);
			}
		} finally {
			close(ps);
			close(conn);
		}

		return 0;
	}
}
