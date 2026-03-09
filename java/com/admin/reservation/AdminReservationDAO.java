package com.admin.reservation;

import static com.util.JdbcUtil.close;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import jakarta.servlet.ServletContext;

public class AdminReservationDAO {

	private Properties props = new Properties();

	public AdminReservationDAO(ServletContext context) {

		try {

			InputStream input = context.getResourceAsStream("/WEB-INF/config/adminMapper.xml");

			props.loadFromXML(input);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<AdminReservationDTO> selectReservationList(Connection conn, int startRow, int listLimit) {

		List<AdminReservationDTO> list = new ArrayList<>();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String sql = props.getProperty("selectReservationList");

			ps = conn.prepareStatement(sql);
			ps.setInt(1, startRow);
			ps.setInt(2, listLimit);
			rs = ps.executeQuery();

			while (rs.next()) {

				AdminReservationDTO r = new AdminReservationDTO();

				r.setReservationId(rs.getInt("reservation_id"));
				r.setUserId(rs.getString("user_id"));
				r.setRoomId(rs.getInt("room_id"));
				r.setRoomName(rs.getString("room_name"));
				r.setName(rs.getString("name"));
				r.setCheckIn(rs.getDate("check_in"));
				r.setCheckOut(rs.getDate("check_out"));
				r.setAdultCount(rs.getInt("adult_count"));
				r.setChildCount(rs.getInt("child_count"));
				r.setTotalPrice(rs.getInt("total_price"));
				r.setStatus(rs.getString("status"));
				r.setCreatedAt(rs.getTimestamp("created_at"));

				list.add(r);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}

		return list;
	}

	public int confirmReservation(Connection conn, int reservationId) {

		PreparedStatement ps = null;
		int result = 0;

		try {

			String sql = props.getProperty("adminReservationConfirm");

			ps = conn.prepareStatement(sql);

			ps.setInt(1, reservationId);

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}

		return result;
	}

	public int cancelReservation(Connection conn, int reservationId) {

		PreparedStatement ps = null;
		int result = 0;

		try {

			String sql = props.getProperty("adminReservationCancel");

			ps = conn.prepareStatement(sql);

			ps.setInt(1, reservationId);

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}

		return result;
	}

	public int selectReservationCount(Connection conn) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		int count = 0;

		try {

			String sql = props.getProperty("selectUserCount");

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}

		return count;
	}

	public List<AdminReservationDTO> selectActiveReservationList(Connection conn) {

		List<AdminReservationDTO> list = new ArrayList<>();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String sql = props.getProperty("adminReservationActiveList");

			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {

				AdminReservationDTO r = new AdminReservationDTO();

				r.setReservationId(rs.getInt("reservation_id"));
				r.setName(rs.getString("name"));
				r.setRoomName(rs.getString("room_name"));
				r.setCheckIn(rs.getDate("check_in"));
				r.setCheckOut(rs.getDate("check_out"));
				r.setAdultCount(rs.getInt("adult_count"));
				r.setChildCount(rs.getInt("child_count"));
				r.setTotalPrice(rs.getInt("total_price"));
				r.setStatus(rs.getString("status"));

				list.add(r);
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