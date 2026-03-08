package com.controller;

import com.service.UserService;
import com.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UpdateController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		// 1️⃣ 세션에서 로그인 정보 가져오기
		HttpSession session = request.getSession();
		UserVO loginMember = (UserVO) session.getAttribute("loginMember");

		// 로그인 여부 체크
		if (loginMember == null) {
			return "redirect:/login.do";
		}

		String userId = loginMember.getUserId();

		// 2️⃣ 폼에서 전달된 정보
		String currentPw = request.getParameter("currentPw"); // JS prompt에서 전달
		String newPw = request.getParameter("newPw");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String statusParam = request.getParameter("status"); // 회원탈퇴 시 전달

		UserService service = new UserService(request.getServletContext());

		// 3️⃣ 비밀번호 확인 필수
		if (currentPw == null || currentPw.isEmpty()) {
			session.setAttribute("msg", "정보 수정/회원탈퇴 전 비밀번호 확인이 필요합니다.");
			return "redirect:/member/myPage.do";
		}

		boolean pwCheck = service.checkPassword(userId, currentPw);
		if (!pwCheck) {
			session.setAttribute("msg", "현재 비밀번호가 틀렸습니다.");
			return "redirect:/member/myPage.do";
		}

		// 4️⃣ 회원탈퇴 요청 처리
		if (statusParam != null && statusParam.equals("2")) {
			// status=2 -> 탈퇴
			loginMember.setStatus("2"); // VO 객체에도 반영
			service.updateMemberStatus(loginMember);

			// 세션 제거
			session.invalidate();

			// 완료 메시지
			session = request.getSession(); // 새 세션
			session.setAttribute("msg", "회원탈퇴가 정상적으로 처리되었습니다.");
			return "redirect:/"; // 메인 페이지로 이동
		}

		// 5️⃣ 새 비밀번호 변경
		if (newPw != null && !newPw.isEmpty()) {
			service.resetPassword(userId, newPw);
		}

		// 6️⃣ 이메일 / 전화번호 변경
		service.updateContact(userId, email, phone);

		// 7️⃣ 완료 후 마이페이지로 redirect
		session.setAttribute("msg", "정보가 정상적으로 수정되었습니다.");
		return "redirect:/member/myPage.do";
	}
}