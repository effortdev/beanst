package com.service;

import java.util.List;

import com.dao.ReservationDAO;
import com.dao.RoomDAO;
import com.vo.ReservationVO;
import com.vo.RoomVO;

import jakarta.servlet.ServletContext;

public class BookingService {

	private ServletContext context;

	public BookingService(ServletContext context) {
		this.context = context;
	}

	public List<RoomVO> getAllRooms(ServletContext context) {

		RoomDAO dao = new RoomDAO(context);
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

	public List<ReservationVO> getReservedDatesExcludingSelf(int resId) {
		ReservationDAO dao = new ReservationDAO(context);
		return dao.getReservedDatesExcludingSelf(resId);
	}

	public boolean checkRoomAvailabilityForUpdate(int roomId, String checkIn, String checkOut, int resId) {
		ReservationDAO dao = new ReservationDAO(context);
		return dao.isRoomAvailableForUpdate(roomId, checkIn, checkOut, resId);
	}

	public boolean modifyReservation(ReservationVO reserveVO) {
		ReservationDAO dao = new ReservationDAO(context);
		return dao.updateReservation(reserveVO);
	}

	public ReservationVO getReservation(int resId) {
		ReservationDAO dao = new ReservationDAO(context);
		return dao.getReservation(resId);
	}
}