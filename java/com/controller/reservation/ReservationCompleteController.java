package com.controller.reservation;

import com.controller.Action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ReservationCompleteController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		// CSS 적용 (기존 style.css 사용)
		request.setAttribute("pageCss", "style");

		// complete.jsp로 포워딩
		return "reservation/complete";
	}
}