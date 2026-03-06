package com.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ResetCompleteController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 예약 완료/가입 완료에서 썼던 고급스러운 체크 아이콘 스타일 재활용
		request.setAttribute("pageCss", "complete");

		return "member/resetComplete";
	}
}