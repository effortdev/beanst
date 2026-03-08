package com.controller.admin;

import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.controller.Action;
import com.service.admin.AdminService; // (본인 프로젝트의 서비스 클래스명에 맞게 수정하세요)
import com.vo.UserVO;

public class AdminUserController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("pageCss", "admin-member");
		String method = request.getMethod();
		String action = request.getParameter("action"); // 어떤 작업을 할지 구분
		AdminService service = new AdminService(request.getServletContext());

		// [GET] 화면 보여주기
		if (method.equals("GET")) {
			// 1. 회원 정보 수정 폼 보여주기
			if ("edit".equals(action)) {
				String userId = request.getParameter("userId");
				UserVO user = service.getUserDetail(userId); // DAO 연결 필요
				request.setAttribute("user", user);
				return "admin/member/adminMemberEdit"; // 수정 JSP로 이동
			}

			// 2. (기본) 회원 전체 목록 보여주기
			List<UserVO> userList = service.getAllUsers(); // DAO 연결 필요

			String filter = request.getParameter("filter"); // 주소창의 ?filter= 값을 확인합니다.

			// 만약 '탈퇴요청 회원보기' 탭을 눌렀다면
			if ("withdraw".equals(filter)) {
				List<UserVO> filteredList = new java.util.ArrayList<>();
				for (UserVO user : userList) {
					if ("2".equals(user.getStatus())) { // 상태가 2(탈퇴요청)인 사람만 담습니다.
						filteredList.add(user);
					}
				}
				userList = filteredList; // 걸러낸 리스트로 덮어씁니다.
			}

			request.setAttribute("userList", userList);
			return "admin/member/adminMemberList"; // 목록 JSP로 이동
		}

		// [POST] 데이터 변경 처리
		if (method.equals("POST")) {
			// 1. 탈퇴 승인 처리
			if ("approveWithdraw".equals(action)) {
				String userId = request.getParameter("userId");
				boolean result = service.approveWithdraw(userId); // DAO 연결 (status='3'으로 업데이트)
				if (result) {
					request.setAttribute("msg", "탈퇴 처리가 완료되었습니다.");
				}
				return "redirect:/admin/memberManage.do?msg=withdraw"; // 본인의 목록 URL로 변경
			}

			// 2. 회원 정보 수정 완료 처리
			if ("update".equals(action)) {
				UserVO vo = new UserVO();
				vo.setUserId(request.getParameter("user_id"));
				vo.setName(request.getParameter("name"));
				vo.setEmail(request.getParameter("email"));
				vo.setPhone(request.getParameter("phone"));
				vo.setRole(request.getParameter("role"));
				vo.setStatus(request.getParameter("status"));

				boolean result = service.updateUser(vo); // DAO 연결
				return "redirect:/admin/memberManage.do?msg=update";
			}
		}

		return null;
	}
}