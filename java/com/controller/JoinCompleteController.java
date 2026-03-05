package com.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JoinCompleteController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		// 예약 완료 화면에서 썼던 complete.css를 재활용합니다!
		request.setAttribute("pageCss", "complete");

		return "member/joinComplete";
	}
}