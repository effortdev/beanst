package com.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.service.UserService;

public class FindPwController implements Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		if (request.getMethod().equals("GET")) {
			request.setAttribute("pageCss", "login");
			return "member/findPw";
		}

		// POST: 본인 확인 처리
		String userId = request.getParameter("user_id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		UserService service = new UserService(request.getServletContext());
		if (service.isUserValid(userId, name, email)) {
			// 확인 성공 시, 비밀번호 변경 페이지에서 쓸 아이디를 세션에 임시 저장
			HttpSession session = request.getSession();
			session.setAttribute("resetId", userId);
			return "redirect:/member/resetPw.do";
		} else {
			request.setAttribute("errorMsg", "입력하신 정보와 일치하는 회원이 없습니다.");
			request.setAttribute("pageCss", "login");
			return "member/findPw";
		}
	}
}