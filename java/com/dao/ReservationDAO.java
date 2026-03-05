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
            // 예약 정보를 DB에 밀어넣는 쿼리 (status는 기본값 '예약완료'로 세팅)
            String sql = props.getProperty("insertReservation");
            
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, vo.getUserId());
            pstmt.setInt(2, vo.getRoomId());
            pstmt.setString(3, vo.getRoomName());  // 테이블 구조 변경으로 추가된 방 이름
            pstmt.setString(4, vo.getName());      // 세션에서 꺼낸 예약자 이름
            pstmt.setString(5, vo.getCheckIn());   
            pstmt.setString(6, vo.getCheckOut());
            pstmt.setInt(7, vo.getAdultCount());
            pstmt.setInt(8, vo.getChildCount());
            pstmt.setInt(9, vo.getTotalPrice());

            int result = pstmt.executeUpdate();

            // INSERT 성공 시 반드시 Commit 수행
            if (result > 0) {
                isSuccess = true;
                JdbcUtil.commit(con); 
            } else {
                JdbcUtil.rollback(con);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 에러 발생 시 Rollback 수행
            if (con != null) {
                JdbcUtil.rollback(con);
            }
        } finally {
            JdbcUtil.close(pstmt);
            JdbcUtil.close(con);
        }
        
        return isSuccess;
    }
    
    
 // 예약 가능 여부 확인 메서드 추가
    public boolean isRoomAvailable(int roomId, String checkIn, String checkOut) {
        boolean available = true;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = JdbcUtil.getConnection();
            // 날짜가 겹치는 '예약완료' 상태의 데이터 개수를 조회합니다.            
            String sql = props.getProperty("isRoomAvailable");
            
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, roomId);
            pstmt.setString(2, checkOut); // 요청한 체크아웃
            pstmt.setString(3, checkIn);  // 요청한 체크인

            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    available = false; // 겹치는 예약이 1개라도 있으면 예약 불가(false)
                }
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
            // 오늘 이후의 '예약완료' 상태인 예약 내역만 조회합니다.            
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
}