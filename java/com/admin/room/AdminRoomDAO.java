package com.admin.room;

import static com.util.JdbcUtil.close;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import jakarta.servlet.ServletContext;

public class AdminRoomDAO {

	private Properties props = new Properties();

	public AdminRoomDAO(ServletContext context) {

		try {

			InputStream input = context.getResourceAsStream("/WEB-INF/config/adminMapper.xml");

			props.loadFromXML(input);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public List<AdminRoomDTO> selectRoomList(Connection conn) {

		List<AdminRoomDTO> list = new ArrayList<>();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String sql = props.getProperty("adminRoomList");

			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {

				AdminRoomDTO room = new AdminRoomDTO();

				room.setRoomId(rs.getInt("room_id"));
				room.setRoomName(rs.getString("room_name"));
				room.setBaseCapacity(rs.getInt("base_capacity"));
				room.setMaxCapacity(rs.getInt("max_capacity"));
				room.setBasePrice(rs.getInt("base_price"));
				room.setExtraCharge(rs.getInt("extra_charge"));

				list.add(room);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}

		return list;
	}



	public AdminRoomDTO selectRoomDetail(Connection conn, int roomId) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		AdminRoomDTO room = null;

		try {

			String sql = props.getProperty("adminRoomDetail");

			ps = conn.prepareStatement(sql);

			ps.setInt(1, roomId);

			rs = ps.executeQuery();

			if (rs.next()) {

				room = new AdminRoomDTO();

				room.setRoomId(rs.getInt("room_id"));
				room.setRoomName(rs.getString("room_name"));
				room.setBaseCapacity(rs.getInt("base_capacity"));
				room.setMaxCapacity(rs.getInt("max_capacity"));
				room.setBasePrice(rs.getInt("base_price"));
				room.setExtraCharge(rs.getInt("extra_charge"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}

		return room;
	}



	public int updateRoom(Connection conn, AdminRoomDTO room) {

		PreparedStatement ps = null;

		int result = 0;

		try {

			String sql = props.getProperty("adminRoomUpdate");

			ps = conn.prepareStatement(sql);

			ps.setInt(1, room.getBaseCapacity());
			ps.setInt(2, room.getMaxCapacity());
			ps.setInt(3, room.getBasePrice());
			ps.setInt(4, room.getExtraCharge());
			ps.setInt(5, room.getRoomId());

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}

		return result;
	}

}