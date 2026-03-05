package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.util.JdbcUtil;
import com.vo.RoomVO;

public class RoomDAO {

	public List<RoomVO> getAllRooms() {
		List<RoomVO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = JdbcUtil.getConnection();
			String sql = "SELECT * FROM room ORDER BY room_id ASC";
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
				vo.setRoomImg(rs.getString("room_img"));
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
}