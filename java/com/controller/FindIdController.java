package com.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.service.UserService;

public class FindIdController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String method = request.getMethod();
		request.setAttribute("pageCss", "login");

		if (method.equals("GET")) {
			return "member/findId";
		}

		if (method.equals("POST")) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");

			UserService service = new UserService(request.getServletContext());
			String foundId = service.findUserId(name, email);

			if (foundId != null) {

				request.setAttribute("foundId", foundId);
				request.setAttribute("name", name);
				return "member/findIdResult"; 
			} else {

				request.setAttribute("errorMsg", "입력하신 정보와 일치하는 아이디가 없습니다.");
				return "member/findId"; 
			}
		}
		return null;
	}
}