package com.controller;

import java.util.List;

import com.dao.ReservationDAO;
import com.service.UserService;
import com.vo.ReservationVO;
import com.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyPageController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("pageCss", "myPage");
		// 로그인 사용자 정보 가져오기
		UserVO loginMember = (UserVO) request.getSession().getAttribute("loginMember");

		if (loginMember == null) {
			return "redirect:/login/login.do";
		}

		// 예약 목록 조회
		ReservationDAO reservationDAO = new ReservationDAO(request.getServletContext());
		List<ReservationVO> reservationList = reservationDAO.getReservationsByUserId(loginMember.getUserId());

		// JSP로 데이터 전달
		UserService svc = new UserService(request.getServletContext());

		request.setAttribute("selectMember", svc.selectUser(loginMember.getUserId()));
		request.setAttribute("reservationList", reservationList);

		return "member/myPage";
	}

}