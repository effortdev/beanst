package com.controller;

import com.service.UserService;
import com.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UpdateController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		UserVO selectMember = (UserVO) session.getAttribute("loginMember");

		if (selectMember == null) {
			return "redirect:/login/login.do";
		}

		String userId = selectMember.getUserId();

		String currentPw = request.getParameter("currentPw");
		String newPw = request.getParameter("newPw");
		String newPwCheck = request.getParameter("newPwCheck");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String statusParam = request.getParameter("status"); // 탈퇴 여부

		UserService userService = new UserService(request.getServletContext());

		// 변경 사항 있으면 비밀번호 확인 필요
		boolean needPwCheck = (newPw != null && !newPw.trim().isEmpty()) || (email != null && !email.trim().isEmpty())
				|| (phone != null && !phone.trim().isEmpty()) || ("2".equals(statusParam));

		if (needPwCheck) {
			if (currentPw == null || currentPw.trim().isEmpty()) {
				session.setAttribute("msg", "정보 수정/회원탈퇴 전 비밀번호 확인이 필요합니다.");
				return "redirect:/member/myPage.do";
			}

			boolean pwCheck = userService.checkPassword(userId, currentPw);
			if (!pwCheck) {
				session.setAttribute("msg", "현재 비밀번호가 틀렸습니다.");
				return "redirect:/member/myPage.do";
			}
		}

		// 회원탈퇴
		if ("2".equals(statusParam)) {
			selectMember.setStatus("2");
			userService.updateMemberStatus(selectMember);

			session.invalidate();
			session = request.getSession();
			session.setAttribute("msg", "회원탈퇴요청이 정상적으로 처리되었습니다.");
			return "redirect:/";
		}

		// 새 비밀번호 변경
		if (newPw != null && !newPw.trim().isEmpty()) {
			if (!newPw.equals(newPwCheck)) {
				session.setAttribute("msg", "새 비밀번호와 확인이 일치하지 않습니다.");
				return "redirect:/member/myPage.do";
			}
			userService.resetPassword(userId, newPw);
			// 세션 객체도 갱신
			selectMember.setPassword(newPw);
			session.setAttribute("loginMember", selectMember);
		}

		// 이메일/전화번호 변경
		if ((email != null && !email.trim().isEmpty()) || (phone != null && !phone.trim().isEmpty())) {
			userService.updateContact(userId, email, phone);

			// 세션 객체도 갱신
			if (email != null && !email.trim().isEmpty())
				selectMember.setEmail(email);
			if (phone != null && !phone.trim().isEmpty())
				selectMember.setPhone(phone);
			session.setAttribute("selectMember", selectMember);
		}

		session.setAttribute("msg", "정보가 정상적으로 수정되었습니다.");
		return "redirect:/member/myPage.do";
	}
}