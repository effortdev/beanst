package com.controller;

import java.util.List;

import com.dao.ReservationDAO;
import com.vo.ReservationVO;
import com.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ReservationCheckController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("pageCss", "reservationCheck");

		ReservationDAO dao = new ReservationDAO(request.getServletContext());

		UserVO vo = (UserVO) request.getSession().getAttribute("loginMember");
		String userId = vo.getUserId();

		List<ReservationVO> reservationList = dao.getReservationsByUserId(userId);

		request.setAttribute("reservationList", reservationList);
		request.setAttribute("userId", userId);

		System.out.println("세션 userId = " + userId);
		System.out.println("DAO 조회 결과 = " + reservationList.size() + "건");

		return "reservation/reservationCheck";

	}
}