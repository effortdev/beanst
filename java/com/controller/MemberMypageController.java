package com.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberMypageController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("contentPage", "/WEB-INF/views/member/mypage.jsp");
		request.setAttribute("pageCss", "mypage");

		return "common/layout";
	}
}
