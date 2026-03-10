package com.controller;

import com.service.UserService;
import com.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JoinController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String method = request.getMethod();

		if (method.equals("GET")) {
			request.setAttribute("pageCss", "login");
			return "member/join";
		}

		if (method.equals("POST")) {
			String userId = request.getParameter("user_id");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");

			UserService service = new UserService(request.getServletContext());


			if (service.isPhoneDuplicateForJoin(phone)) {
				request.setAttribute("errorMsg", "이미 가입된 전화번호입니다. 다른 번호를 입력해주세요.");
				request.setAttribute("pageCss", "login");

	
				request.setAttribute("joinId", userId);
				request.setAttribute("joinName", name);
				request.setAttribute("joinEmail", email);
				request.setAttribute("joinPhone", phone);

				return "member/join"; 
			}


			UserVO userVO = new UserVO();
			userVO.setUserId(userId);
			userVO.setPassword(password);
			userVO.setEmail(email);
			userVO.setName(name);
			userVO.setPhone(phone);

			boolean isSuccess = service.joinUser(userVO);

			if (isSuccess) {
				return "redirect:/member/joinComplete.do";
			} else {
				request.setAttribute("errorMsg", "회원가입 처리 중 오류가 발생했습니다. (중복된 아이디나 이메일일 수 있습니다.)");
				request.setAttribute("pageCss", "login");
				return "member/join";
			}
		}

		return null;
	}
}