package com.dao;

import static com.util.JdbcUtil.close;
import static com.util.JdbcUtil.getConnection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.room.RoomMainDTO;
import com.util.JdbcUtil;
import com.vo.RoomImageVO;
import com.vo.RoomManageVO;
import com.vo.RoomVO;

import jakarta.servlet.ServletContext;

public class RoomDAO {

	private Properties props = new Properties();

	public RoomDAO(ServletContext context) {
		try {
			System.out.println("RoomDAO 생성자 실행");
			InputStream input = context.getResourceAsStream("/WEB-INF/config/mainMapper.xml");
			props.loadFromXML(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<RoomVO> getAllRooms() {
		List<RoomVO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = JdbcUtil.getConnection();

//			String sql = "SELECT * FROM room ORDER BY room_id ASC";
			String sql = props.getProperty("getAllRooms");
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				RoomVO vo = new RoomVO();
				vo.setRoomId(rs.getInt("room_id"));
				vo.setRoomName(rs.getString("room_name"));
				vo.setBaseCapacity(rs.getInt("base_capacity"));
				vo.setMaxCapacity(rs.getInt("max_capacity"));
				vo.setBasePrice(rs.getInt("base_price"));
				vo.setExtraCharge(rs.getInt("extra_charge"));

				vo.setImagePath(rs.getString("image_path"));

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

	// 객실 목록보기 수정중
	public List<RoomMainDTO> selectRoomList() {

		List<RoomMainDTO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = props.getProperty("selectRoomList");

			conn = getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {

				RoomMainDTO dto = new RoomMainDTO();

				dto.setRoom_id(rs.getInt("room_id"));
				dto.setRoom_name(rs.getString("room_name"));
				dto.setCapacity(rs.getString("capacity"));
				dto.setRoom_location(rs.getString("room_location"));
				dto.setRoom_description(rs.getString("room_description"));
				dto.setUsage_time(rs.getString("usage_time"));
				dto.setAmenity(rs.getString("amenity"));
				dto.setMinibar(rs.getString("minibar"));
				dto.setImage_path(rs.getString("image_path"));

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

	// 객실 목록 보기
	public List<RoomMainDTO> selectMainRoom() {

		List<RoomMainDTO> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String sql = props.getProperty("selectMainRoom");

			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {

				RoomMainDTO dto = new RoomMainDTO();

				dto.setRoom_id(rs.getInt("room_id"));
				dto.setRoom_name(rs.getString("room_name"));
				dto.setCapacity(rs.getString("capacity"));
				dto.setRoom_location(rs.getString("room_location"));
				dto.setRoom_description(rs.getString("room_description"));
				dto.setUsage_time(rs.getString("usage_time"));
				dto.setAmenity(rs.getString("amenity"));
				dto.setMinibar(rs.getString("minibar"));
				dto.setImage_path(rs.getString("image_path"));

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

	// 객실 상세보기
	public RoomManageVO selectRoom(int roomId) {
		System.out.println("selectRoom 실행");
		RoomManageVO vo = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = props.getProperty("selectRoom");
			ps = conn.prepareStatement(sql);
			ps.setInt(1, roomId);

			rs = ps.executeQuery();

			if (rs.next()) {
				vo = new RoomManageVO();
				vo.setRoom_id(rs.getInt("room_id"));
				vo.setRoom_name(rs.getString("room_name"));
				vo.setCapacity(rs.getString("capacity"));
				vo.setRoom_location(rs.getString("room_location"));
				vo.setRoom_description(rs.getString("room_description"));
				vo.setUsage_time(rs.getString("usage_time"));
				vo.setAmenity(rs.getString("amenity"));
				vo.setMinibar(rs.getString("minibar"));
				vo.setCreated_at(rs.getTimestamp("created_at"));
				vo.setUpdated_at(rs.getTimestamp("updated_at"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
			close(conn);
		}

		return vo;
	}

	// 객실이미지보기
	public List<RoomImageVO> selectRoomImages(int roomId) {
		System.out.println("selectRoomImages실행");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<RoomImageVO> list = new ArrayList<>();

		try {
			conn = getConnection();

			String sql = props.getProperty("selectRoomImages");
			ps = conn.prepareStatement(sql);
			ps.setInt(1, roomId);

			rs = ps.executeQuery();

			while (rs.next()) {
				RoomImageVO imageVO = new RoomImageVO();
				imageVO.setImage_no(rs.getInt("image_no"));
				imageVO.setRoom_id(rs.getInt("room_id"));
				imageVO.setImage_path(rs.getString("image_path"));
				imageVO.setIs_main(rs.getString("is_main"));
				imageVO.setDisplay_order(rs.getInt("display_order"));

				list.add(imageVO);
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