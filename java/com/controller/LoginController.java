package com.controller;

import com.service.LoginService;
import com.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		String method = request.getMethod();
		request.setAttribute("pageCss", "login");

		if (method.equals("GET")) {
			return "login/login";
		}

		if (method.equals("POST")) {

			String userId = request.getParameter("user_id");
			String password = request.getParameter("password");

			LoginService service = new LoginService(request.getServletContext());

			UserVO login = service.login(userId, password);

			if (login != null) {

				if ("3".equals(login.getStatus())) {
					String dest = request.getParameter("dest");
					request.setAttribute("dest", dest);

					request.setAttribute("errorMsg", "해당 ID는 없는 계정입니다.");
					return "login/login";
				}

				HttpSession session = request.getSession();
				session.setAttribute("loginMember", login);


				String dest = request.getParameter("dest");
				if (dest != null && !dest.isEmpty()) {
					return "redirect:" + dest;
				}

				return "redirect:/reservationMain.do";
			} else {

				String dest = request.getParameter("dest");
				request.setAttribute("dest", dest);
				request.setAttribute("errorMsg", "아이디 또는 비밀번호가 틀렸습니다.");
				return "login/login";
			}
		}

		return null;
	}
}