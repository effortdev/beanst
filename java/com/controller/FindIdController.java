package com.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.service.UserService;

public class FindIdController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String method = request.getMethod();
		request.setAttribute("pageCss", "login"); 

		// 1. 단순 화면 요청 (GET)
		if (method.equals("GET")) {
			return "member/findId"; // 아이디 찾기 입력 폼 띄우기
		}

		// 2. 아이디 찾기 실행 (POST)
		if (method.equals("POST")) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");

			UserService service = new UserService(request.getServletContext());
			String foundId = service.findUserId(name, email);

			if (foundId != null) {
				// 아이디를 찾았을 경우
				request.setAttribute("foundId", foundId);
				request.setAttribute("name", name);
				return "member/findIdResult"; // 결과 전용 페이지로 이동
			} else {
				// 아이디를 못 찾았을 경우
				request.setAttribute("errorMsg", "입력하신 정보와 일치하는 아이디가 없습니다.");
				return "member/findId"; // 다시 입력 폼으로 (에러메시지 지참)
			}
		}
		return null;
	}
}