package com.controller.reservation;

import java.util.List;

import com.controller.Action;
import com.service.BookingService;
import com.vo.ReservationVO;
import com.vo.RoomVO;
import com.vo.UserVO;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class BookingController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		ServletContext context = request.getServletContext();
		request.setAttribute("pageCss", "reservationSub");

		String method = request.getMethod();
		BookingService service = new BookingService(request.getServletContext());

		if (method.equals("GET")) {
			List<RoomVO> roomList = service.getAllRooms(context);
			List<ReservationVO> bookedList;

			String mode = request.getParameter("mode");
			String resIdStr = request.getParameter("resId");

			if ("update".equals(mode) && resIdStr != null) {
				int resId = Integer.parseInt(resIdStr);
				bookedList = service.getReservedDatesExcludingSelf(resId);
				ReservationVO originRes = service.getReservation(resId);

				request.setAttribute("originRes", originRes);
				request.setAttribute("mode", "update");
			} else {
				bookedList = service.getReservedDates();
			}

			if (bookedList != null) {
				for (ReservationVO vo : bookedList) {
				}
			}

			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for (int i = 0; i < bookedList.size(); i++) {
				ReservationVO r = bookedList.get(i);

				String cIn = r.getCheckIn();
				String cOut = r.getCheckOut();
				if (cIn != null && cIn.length() >= 10)
					cIn = cIn.substring(0, 10);
				if (cOut != null && cOut.length() >= 10)
					cOut = cOut.substring(0, 10);

				sb.append("{ roomId: ").append(r.getRoomId()).append(", checkIn: '").append(cIn)
						.append("', checkOut: '").append(cOut).append("' }");
				if (i < bookedList.size() - 1)
					sb.append(", ");
			}
			sb.append("]");

			request.setAttribute("bookedListJson", sb.toString());
			request.setAttribute("roomList", roomList);
			return "reservation/booking";
		}

		if (method.equals("POST")) {

			try {
				HttpSession session = request.getSession();
				UserVO loginMember = (UserVO) session.getAttribute("loginMember");

				if (loginMember == null) {
					return "redirect:/login/login.do";
				}

				int roomId = Integer.parseInt(request.getParameter("room_id"));
				String checkIn = request.getParameter("check_in");
				String checkOut = request.getParameter("check_out");
				String roomName = request.getParameter("room_name");
				int adultCount = Integer.parseInt(request.getParameter("adult_count"));
				int childCount = Integer.parseInt(request.getParameter("child_count"));
				int totalPrice = Integer.parseInt(request.getParameter("total_price"));

				String mode = request.getParameter("mode");
				String reservationIdStr = request.getParameter("reservation_id");

				ReservationVO reserveVO = new ReservationVO();
				reserveVO.setUserId(loginMember.getUserId());
				reserveVO.setRoomId(roomId);
				reserveVO.setRoomName(roomName);
				reserveVO.setName(loginMember.getName());
				reserveVO.setCheckIn(checkIn);
				reserveVO.setCheckOut(checkOut);
				reserveVO.setAdultCount(adultCount);
				reserveVO.setChildCount(childCount);
				reserveVO.setTotalPrice(totalPrice);

				boolean isAvailable;
				if ("update".equals(mode) && reservationIdStr != null && !reservationIdStr.isEmpty()) {
					int resId = Integer.parseInt(reservationIdStr);
					reserveVO.setReservationId(resId);
					isAvailable = service.checkRoomAvailabilityForUpdate(roomId, checkIn, checkOut, resId);
				} else {
					isAvailable = service.checkRoomAvailability(roomId, checkIn, checkOut);
				}

				if (!isAvailable) {
					request.setAttribute("errorMsg", "선택하신 날짜에 이미 예약된 객실입니다. 다른 날짜나 객실을 선택해 주세요.");

					request.setAttribute("roomList", service.getAllRooms(context));
					request.setAttribute("mode", mode);
					if ("update".equals(mode)) {
						request.setAttribute("originRes", reserveVO);
					}

					List<ReservationVO> bookedList = ("update".equals(mode) && reservationIdStr != null)
							? service.getReservedDatesExcludingSelf(Integer.parseInt(reservationIdStr))
							: service.getReservedDates();

					StringBuilder sb = new StringBuilder();
					sb.append("[");
					for (int i = 0; i < bookedList.size(); i++) {
						ReservationVO r = bookedList.get(i);

						String cIn = r.getCheckIn();
						String cOut = r.getCheckOut();
						if (cIn != null && cIn.length() >= 10)
							cIn = cIn.substring(0, 10);
						if (cOut != null && cOut.length() >= 10)
							cOut = cOut.substring(0, 10);

						sb.append("{ roomId: ").append(r.getRoomId()).append(", checkIn: '").append(cIn)
								.append("', checkOut: '").append(cOut).append("' }");
						if (i < bookedList.size() - 1)
							sb.append(", ");
					}
					sb.append("]");
					request.setAttribute("bookedListJson", sb.toString());

					return "reservation/booking";
				}

				boolean isSuccess;
				if ("update".equals(mode) && reservationIdStr != null && !reservationIdStr.isEmpty()) {
					isSuccess = service.modifyReservation(reserveVO);
				} else {
					isSuccess = service.makeReservation(reserveVO);
				}

				if (isSuccess) {
					return "redirect:/reservation/complete.do";
				} else {
					request.setAttribute("errorMsg", "예약 처리 중 문제가 발생했습니다.");
					return "redirect:/reservation/booking.do";
				}

			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/reservation/booking.do";
			}
		}
		return null;
	}
}