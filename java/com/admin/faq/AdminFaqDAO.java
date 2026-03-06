package com.admin.faq;

import static com.util.JdbcUtil.*;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import jakarta.servlet.ServletContext;

public class AdminFaqDAO {

	private Properties props = new Properties();

	public AdminFaqDAO(ServletContext context) {

		try {

			InputStream input = context.getResourceAsStream("/WEB-INF/config/adminMapper.xml");

			props.loadFromXML(input);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<AdminFaqDTO> selectFaqList(Connection conn) {

		List<AdminFaqDTO> list = new ArrayList<>();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String sql = props.getProperty("adminFaqList");

			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {

				AdminFaqDTO f = new AdminFaqDTO();

				f.setFaqNo(rs.getInt("faq_no"));
				f.setCategory(rs.getString("category"));
				f.setQuestion(rs.getString("question"));
				f.setAnswer(rs.getString("answer"));
				f.setSortOrder(rs.getInt("sort_order"));
				f.setStatus(rs.getString("status"));
				f.setRegDate(rs.getTimestamp("reg_date"));

				list.add(f);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}

		return list;
	}

	public int insertFaq(Connection conn, AdminFaqDTO faq) {

		PreparedStatement ps = null;
		int result = 0;

		try {

			String sql = props.getProperty("adminFaqInsert");

			ps = conn.prepareStatement(sql);

			ps.setString(1, faq.getCategory());
			ps.setString(2, faq.getQuestion());
			ps.setString(3, faq.getAnswer());
			ps.setInt(4, faq.getSortOrder());
			ps.setString(5, faq.getStatus());

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}

		return result;
	}

	public AdminFaqDTO selectFaq(Connection conn, int faqNo) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		AdminFaqDTO f = null;

		try {

			String sql = props.getProperty("adminFaqDetail");

			ps = conn.prepareStatement(sql);
			ps.setInt(1, faqNo);

			rs = ps.executeQuery();

			if (rs.next()) {

				f = new AdminFaqDTO();

				f.setFaqNo(rs.getInt("faq_no"));
				f.setCategory(rs.getString("category"));
				f.setQuestion(rs.getString("question"));
				f.setAnswer(rs.getString("answer"));
				f.setSortOrder(rs.getInt("sort_order"));
				f.setStatus(rs.getString("status"));
				f.setRegDate(rs.getTimestamp("reg_date"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}

		return f;
	}

	public int updateFaq(Connection conn, AdminFaqDTO faq) {

		PreparedStatement ps = null;
		int result = 0;

		try {

			String sql = props.getProperty("adminFaqUpdate");

			ps = conn.prepareStatement(sql);

			ps.setString(1, faq.getCategory());
			ps.setString(2, faq.getQuestion());
			ps.setString(3, faq.getAnswer());
			ps.setInt(4, faq.getSortOrder());
			ps.setString(5, faq.getStatus());
			ps.setInt(6, faq.getFaqNo());

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}

		return result;
	}

	public int deleteFaq(Connection conn, int faqNo) {

		PreparedStatement ps = null;
		int result = 0;

		try {

			String sql = props.getProperty("adminFaqDelete");

			ps = conn.prepareStatement(sql);

			ps.setInt(1, faqNo);

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}

		return result;
	}
}