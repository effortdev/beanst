package com.admin.dashboard;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.admin.qna.AdminQnaDTO;
import com.admin.reservation.AdminReservationDTO;

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

	// ===============================
	// 통계 COUNT
	// ===============================

	public int getCount(Connection conn, String key) {

		int count = 0;

		try {

			String sql = props.getProperty(key);

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}

			rs.close();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;

	}

	// ===============================
	// 최근 예약
	// ===============================

	public List<AdminReservationDTO> getRecentReservations(Connection conn) {

		List<AdminReservationDTO> list = new ArrayList<>();

		try {

			String sql = props.getProperty("adminRecentReservations");

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				AdminReservationDTO r = new AdminReservationDTO();

				r.setReservationId(rs.getInt("reservation_id"));
				r.setUserId(rs.getString("user_id"));
				r.setRoomName(rs.getString("room_name"));
				r.setCheckIn(rs.getDate("check_in"));
				r.setStatus(rs.getString("status"));

				list.add(r);

			}

			rs.close();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	// ===============================
	// 최근 문의
	// ===============================

	public List<AdminQnaDTO> getRecentQna(Connection conn) {

		List<AdminQnaDTO> list = new ArrayList<>();

		try {

			String sql = props.getProperty("adminRecentQna");

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				AdminQnaDTO q = new AdminQnaDTO();

				q.setQnaNo(rs.getInt("qna_no"));
				q.setUserId(rs.getString("user_id"));
				q.setTitle(rs.getString("title"));
				q.setStatus(rs.getString("status"));

				list.add(q);

			}

			rs.close();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	// ===============================
	// 차트 Labels
	// ===============================

	public List<String> getReservationChartLabels(Connection conn) {

		List<String> labels = new ArrayList<>();

		try {

			String sql = props.getProperty("adminReservationChart");

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				labels.add("'" + rs.getString("day") + "'");

			}

			rs.close();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return labels;

	}

	// ===============================
	// 차트 Data
	// ===============================

	public List<Integer> getReservationChartData(Connection conn) {

		List<Integer> data = new ArrayList<>();

		try {

			String sql = props.getProperty("adminReservationChart");

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				data.add(rs.getInt("count"));

			}

			rs.close();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;

	}

}