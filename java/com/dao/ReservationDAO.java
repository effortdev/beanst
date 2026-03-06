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

	// =============================
	// DAO 생성자 - reservationMapper.xml 읽어서 SQL 가져오기
	// =============================
	public ReservationDAO(ServletContext context) {
		try {
			InputStream input = context.getResourceAsStream("/WEB-INF/config/reservationMapper.xml");
			props.loadFromXML(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// =============================
	// 1. 예약 등록
	// =============================
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

	// =============================
	// 2. 단일 예약 조회 (reservation_id 기준)
	// =============================
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

	// =============================
	// 3. 객실 예약 가능 여부 확인
	// =============================
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
				available = count == 0; // 0이면 예약 가능
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

	// =============================
	// 4. 예약된 날짜 목록 조회 (모든 객실)
	// =============================
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

	// =============================
	// 5. 유저 예약 전체 조회 (스와이프용)
	// =============================
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

	// =============================
	// 공통: ResultSet → VO 변환
	// =============================
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

	// 예약 취소 (여러 개)
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

	// 1. 달력용: 내 예약 제외하고 날짜 가져오기
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

	// 2. 중복 방지용: 수정 시 겹치는 예약이 있는지 확인
	public boolean isRoomAvailableForUpdate(int roomId, String checkIn, String checkOut, int resId) {
		boolean isAvailable = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(props.getProperty("isRoomAvailableForUpdate"));
			pstmt.setInt(1, roomId);
			pstmt.setInt(2, resId); // 내 예약번호 제외
			pstmt.setString(3, checkOut); // (check_in < checkOut)
			pstmt.setString(4, checkIn); // (check_out > checkIn)
			rs = pstmt.executeQuery();
			if (rs.next() && rs.getInt(1) == 0) {
				isAvailable = true; // 겹치는 예약이 0개면 예약 가능!
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

	// 3. 진짜 업데이트 실행
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