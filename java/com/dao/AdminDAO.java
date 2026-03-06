package com.dao;

import static com.util.JdbcUtil.close;
import static com.util.JdbcUtil.commit;
import static com.util.JdbcUtil.getConnection;
import static com.util.JdbcUtil.rollback;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.dto.AdminDTO;
import com.vo.RoomImageVO;
import com.vo.RoomManegeVO;

import jakarta.servlet.ServletContext;

public class AdminDAO {

	private Properties props = new Properties();

	public AdminDAO(ServletContext context) {
		try {
			System.out.println("AdminDAO 생성자 실행");
			InputStream input = context.getResourceAsStream("/WEB-INF/config/adminMapper.xml");
			props.loadFromXML(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AdminDTO login(String userId, String password) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = props.getProperty("adminLogin");
			ps = conn.prepareStatement(sql);

			System.out.println("sql = " + sql);

			ps.setString(1, userId);
			ps.setString(2, password);

			rs = ps.executeQuery();

			if (rs.next()) {
				AdminDTO dto = new AdminDTO();
				dto.setUserId(rs.getString("user_id"));
				dto.setRole(rs.getString("role"));
				return dto;
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

	// 객실 목록보기
	public List<RoomManegeVO> selectRoomList() {
		System.out.println("selectRoomList 실행");
		List<RoomManegeVO> list = new ArrayList<>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		String sql = props.getProperty("adminRoomManage");

		try {
			conn = getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				RoomManegeVO room = new RoomManegeVO();
				room.setRoom_id(rs.getInt("room_id"));
				room.setRoom_name(rs.getString("room_name"));
				room.setCapacity(rs.getString("capacity"));
				room.setRoom_location(rs.getString("room_location"));
				room.setRoom_description(rs.getString("room_description"));
				room.setCreated_at(rs.getTimestamp("created_at"));
				room.setUpdated_at(rs.getTimestamp("updated_at"));

				list.add(room);
			}

			rs.close();
			st.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	// 객실 상세보기
	public RoomManegeVO selectRoomByNo(int roomId) {
		System.out.println("selectRoomByNo 실행");
		RoomManegeVO vo = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = props.getProperty("selectRoomByNo");
			ps = conn.prepareStatement(sql);
			ps.setInt(1, roomId);

			rs = ps.executeQuery();

			if (rs.next()) {
				vo = new RoomManegeVO();
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

	// 객실 정보 변경
	public void updateRoom(RoomManegeVO vo) {
		System.out.println("updateRoom 실행");
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = getConnection();

			String sql = props.getProperty("updateRoom");
			System.out.println("updateRoom sql : " + sql);
			ps = conn.prepareStatement(sql);

			ps.setString(1, vo.getRoom_name());
			ps.setString(2, vo.getCapacity());
			ps.setString(3, vo.getRoom_location());
			ps.setString(4, vo.getRoom_description());
			ps.setString(5, vo.getUsage_time());
			ps.setString(6, vo.getAmenity());
			ps.setString(7, vo.getMinibar());
			ps.setInt(8, vo.getRoom_id());

			int result = ps.executeUpdate();
			System.out.println("updateRoom result : " + result);
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			close(ps);
			close(conn);
		}
	}

	public void updateMainImage(int roomId, int imageId) {

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = getConnection();

			String sql = props.getProperty("updateRoomMainImage");

			ps = conn.prepareStatement(sql);
			ps.setInt(1, imageId);
			ps.setInt(2, roomId);

			ps.executeUpdate();

			commit(conn);

		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			close(ps);
			close(conn);
		}
	}

	public void deleteRoom(int room_id) {
		System.out.println("deleteRoom실행");
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = getConnection();
			String sql = props.getProperty("deleteRoom");

			ps = conn.prepareStatement(sql);
			ps.setInt(1, room_id);

			ps.executeUpdate();
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			close(ps);
			close(conn);
		}
	}

	public void deleteRoomImages(int roomNo) {
		System.out.println("deleteRoomImages실행");
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = getConnection();

			String sql = props.getProperty("deleteRoomImage");
			ps = conn.prepareStatement(sql);
			ps.setInt(1, roomNo);

			ps.executeUpdate();
			commit(conn);

		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			close(ps);
			close(conn);
		}
	}

	// DB이미지삭제
	public void deleteRoomImageDB(int imageNo) {

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = getConnection();

			String sql = props.getProperty("deleteRoomImageDB");
			ps = conn.prepareStatement(sql);
			ps.setInt(1, imageNo);

			ps.executeUpdate();

			commit(conn);

		} catch (Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(ps);
			close(conn);
		}
	}

	public String getImagePath(int imageNo) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String path = null;

		try {

			conn = getConnection();

			String sql = props.getProperty("selectRoomImagePath");

			ps = conn.prepareStatement(sql);
			ps.setInt(1, imageNo);

			rs = ps.executeQuery();

			if (rs.next()) {
				path = rs.getString("image_path");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
			close(conn);
		}

		return path;
	}

	// 서버 이미지 삭제
	public void deleteRoomImage(String imagePath) {

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = getConnection();

			String sql = props.getProperty("deleteRoomImageByPath");
			ps = conn.prepareStatement(sql);
			ps.setString(1, imagePath);

			ps.executeUpdate();
			commit(conn);

		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			close(ps);
			close(conn);
		}
	}

	public void insertRoom(RoomManegeVO vo) {

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = getConnection();

			String sql = props.getProperty("insertRoom");
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getRoom_id());
			ps.setString(2, vo.getRoom_name());
			ps.setString(3, vo.getCapacity());
			ps.setString(4, vo.getRoom_location());
			ps.setString(5, vo.getRoom_description());
			ps.setString(6, vo.getUsage_time());
			ps.setString(7, vo.getAmenity());
			ps.setString(8, vo.getMinibar());

			ps.executeUpdate();
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			close(ps);
			close(conn);
		}
	}

	public int insertRoomImage(int roomId, String imagePath, String isMain, int displayOrder) {
		

		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;

		try {
			conn = getConnection();
			String sql = props.getProperty("insertRoomImage");
			ps = conn.prepareStatement(sql);

			ps.setInt(1, roomId);
			ps.setString(2, imagePath);
			ps.setString(3, isMain);
			ps.setInt(4, displayOrder);

			result = ps.executeUpdate();
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			close(ps);
			close(conn);
		}

		return result;
	}

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
				imageVO.setDisplay_order(rs.getString("display_order"));

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