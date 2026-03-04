package com.dao;

import static com.util.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dto.QnaDTO;
import com.util.PageInfo;

public class QnaDAO {

	public int getQnaCount(String keyword) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT COUNT(*) ");
			sql.append("FROM qna ");

			if (keyword != null && !keyword.isEmpty()) {
				sql.append("WHERE title LIKE ? OR content LIKE ? ");
			}

			ps = conn.prepareStatement(sql.toString());

			if (keyword != null && !keyword.isEmpty()) {
				String kw = "%" + keyword + "%";
				ps.setString(1, kw);
				ps.setString(2, kw);
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

	public List<QnaDTO> selectQnaList(PageInfo pi, String keyword) {

		List<QnaDTO> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT q.*, m.member_id, m.member_name ");
			sql.append("FROM qna q ");
			sql.append("JOIN member m ON q.member_no = m.member_no ");

			if (keyword != null && !keyword.isEmpty()) {
				sql.append("WHERE q.title LIKE ? OR q.content LIKE ? ");
			}

			sql.append("ORDER BY q.qna_no DESC ");
			sql.append("LIMIT ?, ?");

			ps = conn.prepareStatement(sql.toString());

			int idx = 1;

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
				q.setMemberNo(rs.getInt("member_no"));
				q.setTitle(rs.getString("title"));
				q.setContent(rs.getString("content"));
				q.setIsSecret(rs.getString("is_secret"));
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

		try {
			conn = getConnection();

			// 조회수 증가
			try {
				ps = conn.prepareStatement("UPDATE qna SET view_count = view_count + 1 WHERE qna_no = ?");
				ps.setInt(1, no);
				ps.executeUpdate();
				close(ps);
				ps = null;
			} catch (Exception e) {
				e.printStackTrace();
			}

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT q.*, m.member_id, m.member_name ");
			sql.append("FROM qna q ");
			sql.append("JOIN member m ON q.member_no = m.member_no ");
			sql.append("WHERE q.qna_no = ?");

			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, no);

			rs = ps.executeQuery();

			if (rs.next()) {
				QnaDTO q = new QnaDTO();
				q.setQnaNo(rs.getInt("qna_no"));
				q.setMemberNo(rs.getInt("member_no"));
				q.setTitle(rs.getString("title"));
				q.setContent(rs.getString("content"));
				q.setIsSecret(rs.getString("is_secret"));
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
				return q;
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