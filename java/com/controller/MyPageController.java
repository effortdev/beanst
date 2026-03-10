package com.controller;

import com.service.MyPageService;
import com.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MyPageController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		String method = request.getMethod();
		HttpSession session = request.getSession();
		request.setAttribute("pageCss", "login");
		UserVO loginMember = (UserVO) session.getAttribute("loginMember");

		if (loginMember == null) {
			return "redirect:/login/login.do";
		}

		MyPageService service = new MyPageService(request.getServletContext());
		String userId = loginMember.getUserId();


		if (method.equals("GET")) {
			// 최신 내 정보를 DB에서 다시 불러옴
			UserVO myInfo = service.getMyInfo(userId);
			request.setAttribute("myInfo", myInfo);
			request.setAttribute("pageCss", "login"); 
			return "member/myPage"; 
		}


		if (method.equals("POST")) {
			String actionType = request.getParameter("actionType");
			String currentPw = request.getParameter("currentPw");


			if (currentPw == null || !service.checkPassword(userId, currentPw)) {
				session.setAttribute("msg", "현재 비밀번호가 틀렸습니다.");
				return "redirect:/member/myPage.do";
			}

			if ("withdraw".equals(actionType)) {
				service.withdrawUser(userId);
				loginMember.setStatus("2");
				session.setAttribute("loginMember", loginMember);
				session.setAttribute("msg", "회원탈퇴 요청이 접수되었습니다. 관리자 승인 전까지 정상 이용 가능합니다.");
				return "redirect:/member/myPage.do";
			}


			if ("update".equals(actionType)) {
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				String phone = request.getParameter("phone");
				String newPw = request.getParameter("newPw");
				String newPwCheck = request.getParameter("newPwCheck");


				if (service.isEmailDup(email, userId)) {
					session.setAttribute("msg", "이미 사용 중인 이메일입니다.");
					return "redirect:/member/myPage.do";
				}
				if (service.isPhoneDup(phone, userId)) {
					session.setAttribute("msg", "이미 사용 중인 전화번호입니다.");
					return "redirect:/member/myPage.do";
				}

	
				if (newPw != null && !newPw.trim().isEmpty()) {
					if (!newPw.equals(newPwCheck)) {
						session.setAttribute("msg", "새 비밀번호가 서로 일치하지 않습니다.");
						return "redirect:/member/myPage.do";
					}
					service.updateMyPassword(userId, newPw);
				}


				UserVO updateVO = new UserVO();
				updateVO.setUserId(userId);
				updateVO.setName(name);
				updateVO.setEmail(email);
				updateVO.setPhone(phone);
				service.updateMyInfo(updateVO);


				loginMember.setName(name);
				loginMember.setEmail(email);
				loginMember.setPhone(phone);
				session.setAttribute("loginMember", loginMember);

				session.setAttribute("msg", "정보가 성공적으로 수정되었습니다.");
				return "redirect:/member/myPage.do";
			}
		}

		return null;
	}
}