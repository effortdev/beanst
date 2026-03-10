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
		UserVO loginMember = (UserVO) request.getSession().getAttribute("loginMember");

		if (loginMember == null) {
			return "redirect:/login/login.do";
		}

		ReservationDAO reservationDAO = new ReservationDAO(request.getServletContext());
		List<ReservationVO> reservationList = reservationDAO.getReservationsByUserId(loginMember.getUserId());

		UserService svc = new UserService(request.getServletContext());

		request.setAttribute("selectMember", svc.selectUser(loginMember.getUserId()));
		request.setAttribute("reservationList", reservationList);

		return "member/myPage";
	}

}