package com.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.util.JdbcUtil;
import com.vo.ReservationVO;

import jakarta.servlet.ServletContext;

public class ReservationDAO {

	private Properties props = new Properties();

	public ReservationDAO(ServletContext context) {
		try {
			InputStream input = context.getResourceAsStream("/WEB-INF/config/reservationMapper.xml");
			props.loadFromXML(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean insertReservation(ReservationVO vo) {
		boolean isSuccess = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = JdbcUtil.getConnection();
			String sql = props.getProperty("insertReservation");

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getUserId());
			pstmt.setInt(2, vo.getRoomId());
			pstmt.setString(3, vo.getRoomName());
			pstmt.setString(4, vo.getName());
			pstmt.setString(5, vo.getCheckIn());
			pstmt.setString(6, vo.getCheckOut());
			pstmt.setInt(7, vo.getAdultCount());
			pstmt.setInt(8, vo.getChildCount());
			pstmt.setInt(9, vo.getTotalPrice());

			int result = pstmt.executeUpdate();
			if (result > 0) {
				isSuccess = true;
				JdbcUtil.commit(con);
			} else {
				JdbcUtil.rollback(con);
			}

		} catch (Exception e) {
			e.printStackTrace();
			JdbcUtil.rollback(con);
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}

		return isSuccess;
	}

	public ReservationVO getReservation(int reservationId) {
		ReservationVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = JdbcUtil.getConnection();
			String sql = props.getProperty("getReservation");

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reservationId);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = mapResultSetToVO(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}

		return vo;
	}

	public boolean isRoomAvailable(int roomId, String checkIn, String checkOut) {
		boolean available = true;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = JdbcUtil.getConnection();
			String sql = props.getProperty("isRoomAvailable");

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, roomId);
			pstmt.setString(2, checkOut);
			pstmt.setString(3, checkIn);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				available = count == 0; 
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}

		return available;
	}


	public List<ReservationVO> getReservedDates() {
		List<ReservationVO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = JdbcUtil.getConnection();
			String sql = props.getProperty("getReservedDates");

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReservationVO vo = new ReservationVO();
				vo.setRoomId(rs.getInt("room_id"));
				vo.setCheckIn(rs.getString("check_in"));
				vo.setCheckOut(rs.getString("check_out"));
				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}

		return list;
	}

	public List<ReservationVO> getReservationsByUserId(String userId) {
		List<ReservationVO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = JdbcUtil.getConnection();
			String sql = props.getProperty("getReservationsByUserId");

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(mapResultSetToVO(rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}

		return list;
	}

	private ReservationVO mapResultSetToVO(ResultSet rs) throws Exception {
		ReservationVO vo = new ReservationVO();
		vo.setReservationId(rs.getInt("reservation_id"));
		vo.setUserId(rs.getString("user_id"));
		vo.setRoomId(rs.getInt("room_id"));
		vo.setRoomName(rs.getString("room_name"));
		vo.setName(rs.getString("name"));
		vo.setCheckIn(rs.getString("check_in"));
		vo.setCheckOut(rs.getString("check_out"));
		vo.setAdultCount(rs.getInt("adult_count"));
		vo.setChildCount(rs.getInt("child_count"));
		vo.setTotalPrice(rs.getInt("total_price"));
		vo.setStatus(rs.getString("status"));
		return vo;
	}


	public boolean cancelReservations(List<Integer> reservationIds) {

		Connection con = null;
		PreparedStatement pstmt = null;

		boolean success = false;

		try {

			con = JdbcUtil.getConnection();

			String sql = props.getProperty("cancelReservation");

			pstmt = con.prepareStatement(sql);

			for (int id : reservationIds) {

				pstmt.setInt(1, id);
				pstmt.addBatch();

			}

			int[] result = pstmt.executeBatch();

			if (result.length > 0) {

				JdbcUtil.commit(con);
				success = true;

			} else {

				JdbcUtil.rollback(con);

			}

		} catch (Exception e) {

			e.printStackTrace();

			JdbcUtil.rollback(con);

		} finally {

			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);

		}

		return success;
	}

	public List<ReservationVO> getReservedDatesExcludingSelf(int resId) {
		List<ReservationVO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(props.getProperty("getReservedDatesExcludingSelf"));
			pstmt.setInt(1, resId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReservationVO vo = new ReservationVO();
				vo.setRoomId(rs.getInt("room_id"));
				vo.setCheckIn(rs.getString("check_in"));
				vo.setCheckOut(rs.getString("check_out"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		return list;
	}

	public boolean isRoomAvailableForUpdate(int roomId, String checkIn, String checkOut, int resId) {
		boolean isAvailable = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(props.getProperty("isRoomAvailableForUpdate"));
			pstmt.setInt(1, roomId);
			pstmt.setInt(2, resId); 
			pstmt.setString(3, checkOut); 
			pstmt.setString(4, checkIn); 
			rs = pstmt.executeQuery();
			if (rs.next() && rs.getInt(1) == 0) {
				isAvailable = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		return isAvailable;
	}

	public boolean updateReservation(ReservationVO vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(props.getProperty("updateReservation"));
			pstmt.setInt(1, vo.getRoomId());
			pstmt.setString(2, vo.getRoomName());
			pstmt.setString(3, vo.getCheckIn());
			pstmt.setString(4, vo.getCheckOut());
			pstmt.setInt(5, vo.getAdultCount());
			pstmt.setInt(6, vo.getChildCount());
			pstmt.setInt(7, vo.getTotalPrice());
			pstmt.setInt(8, vo.getReservationId());

			result = pstmt.executeUpdate();
			if (result > 0)
				JdbcUtil.commit(con);
		} catch (Exception e) {
			e.printStackTrace();
			JdbcUtil.rollback(con);
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		return result > 0;
	}

}