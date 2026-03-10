package com.controller.admin;

import static com.util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.List;

import com.controller.Action;
import com.service.admin.AdminService; // (본인 프로젝트의 서비스 클래스명에 맞게 수정하세요)
import com.util.PageInfo;
import com.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminUserController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("AdminUserController실행");
		request.setAttribute("pageCss", "admin-member");
		Connection conn = null;
		String method = request.getMethod();
		String action = request.getParameter("action"); // 어떤 작업을 할지 구분
		AdminService service = new AdminService(request.getServletContext());

		int currentPage = 1;

		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}

		conn = getConnection();

		// 전체 글 수
		int listCount = service.selectUserCount(conn);

		// 페이지 설정
		int pageLimit = 10; // 페이지 번호 개수
		int boardLimit = 10; // 한 페이지 글 개수

		// PageInfo 생성
		int totalPage = (int) Math.ceil((double) listCount / boardLimit);

		PageInfo pageInfo = new PageInfo(currentPage, listCount, pageLimit, boardLimit);

		// 목록 조회
		List<UserVO> list = service.selectUserList(conn, pageInfo.getStartRow(), pageInfo.getEndRow());

		// request 전달
		request.setAttribute("userList", list);
		request.setAttribute("pageInfo", pageInfo);

		// [GET] 화면 보여주기
		if (method.equals("GET")) {
			// 1. 회원 정보 수정 폼 보여주기
			if ("edit".equals(action)) {
				String userId = request.getParameter("userId");
				UserVO user = service.getUserDetail(userId); // DAO 연결 필요
				request.setAttribute("user", user);
				return "admin/member/adminMemberEdit"; // 수정 JSP로 이동
			}

//			// 2. (기본) 회원 전체 목록 보여주기
//			List<UserVO> userList = service.getAllUsers(); // DAO 연결 필요

			String filter = request.getParameter("filter"); // 주소창의 ?filter= 값을 확인합니다.

			if ("withdraw".equals(filter)) {
				List<UserVO> allUsers = service.getAllUsers();
				List<UserVO> filteredList = new java.util.ArrayList<>();

				if (allUsers != null) {
					for (UserVO user : allUsers) {
						if ("2".equals(user.getStatus())) {
							filteredList.add(user);
						}
					}
				}
				request.setAttribute("userList", filteredList);

				// 페이징 에러를 막기 위해 임시 PageInfo 세팅 (탈퇴 요청 리스트는 보통 한 페이지에 다 보여줌)
				request.setAttribute("pageInfo",
						new PageInfo(1, filteredList.size(), 10, filteredList.size() > 0 ? filteredList.size() : 1));

			} else {
				// [전체 회원 탭] 기존의 10명씩 가져오는 페이징 리스트 유지
				request.setAttribute("userList", list);
			}
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