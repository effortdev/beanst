package com.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.service.UserService;

public class ResetPwController implements Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("resetId");

		if (userId == null)
			return "redirect:/member/findPw.do"; // 비정상 접근 차단

		if (request.getMethod().equals("GET")) {
			request.setAttribute("pageCss", "join");
			return "member/resetPw";
		}

		String newPw = request.getParameter("password");
		UserService service = new UserService(request.getServletContext());

		if (service.resetPassword(userId, newPw)) {
			session.removeAttribute("resetId"); // 세션 정리
			return "redirect:/member/resetComplete.do"; // 완료 페이지로!
		}
		return "member/resetPw";
	}
}