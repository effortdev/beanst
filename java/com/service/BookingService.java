package com.service;

import java.util.List;
import jakarta.servlet.ServletContext;

import com.dao.ReservationDAO;
import com.dao.RoomDAO;
import com.vo.ReservationVO;
import com.vo.RoomVO;

public class BookingService {

    private ServletContext context;

    // 생성자에서 context를 받아옵니다.
    public BookingService(ServletContext context) {
        this.context = context;
    }

    // 객실 목록 가져오기
    public List<RoomVO> getAllRooms() {
        // 기존 패턴과 동일하게 DAO를 생성하여 호출합니다.
        // (만약 RoomDAO에서도 context가 필요하다면 넘겨줄 수 있도록 구조를 맞췄습니다)
        RoomDAO dao = new RoomDAO(); 
        return dao.getAllRooms();
    }
    
    public boolean makeReservation(ReservationVO reserveVO) {
	    ReservationDAO dao = new ReservationDAO(context);
	    return dao.insertReservation(reserveVO);
	}
    
    public boolean checkRoomAvailability(int roomId, String checkIn, String checkOut) {
        ReservationDAO dao = new ReservationDAO(context);
        return dao.isRoomAvailable(roomId, checkIn, checkOut);
    }
    
    public List<ReservationVO> getReservedDates() {
        ReservationDAO dao = new ReservationDAO(context);
        return dao.getReservedDates();
    }
}