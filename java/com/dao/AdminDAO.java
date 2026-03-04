package com.dao;

import static com.util.JdbcUtil.close;
import static com.util.JdbcUtil.getConnection;
import static com.util.JdbcUtil.commit;
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
import com.vo.RoomVO;

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
	
	//객실 목록보기
	public List<RoomVO> selectRoomList() {

		List<RoomVO> list = new ArrayList<>();
		Connection conn = null;
        Statement st = null;
        ResultSet rs =  null;
        
        String sql = props.getProperty("adminRoomManage");

        try {
            conn = getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while(rs.next()) {
                RoomVO room = new RoomVO();
                room.setRoom_no(rs.getInt("room_no"));
                room.setRoom_name(rs.getString("room_name"));
                room.setCapacity(rs.getString("capacity"));
                room.setRoom_location(rs.getString("room_location"));
                room.setRoom_description(rs.getString("room_description"));
                room.setRoom_image(rs.getString("room_image"));
                room.setCreated_at(rs.getTimestamp("created_at"));
                room.setUpdated_at(rs.getTimestamp("updated_at"));

                list.add(room);
            }

            rs.close();
            st.close();
            conn.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }
	
	//객실 상세보기
	public RoomVO selectRoomByNo(int roomNo) {

	    RoomVO vo = null;
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        conn = getConnection();

	        String sql = "SELECT * FROM room_manage WHERE room_no = ?";
	        ps = conn.prepareStatement(sql);
	        ps.setInt(1, roomNo);

	        rs = ps.executeQuery();

	        if (rs.next()) {
	        	vo = new RoomVO();
	        	vo.setRoom_no(rs.getInt("room_no"));
	        	vo.setRoom_name(rs.getString("room_name"));
	        	vo.setCapacity(rs.getString("capacity"));
	        	vo.setRoom_location(rs.getString("room_location"));
	        	vo.setRoom_description(rs.getString("room_description"));
	        	vo.setRoom_image(rs.getString("room_image"));
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
	
	//객실 정보 변경
	public void updateRoom(RoomVO vo) {
		System.out.println("updateRoom 실행");
	    Connection conn = null;
	    PreparedStatement ps = null;

	    try {
	        conn = getConnection();

	        String sql = props.getProperty("updateRoom");
	        System.out.println("updateRoom sql : "+sql);
	        ps = conn.prepareStatement(sql);

	        ps.setString(1, vo.getRoom_name());
	        ps.setString(2, vo.getCapacity()); 
	        ps.setString(3, vo.getRoom_location());
	        ps.setString(4, vo.getRoom_description());
	        ps.setInt(5, vo.getRoom_no());

	        int result=ps.executeUpdate();
	        System.out.println("updateRoom result : "+result);
	        commit(conn);
	    } catch (Exception e) {
	        e.printStackTrace();
	        rollback(conn);
	    } finally {
	        close(ps);
	        close(conn);
	    }
	}
	
	public void deleteRoom(int room_no) {
		System.out.println("deleteRoom실행");
	    Connection conn = null;
	    PreparedStatement ps = null;

	    try {
	        conn = getConnection();
	        String sql = props.getProperty("deleteRoom");
	        
	        ps = conn.prepareStatement(sql);
	        ps.setInt(1, room_no);

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
	
	public void insertRoom(RoomVO vo) {
		System.out.println("insertRoom실행");
	    Connection conn = null;
	    PreparedStatement ps = null;

	    try {
	        conn = getConnection();

	        String sql = props.getProperty("insertRoom");
	        ps = conn.prepareStatement(sql);
	        ps.setInt(1, vo.getRoom_no());
	        ps.setString(2, vo.getRoom_name());
	        ps.setString(3, vo.getCapacity());
	        ps.setString(4, vo.getRoom_location());
	        ps.setString(5, vo.getRoom_description());

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
	
	public int insertRoomImage(
            int roomNo,
            String imagePath,
            String isMain,
            int displayOrder)  {
		System.out.println("insertRoomImage실행");
		String sql = props.getProperty("insertRoomImage");
		
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, roomNo);
			ps.setString(2, imagePath);
			ps.setString(3, isMain);
			ps.setInt(4, displayOrder);
			
			result = ps.executeUpdate();
			commit(conn);
		}catch(Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			close(ps);
	        close(conn);
		}
		
		return result;
		}
	
	public List<String> selectRoomImages(int roomNo) {

	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<String> list = new ArrayList<>();

	    try {
	        conn = getConnection();

	        String sql = "SELECT image_path FROM room_image WHERE room_no = ?";
	        ps = conn.prepareStatement(sql);
	        ps.setInt(1, roomNo);

	        rs = ps.executeQuery();

	        while (rs.next()) {
	            list.add(rs.getString("image_path"));
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